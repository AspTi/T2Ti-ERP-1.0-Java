package com.t2tierp.financeiro.cliente;

import com.t2tierp.financeiro.java.ViewFinFluxoCaixaID;
import com.t2tierp.padrao.java.Constantes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.message.send.java.GridParams;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.client.ClientUtils;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe de controle da tela FinFluxoCaixaGrid.</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM</p>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 *       The author may be contacted at:
 *           t2ti.com@gmail.com</p>
 *
 * @author Claudio de Barros (T2Ti.COM)
 * @version 1.0
 */
public class FinFluxoCaixaGridController extends GridController implements GridDataLocator {

    private FinFluxoCaixaGrid grid;
    private String acaoServidor;
    private Date dataInicio;
    private Date dataFim;

    public FinFluxoCaixaGridController() {
        grid = new FinFluxoCaixaGrid(this);
        acaoServidor = "finFluxoCaixaGridAction";
        MDIFrame.add(grid);
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        dataInicio = getDataInicial(grid.getPeriodo());
        if (dataInicio != null) {
            dataFim = ultimoDiaMes(dataInicio);
            otherGridParams.put("acao", Constantes.LOAD);
            otherGridParams.put("dataInicio", dataInicio);
            otherGridParams.put("dataFim", dataFim);

            return ClientUtils.getData(acaoServidor, new GridParams(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, otherGridParams));
        }
        grid.getGrid1().clearData();
        return new ErrorResponse("Per??odo inv??lido!");
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        ViewFinFluxoCaixaID fluxo = (ViewFinFluxoCaixaID) persistentObject;
        new FinFluxoCaixaDetalheController(grid, fluxo, dataInicio, dataFim);
    }

    private Date getDataInicial(String periodo) {
        try {
            if (periodo == null) {
                return null;
            }
            if ((periodo.length() != 7)) {
                return null;
            }
            Calendar dataValida = Calendar.getInstance();
            dataValida.setLenient(false);
            int mes = Integer.valueOf(periodo.substring(0, 2));
            int ano = Integer.valueOf(periodo.substring(3, 7));

            dataValida.set(Calendar.DAY_OF_MONTH, 1);
            dataValida.set(Calendar.MONTH, mes - 1);
            dataValida.set(Calendar.YEAR, ano);

            dataValida.getTime();

            return dataValida.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    private Date ultimoDiaMes(Date dataInicio) {
        Calendar dataF = Calendar.getInstance();
        dataF.setTime(dataInicio);
        dataF.setLenient(false);
        dataF.set(Calendar.DAY_OF_MONTH, dataF.getActualMaximum(Calendar.DAY_OF_MONTH));

        return dataF.getTime();
    }
}
