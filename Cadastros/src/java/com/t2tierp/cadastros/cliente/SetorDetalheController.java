package com.t2tierp.cadastros.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.SetorVO;
import com.t2tierp.padrao.cliente.Container;
import com.t2tierp.padrao.java.Constantes;
import javax.swing.JOptionPane;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.java.Consts;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description: Classe de controle da tela SetorDetalhe.</p>
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
public class SetorDetalheController extends FormController {

    private SetorDetalhe setorDetalhe = null;
    private String pk = null;
    private SetorGrid setorGrid = null;
    private String acaoServidor;

    public SetorDetalheController(SetorGrid setorGrid, String pk) {
        this.setorGrid = setorGrid;
        this.pk = pk;
        this.acaoServidor = "setorDetalheAction";
        setorDetalhe = new SetorDetalhe(this);
        setorDetalhe.setParentFrame(this.setorGrid);
        this.setorGrid.pushFrame(setorDetalhe);
        MDIFrame.add(setorDetalhe);

        if (pk != null) {
            setorDetalhe.getForm1().setMode(Consts.READONLY);
            setorDetalhe.getForm1().reload();
        } else {
            setorDetalhe.getForm1().setMode(Consts.INSERT);
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.LOAD, pk});
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");
        ((SetorVO) newPersistentObject).setEmpresa(empresa);

        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.INSERT, newPersistentObject});
    }

    @Override
    public void afterInsertData() {
        setorGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(setorDetalhe, "Dados salvos com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        return ClientUtils.getData(acaoServidor, new Object[]{Constantes.UPDATE, oldPersistentObject, persistentObject});
    }

    @Override
    public void afterEditData() {
        setorGrid.getGrid1().reloadData();
        JOptionPane.showMessageDialog(setorDetalhe, "Dados alterados com sucesso!", "Informacao do Sistema", JOptionPane.INFORMATION_MESSAGE);
    }
}
