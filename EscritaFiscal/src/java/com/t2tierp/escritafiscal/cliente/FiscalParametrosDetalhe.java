package com.t2tierp.escritafiscal.cliente;

import javax.swing.text.MaskFormatter;
import org.openswing.swing.mdi.client.InternalFrame;

/**
* <p>Title: T2Ti ERP</p>
* <p>Description: Tela FiscalParametrosDetalhe.</p>
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
public class FiscalParametrosDetalhe extends InternalFrame {

    public FiscalParametrosDetalhe(FiscalParametrosDetalheController controller) {
        initComponents();

        form1.setFormController(controller);

        formattedTextControl1.setEnabled(false);
        try {
            MaskFormatter mask = new MaskFormatter("##/####");
            mask.setValidCharacters("0123456789");
            formattedTextControl1.setFormatter(mask);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * @return the form1
     */
    public org.openswing.swing.form.client.Form getForm1() {
        return form1;
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
        editButton1 = new org.openswing.swing.client.EditButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        comboBoxControl8 = new org.openswing.swing.client.ComboBoxControl();
        jPanel2 = new javax.swing.JPanel();
        labelControl13 = new org.openswing.swing.client.LabelControl();
        comboBoxControl9 = new org.openswing.swing.client.ComboBoxControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        labelControl11 = new org.openswing.swing.client.LabelControl();
        comboBoxControl10 = new org.openswing.swing.client.ComboBoxControl();
        labelControl12 = new org.openswing.swing.client.LabelControl();
        labelControl14 = new org.openswing.swing.client.LabelControl();
        comboBoxControl13 = new org.openswing.swing.client.ComboBoxControl();
        labelControl15 = new org.openswing.swing.client.LabelControl();
        comboBoxControl11 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl14 = new org.openswing.swing.client.ComboBoxControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        jPanel3 = new javax.swing.JPanel();
        labelControl16 = new org.openswing.swing.client.LabelControl();
        comboBoxControl15 = new org.openswing.swing.client.ComboBoxControl();
        comboBoxControl16 = new org.openswing.swing.client.ComboBoxControl();
        labelControl17 = new org.openswing.swing.client.LabelControl();
        labelControl18 = new org.openswing.swing.client.LabelControl();
        comboBoxControl17 = new org.openswing.swing.client.ComboBoxControl();
        jPanel4 = new javax.swing.JPanel();
        labelControl19 = new org.openswing.swing.client.LabelControl();
        comboBoxControl18 = new org.openswing.swing.client.ComboBoxControl();
        jSeparator1 = new javax.swing.JSeparator();
        formattedTextControl1 = new org.openswing.swing.client.FormattedTextControl();

        setTitle("T2Ti ERP - Escritafiscal");
        setPreferredSize(new java.awt.Dimension(700, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Fiscal Parametros"));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jPanel1.add(editButton1);
        jPanel1.add(reloadButton1);
        jPanel1.add(saveButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        form1.setVOClassName("com.t2tierp.escritafiscal.java.FiscalParametrosVO");
        form1.setEditButton(editButton1);
        form1.setFunctionId("fiscalParametros");
        form1.setReloadButton(reloadButton1);
        form1.setSaveButton(saveButton1);
        form1.setLayout(new java.awt.GridBagLayout());

        labelControl7.setLabel("Vigencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl7, gridBagConstraints);

        labelControl8.setLabel("Descricao Vigencia:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl8, gridBagConstraints);

        textControl7.setAttributeName("descricaoVigencia");
        textControl7.setEnabled(false);
        textControl7.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(textControl7, gridBagConstraints);

        labelControl9.setLabel("Criterio Lancamento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        form1.add(labelControl9, gridBagConstraints);

        comboBoxControl8.setAttributeName("criterioLancamento");
        comboBoxControl8.setDomainId("criterioLancamentoFechamento");
        comboBoxControl8.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(comboBoxControl8, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Federal"));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        labelControl13.setLabel("Simples Codigo Acesso:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl13, gridBagConstraints);

        comboBoxControl9.setAttributeName("apuracao");
        comboBoxControl9.setDomainId("fiscalParametrosApuracao");
        comboBoxControl9.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl9, gridBagConstraints);

        labelControl10.setLabel("Apuracao:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl10, gridBagConstraints);

        labelControl11.setLabel("Microempreendedor Individual:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl11, gridBagConstraints);

        comboBoxControl10.setAttributeName("microempreeIndividual");
        comboBoxControl10.setDomainId("simnao");
        comboBoxControl10.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl10, gridBagConstraints);

        labelControl12.setLabel("Calculo Pis Cofins Efd:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl12, gridBagConstraints);

        labelControl14.setLabel("Tabela Simples:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl14, gridBagConstraints);

        comboBoxControl13.setAttributeName("simplesTabela");
        comboBoxControl13.setDomainId("simplesNacionalCabecalho");
        comboBoxControl13.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl13, gridBagConstraints);

        labelControl15.setLabel("Atividade Simples:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel2.add(labelControl15, gridBagConstraints);

        comboBoxControl11.setAttributeName("simplesAtividade");
        comboBoxControl11.setDomainId("fiscalParametrosSimplesAtividade");
        comboBoxControl11.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl11, gridBagConstraints);

        comboBoxControl14.setAttributeName("calcPisCofinsEfd");
        comboBoxControl14.setDomainId("fiscalParametrosCalculoPisCofins");
        comboBoxControl14.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(comboBoxControl14, gridBagConstraints);

        textControl8.setAttributeName("simplesCodigoAcesso");
        textControl8.setEnabled(false);
        textControl8.setMaxCharacters(100);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel2.add(textControl8, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        form1.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Estadual"));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        labelControl16.setLabel("Perfil Sped:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl16, gridBagConstraints);

        comboBoxControl15.setAttributeName("perfilSped");
        comboBoxControl15.setDomainId("SpedPerfil");
        comboBoxControl15.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(comboBoxControl15, gridBagConstraints);

        comboBoxControl16.setAttributeName("apuracaoConsolidada");
        comboBoxControl16.setDomainId("simnao");
        comboBoxControl16.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(comboBoxControl16, gridBagConstraints);

        labelControl17.setLabel("Apuracao Consolidada:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl17, gridBagConstraints);

        labelControl18.setLabel("Substituicao Tributaria:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel3.add(labelControl18, gridBagConstraints);

        comboBoxControl17.setAttributeName("substituicaoTributaria");
        comboBoxControl17.setDomainId("simnao");
        comboBoxControl17.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel3.add(comboBoxControl17, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        form1.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Municipal"));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        labelControl19.setLabel("Forma Calculo Iss:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 5);
        jPanel4.add(labelControl19, gridBagConstraints);

        comboBoxControl18.setAttributeName("formaCalculoIss");
        comboBoxControl18.setDomainId("fiscalParametrosFormaCalculoIss");
        comboBoxControl18.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        jPanel4.add(comboBoxControl18, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        form1.add(jPanel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        form1.add(jSeparator1, gridBagConstraints);

        formattedTextControl1.setAttributeName("vigencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        form1.add(formattedTextControl1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(form1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.ComboBoxControl comboBoxControl10;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl11;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl13;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl14;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl15;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl16;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl17;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl18;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl8;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl9;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.FormattedTextControl formattedTextControl1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl11;
    private org.openswing.swing.client.LabelControl labelControl12;
    private org.openswing.swing.client.LabelControl labelControl13;
    private org.openswing.swing.client.LabelControl labelControl14;
    private org.openswing.swing.client.LabelControl labelControl15;
    private org.openswing.swing.client.LabelControl labelControl16;
    private org.openswing.swing.client.LabelControl labelControl17;
    private org.openswing.swing.client.LabelControl labelControl18;
    private org.openswing.swing.client.LabelControl labelControl19;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    // End of variables declaration//GEN-END:variables

}
