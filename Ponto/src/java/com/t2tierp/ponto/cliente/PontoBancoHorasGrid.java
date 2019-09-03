package com.t2tierp.ponto.cliente;

import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description: Tela PontoBancoHorasGrid.</p>
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
public class PontoBancoHorasGrid extends InternalFrame {

    public PontoBancoHorasGrid(PontoBancoHorasGridController controller) {
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
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        gridControl1 = new org.openswing.swing.client.GridControl();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        comboColumn5 = new org.openswing.swing.table.columns.client.ComboColumn();

        setTitle("T2Ti ERP - Ponto Eletrônico");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ponto Banco Horas"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(reloadButton1);
        jPanel1.add(navigatorBar1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        gridControl1.setFunctionId("pontoBancoHoras");
        gridControl1.setNavBar(navigatorBar1);
        gridControl1.setReloadButton(reloadButton1);
        gridControl1.setValueObjectClassName("com.t2tierp.ponto.java.PontoBancoHorasVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("colaborador.pessoa.nome");
        textColumn2.setColumnSortable(true);
        textColumn2.setHeaderColumnName("Colaborador");
        textColumn2.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        textColumn2.setPreferredWidth(300);
        gridControl1.getColumnContainer().add(textColumn2);

        dateColumn3.setColumnFilterable(true);
        dateColumn3.setColumnName("dataTrabalho");
        dateColumn3.setColumnSortable(true);
        dateColumn3.setHeaderColumnName("Data Trabalho");
        dateColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(dateColumn3);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("quantidade");
        textColumn4.setColumnSortable(true);
        textColumn4.setHeaderColumnName("Quantidade");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        gridControl1.getColumnContainer().add(textColumn4);

        comboColumn5.setColumnFilterable(true);
        comboColumn5.setColumnName("situacao");
        comboColumn5.setColumnSortable(true);
        comboColumn5.setDomainId("situacaoBancoHoras");
        comboColumn5.setHeaderColumnName("Situacao");
        comboColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        comboColumn5.setPreferredWidth(150);
        gridControl1.getColumnContainer().add(comboColumn5);

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
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn5;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.client.GridControl gridControl1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    // End of variables declaration//GEN-END:variables
}
