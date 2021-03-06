package com.t2tierp.contabilidade.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.contabilidade.java.ViewDfcVO;
import com.t2tierp.padrao.cliente.Container;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.client.ClientUtils;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe de controle da tela DfcGrid.</p>
 *
 * <p>The MIT License</p>
 *
 * <p>Copyright: Copyright (C) 2010 T2Ti.COM
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
 *        The author may be contacted at:
 *            t2ti.com@gmail.com</p>
 *
 * @author Claudio de Barros (t2ti.com@gmail.com)
 * @version 1.0
 */
public class DfcGridController {

    private DfcGrid grid;
    private String acaoServidor;

    public DfcGridController() {
        grid = new DfcGrid(this);
        acaoServidor = "dfcGridAction";
        MDIFrame.add(grid);
    }

    public void geraRelatorio(String periodo) throws Exception {
        Response res = ClientUtils.getData(acaoServidor, new Object[]{periodo});
        if (res.isError()) {
            throw new Exception(res.getErrorMessage());
        }

        List<ViewDfcVO> listaDfc = ((VOListResponse) res).getRows();
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ImageIcon logoEmpresa = new ImageIcon(this.getClass().getResource("/images/t2ti.jpg"));
        Map parametros = new HashMap();
        parametros.put("LOGO_EMPRESA", logoEmpresa.getImage());
        parametros.put("NOME_FANTASIA", empresa.getNomeFantasia());
        parametros.put("RAZAO_SOCIAL", empresa.getRazaoSocial());
        parametros.put("NOME_SOFTWARE_HOUSE", "T2Ti.com");

        JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaDfc);
        InputStream ip = this.getClass().getResourceAsStream("/relatorios/Dfc.jasper");
        if (ip == null) {
            throw new Exception("Arquivo do relat??rio n??o dispon??vel.");
        }
        JasperPrint jp = JasperFillManager.fillReport(ip, parametros, jrbean);
        JasperViewer.viewReport(jp, false);
    }
}
