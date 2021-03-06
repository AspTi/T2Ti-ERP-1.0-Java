/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EdicaoContratoDialog.java
 *
 * Created on 14/02/2013, 11:16:08
 */

package com.t2tierp.contratos.cliente;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Tela EdicaoContratoDialog.</p>
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
public class EdicaoTemplateDialog extends javax.swing.JDialog {

    /** Creates new form EdicaoContratoDialog */
    public EdicaoTemplateDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jTextPane1.setContentType("text/html");
        jTextPane1.setEditable(false);
        jTextPane1.setText("<html>\n  <head>\n  </head>\n  <body>\n\tFoi aberta uma inst??ncia do OpenOffice para edi????o do template do contrato.\n\t<br/>\n\t<br/>\n\n\tUtilize os seguintes termos (incluindo o caracter #) para substitui????o quando da gera????o do contrato:\n\t<br/>\n\t<br/>\n\tDados da Contratada\n\t<br/>\n\t<b>#CONTRATADA#</b> <br/>\n\t<b>#CNPJ_CONTRATADA#</b>  <br/> \n\t<b>#ENDERECO_CONTRATADA#</b>  <br/>\n\t<b>#CIDADE_CONTRATADA#</b>  <br/>\n\t<b>#UF_CONTRATADA#</b>  <br/>\n\t<b>#BAIRRO_CONTRATADA#</b>  <br/>\n\t<b>#CEP_CONTRATADA#</b>  <br/>\n\t\n\t<br/>\n\tDados do Contratante\n\t<br/>\n\t<b>#CONTRATANTE#</b>  <br/>\n\t<b>#CNPJ_CONTRATANTE#</b>  <br/>\n\t<b>#ENDERECO_CONTRATANTE#</b>  <br/>\n\t<b>#CIDADE_CONTRATANTE#</b>  <br/>\n\t<b>#UF_CONTRATANTE#</b>  <br/>\n\t<b>#BAIRRO_CONTRATANTE#</b>  <br/>\n\t<b>#CEP_CONTRATANTE#</b>  <br/>\n\t\n\t<br/>\n\tOutros\n\t<br/>\n\t<b>#VALOR_MENSAL#</b>  <br/>\n\t<b>#DATA_EXTENSO#</b>  <br/>\n\t<br/>\n\t<br/>\n\t<b>Obs.: Esta janela ser?? fechada automaticamente ap??s o fechamento do OpenOffice.</b>\n\t<br/>\n\t<br/>\n\t<b>Ap??s realizar as altera????es e fechar o OpenOffice, clique no bot??o \"Salvar Registro\".</b>\n  </body>\n</html>");
        jScrollPane1.setViewportView(jTextPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

}
