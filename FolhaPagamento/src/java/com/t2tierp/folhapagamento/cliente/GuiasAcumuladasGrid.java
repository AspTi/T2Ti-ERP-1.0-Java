package com.t2tierp.folhapagamento.cliente;

import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.InternalFrame;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description: Tela GuiasAcumuladasGrid.</p>
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
public class GuiasAcumuladasGrid extends InternalFrame {

    public GuiasAcumuladasGrid(GuiasAcumuladasGridController controller) {
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
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn5 = new org.openswing.swing.table.columns.client.DecimalColumn();
        decimalColumn6 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn7 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        integerColumn9 = new org.openswing.swing.table.columns.client.IntegerColumn();
        decimalColumn10 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn11 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        decimalColumn13 = new org.openswing.swing.table.columns.client.DecimalColumn();
        dateColumn14 = new org.openswing.swing.table.columns.client.DateColumn();

        setTitle("T2Ti ERP - Folha de Pagamento");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Guias Acumuladas"));
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
        gridControl1.setValueObjectClassName("com.t2tierp.folhapagamento.java.GuiasAcumuladasVO");
        gridControl1.getColumnContainer().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        textColumn3.setColumnName("gpsTipo");
        textColumn3.setHeaderColumnName("Gps Tipo");
        textColumn3.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(textColumn3);

        textColumn4.setColumnName("gpsCompetencia");
        textColumn4.setHeaderColumnName("Gps Competencia");
        textColumn4.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(textColumn4);

        decimalColumn5.setColumnName("gpsValorInss");
        decimalColumn5.setHeaderColumnName("Gps Valor Inss");
        decimalColumn5.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(decimalColumn5);

        decimalColumn6.setColumnName("gpsValorOutrasEnt");
        decimalColumn6.setHeaderColumnName("Gps Valor Outras Ent");
        decimalColumn6.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(decimalColumn6);

        dateColumn7.setColumnName("gpsDataPagamento");
        dateColumn7.setHeaderColumnName("Gps Data Pagamento");
        dateColumn7.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(dateColumn7);

        textColumn8.setColumnName("irrfCompetencia");
        textColumn8.setHeaderColumnName("Irrf Competencia");
        textColumn8.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(textColumn8);

        integerColumn9.setColumnName("irrfCodigoRecolhimento");
        integerColumn9.setHeaderColumnName("Irrf Codigo Recolhimento");
        integerColumn9.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(integerColumn9);

        decimalColumn10.setColumnName("irrfValorAcumulado");
        decimalColumn10.setHeaderColumnName("Irrf Valor Acumulado");
        decimalColumn10.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(decimalColumn10);

        dateColumn11.setColumnName("irrfDataPagamento");
        dateColumn11.setHeaderColumnName("Irrf Data Pagamento");
        dateColumn11.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(dateColumn11);

        textColumn12.setColumnName("pisCompetencia");
        textColumn12.setHeaderColumnName("Pis Competencia");
        textColumn12.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(textColumn12);

        decimalColumn13.setColumnName("pisValorAcumulado");
        decimalColumn13.setHeaderColumnName("Pis Valor Acumulado");
        decimalColumn13.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(decimalColumn13);

        dateColumn14.setColumnName("pisDataPagamento");
        dateColumn14.setHeaderColumnName("Pis Data Pagamento");
        dateColumn14.setHeaderFont(new java.awt.Font("Arial", 1, 11));
        gridControl1.getColumnContainer().add(dateColumn14);

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
    private org.openswing.swing.table.columns.client.DateColumn dateColumn11;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn14;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn7;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn10;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn13;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn5;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn6;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn9;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    // End of variables declaration//GEN-END:variables
}
