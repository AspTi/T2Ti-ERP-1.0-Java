package com.t2tierp.vendas.cliente;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.vendas.java.VendaOrcamentoCabecalhoVO;
import com.t2tierp.vendas.java.VendaOrcamentoDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe de controle da tela VendaOrcamentoDetalhe.</p>
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
public class VendaOrcamentoDetalheController extends FormController {

    private VendaOrcamentoDetalhe vendaOrcamentoDetalhe = null;
    private String pk = null;
    private VendaOrcamentoGrid vendaOrcamentoGrid = null;
    private String acaoServidor;

    public VendaOrcamentoDetalheController(VendaOrcamentoGrid vendaOrcamentoGrid, String pk) {
        this.vendaOrcamentoGrid = vendaOrcamentoGrid;
        this.pk = pk;
        this.acaoServidor = "vendaOrcamentoDetalheAction";
        vendaOrcamentoDetalhe = new VendaOrcamentoDetalhe(this);
        vendaOrcamentoDetalhe.setParentFrame(this.vendaOrcamentoGrid);
        this.vendaOrcamentoGrid.pushFrame(vendaOrcamentoDetalhe);
        MDIFrame.add(vendaOrcamentoDetalhe);

        if (pk != null) {
            vendaOrcamentoDetalhe.getForm1().setMode(Consts.READONLY);
            vendaOrcamentoDetalhe.getForm1().reload();
        } else {
            vendaOrcamentoDetalhe.getForm1().setMode(Consts.INSERT);
            vendaOrcamentoDetalhe.getGridControl1().reloadData();
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public void loadDataCompleted(boolean error) {
        vendaOrcamentoDetalhe.getGridController().setPk(pk);
        vendaOrcamentoDetalhe.getGridControl1().reloadData();
    }

    @Override
    public boolean beforeInsertData(Form form) {
        atualizaTotais();
        return true;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        List<VendaOrcamentoDetalheVO> orcamentoDetalhe = vendaOrcamentoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (orcamentoDetalhe.isEmpty()) {
            return new ErrorResponse("N??o h?? produtos no or??amento.");
        }

        ((VendaOrcamentoCabecalhoVO) newPersistentObject).setSituacao("D");

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject, orcamentoDetalhe});
    }

    @Override
    public void afterInsertData() {
        vendaOrcamentoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaOrcamentoDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public boolean beforeEditData(Form form) {
        atualizaTotais();
        return true;
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        String situacao = ((VendaOrcamentoCabecalhoVO) persistentObject).getSituacao();
        if (!situacao.equals("D")){
            String mensagem = "Este registro n??o pode ser alterado.\n";
            if (situacao.equals("P")){
                mensagem += "Situa????o: Em Produ????o";
            }
            if (situacao.equals("X")){
                mensagem += "Situa????o: Em Expedi????o";
            }
            if (situacao.equals("F")){
                mensagem += "Situa????o: Faturado";
            }
            if (situacao.equals("E")){
                mensagem += "Situa????o: Entregue";
            }
            return new ErrorResponse(mensagem);
        }
        List<VendaOrcamentoDetalheVO> orcamentoDetalhe = vendaOrcamentoDetalhe.getGridControl1().getVOListTableModel().getDataVector();

        if (orcamentoDetalhe.isEmpty()) {
            return new ErrorResponse("N??o h?? produtos no or??amento.");
        }

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject, orcamentoDetalhe});
    }

    @Override
    public void afterEditData() {
        vendaOrcamentoGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(vendaOrcamentoDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    public void atualizaTotais() {
        VendaOrcamentoCabecalhoVO orcamentoCabecalho = (VendaOrcamentoCabecalhoVO) vendaOrcamentoDetalhe.getForm1().getVOModel().getValueObject();
        if (orcamentoCabecalho.getValorSubtotal() != null) {
            if (orcamentoCabecalho.getTaxaDesconto() != null) {
                orcamentoCabecalho.setValorDesconto(orcamentoCabecalho.getValorSubtotal().multiply(orcamentoCabecalho.getTaxaDesconto().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                orcamentoCabecalho.setValorTotal(orcamentoCabecalho.getValorSubtotal().subtract(orcamentoCabecalho.getValorDesconto()));
            }
            if (orcamentoCabecalho.getValorFrete() != null) {
                if (orcamentoCabecalho.getValorTotal() == null) {
                    orcamentoCabecalho.setValorTotal(orcamentoCabecalho.getValorSubtotal());
                }
                orcamentoCabecalho.setValorTotal(orcamentoCabecalho.getValorTotal().add(orcamentoCabecalho.getValorFrete()));
            }
        }
        vendaOrcamentoDetalhe.getForm1().pull();
    }
}
