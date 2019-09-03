package com.t2tierp.contabilidade.cliente;

import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description: Tela ContabilLancamentoCabecalhoGrid.</p>
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
public class ContabilLancamentoCabecalhoGrid extends InternalFrame {

    public ContabilLancamentoCabecalhoGrid(ContabilLancamentoCabecalhoGridController controller) {
        initComponents();
        gridControl1.setController(controller);
        gridControl1.setGridDataLocator(controller);
    }

    public GridControl getGrid1() {
        return gridControl1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn7 = new org.openswing.swing.table.columns.client.ComboColumn();

        setTitle("T2Ti ERP - Contabilidade");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Contabil Lancamento Cabecalho"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(insertButton1);
        jPanel1.add(deleteButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton1);
        gridControl1.setValueObjectClassName("com.t2tierp.contabilidade.java.ContabilLancamentoCabecalhoVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("contabilLote.descricao");
        textColumn3.setHeaderColumnName("Lote");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn3.setPreferredWidth(200);
        gridControl1.getColumnContainer().add(textColumn3);

        dateColumn4.setColumnName("dataLancamento");
        dateColumn4.setHeaderColumnName("Data Lancamento");
        dateColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(dateColumn4);

        dateColumn5.setColumnName("dataInclusao");
        dateColumn5.setHeaderColumnName("Data Inclusao");
        dateColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(dateColumn5);

        comboColumn1.setColumnName("tipo");
        comboColumn1.setDomainId("tipoLancamentoProgramado");
        comboColumn1.setHeaderColumnName("Tipo");
        comboColumn1.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        comboColumn1.setPreferredWidth(150);
        gridControl1.getColumnContainer().add(comboColumn1);

        comboColumn7.setColumnName("liberado");
        comboColumn7.setDomainId("simnao");
        comboColumn7.setHeaderColumnName("Liberado");
        comboColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(comboColumn7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(gridControl1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn7;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    // End of variables declaration//GEN-END:variables
}
