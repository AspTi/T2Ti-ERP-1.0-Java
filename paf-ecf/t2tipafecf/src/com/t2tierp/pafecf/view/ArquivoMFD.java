/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Janela do Arquivo MFD.</p>
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
 * @author Albert Eije (T2Ti.COM)
 * @version 1.0
 */
package com.t2tierp.pafecf.view;

import com.t2tierp.pafecf.controller.RegistroRController;
import com.t2tierp.pafecf.infra.Biblioteca;
import com.t2tierp.pafecf.infra.Ecf;
import com.t2tierp.pafecf.infra.MenuFiscalAction;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class ArquivoMFD extends javax.swing.JDialog {

    public ArquivoMFD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        defineFormatoData();

        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        //TODO : ?? necess??rio configurar a cor de cada Panel?
        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelFiltro.setBackground(new Color(r, g, b));
        panelPeriodo.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));
        panelCOO.setBackground(new Color(r, g, b));

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        panelPrincipal.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        panelPrincipal.getActionMap().put("menuFiscal", menuFiscalAction);

        this.setPreferredSize(new Dimension(530, 200));
        this.pack();
    }

    private void defineFormatoData() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            DefaultFormatterFactory formatter = new DefaultFormatterFactory(mascara);
            dataInicial.setFormatterFactory(formatter);
            dataFinal.setFormatterFactory(formatter);
        } catch (ParseException ex) {
            Logger.getLogger(ArquivoMFD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelFiltro = new javax.swing.JPanel();
        radioPeriodo = new javax.swing.JRadioButton();
        radioCOO = new javax.swing.JRadioButton();
        panelPeriodo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dataInicial = new javax.swing.JFormattedTextField();
        dataFinal = new javax.swing.JFormattedTextField();
        panelCOO = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        primeiroCOO = new javax.swing.JFormattedTextField();
        ultimoCOO = new javax.swing.JFormattedTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Arquivo MFD - Mem??ria Fita Detalhe");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaRegistradora01.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelFiltro.setBackground(new Color(255,255,255,0));
        panelFiltro.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Filtro:"));
        panelFiltro.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(radioPeriodo);
        radioPeriodo.setSelected(true);
        radioPeriodo.setText("Per??odo de Data");
        radioPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPeriodoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panelFiltro.add(radioPeriodo, gridBagConstraints);

        buttonGroup1.add(radioCOO);
        radioCOO.setText("Intervalo de COO");
        radioCOO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCOOActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panelFiltro.add(radioCOO, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelFiltro, gridBagConstraints);

        panelPeriodo.setBackground(new Color(255,255,255,0));
        panelPeriodo.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Data inicial:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Data final:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(dataInicial, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelPeriodo.add(dataFinal, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelPeriodo, gridBagConstraints);

        panelCOO.setBackground(new Color(255,255,255,0));
        panelCOO.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Primeiro:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelCOO.add(jLabel4, gridBagConstraints);

        jLabel5.setText("??ltimo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelCOO.add(jLabel5, gridBagConstraints);

        primeiroCOO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        primeiroCOO.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 0);
        panelCOO.add(primeiroCOO, gridBagConstraints);

        ultimoCOO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        ultimoCOO.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelCOO.add(ultimoCOO, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelCOO, gridBagConstraints);

        panelBotoes.setBackground(new Color(255,255,255,0));
        panelBotoes.setLayout(new java.awt.GridBagLayout());

        botaoConfirma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoConfirmar.png"))); // NOI18N
        botaoConfirma.setText("Confirma (F12)");
        botaoConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        panelBotoes.add(botaoConfirma, gridBagConstraints);

        botaoCancela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoCancelar.png"))); // NOI18N
        botaoCancela.setText("Cancela (ESC)");
        botaoCancela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 5);
        panelBotoes.add(botaoCancela, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
        panelComponentes.add(panelBotoes, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        panelPrincipal.add(panelComponentes, gridBagConstraints);

        getContentPane().add(panelPrincipal, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPeriodoActionPerformed
        primeiroCOO.setEnabled(false);
        ultimoCOO.setEnabled(false);
        dataInicial.setEnabled(true);
        dataFinal.setEnabled(true);
    }//GEN-LAST:event_radioPeriodoActionPerformed

    private void radioCOOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCOOActionPerformed
        primeiroCOO.setEnabled(true);
        ultimoCOO.setEnabled(true);
        dataInicial.setEnabled(false);
        dataFinal.setEnabled(false);
    }//GEN-LAST:event_radioCOOActionPerformed

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        validaPeriodo();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ArquivoMFD dialog = new ArquivoMFD(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField dataFinal;
    private javax.swing.JFormattedTextField dataInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelCOO;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelPeriodo;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JFormattedTextField primeiroCOO;
    private javax.swing.JRadioButton radioCOO;
    private javax.swing.JRadioButton radioPeriodo;
    private javax.swing.JFormattedTextField ultimoCOO;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            validaPeriodo();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public void validaPeriodo() {
        if (radioPeriodo.isSelected()) {
            try {
                dataInicial.commitEdit();
                dataFinal.commitEdit();
                if (!Biblioteca.isDataValida(dataInicial.getText())) {
                    JOptionPane.showMessageDialog(this, "Data inicial inv??lida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    dataInicial.requestFocus();
                } else if (!Biblioteca.isDataValida(dataFinal.getText())) {
                    JOptionPane.showMessageDialog(this, "Data final inv??lida!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    dataFinal.requestFocus();
                } else {
                    confirma();
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Problemas com os valores informados!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                dataInicial.requestFocus();
            }
        } else if (radioCOO.isSelected()) {
            try {
                primeiroCOO.commitEdit();
                ultimoCOO.commitEdit();

                if ((Long) primeiroCOO.getValue() <= 0) {
                    JOptionPane.showMessageDialog(this, "Primeiro COO inv??lido!!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    primeiroCOO.requestFocus();
                } else if ((Long) ultimoCOO.getValue() <= 0) {
                    JOptionPane.showMessageDialog(this, "??ltimo COO inv??lido!!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    ultimoCOO.requestFocus();
                } else if ((Long) ultimoCOO.getValue() < (Long) primeiroCOO.getValue()) {
                    JOptionPane.showMessageDialog(this, "??ltimo COO menor que o primeiro COO!!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                    ultimoCOO.requestFocus();
                } else {
                    confirma();
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Problemas com os valores informados!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
                primeiroCOO.requestFocus();
            }
        }
    }

    private void confirma() {
        String[] opcoes = {"Sim", "N??o"};
        int escolha = JOptionPane.showOptionDialog(null, "Deseja gerar o arquivo da MFD - Mem??ria Fita Detalhe?", "Pergunta do Sistema",
                JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, opcoes, null);
        if (escolha == JOptionPane.YES_OPTION) {
            File file = new File(System.getProperty("user.dir") + "\\binario\\DarumaFrameWork.xml");
            if (file.exists()) {
                file.delete();
            }
            //por data
            if (radioPeriodo.isSelected()) {
                //TODO : DLL sem suporte ao m??todo. Como fazer?
                //TODO : Tem que usar a DLL do fabricante? E o Linux?
                //Caixa.aCBrECF.arquivoMFD(dataInicial.getValue(),dataFinal.getValue(),"ArquivoMDF.txt");
                //Caixa.aCBrECF.arquivoMFD("01/03/2011", "21/03/2011", "ArquivoMDF.txt");
                try {
                    SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataIni = formataData.parse((String) dataInicial.getValue());
                    Date dataFim = formataData.parse((String) dataFinal.getValue());
                    Date dataEcf = Ecf.getDataHoraEcf();

                    RegistroRController registroRControl = new RegistroRController();
                    if (registroRControl.verificaMovimentoPeriodo(dataIni, dataFim)) {
                        Calendar calDataEcf = Calendar.getInstance();
                        calDataEcf.setTime(dataEcf);
                        calDataEcf.set(Calendar.HOUR_OF_DAY, 0);
                        calDataEcf.set(Calendar.MINUTE, 0);
                        calDataEcf.set(Calendar.SECOND, 0);

                        String strDataIni = formataData.format(dataIni);
                        String strDataFim = formataData.format(dataFim);
                        if (dataFim.getTime() > calDataEcf.getTime().getTime()) {
                            JOptionPane.showMessageDialog(this, "Data final precisa ser menor ou igual a " + formataData.format(dataEcf), "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            String comando =
                                    System.getProperty("user.dir") + "\\binario\\binario.exe "
                                    + "ArquivoMFDData "
                                    + Caixa.configuracao.getIdEmpresa() + " "
                                    + "0 "
                                    + "0 "
                                    + Caixa.configuracao.getImpressoraVO().getId() + " "
                                    + Caixa.configuracao.getPortaECF() + " "
                                    + Caixa.configuracao.getTimeOutECF() + " "
                                    + Caixa.configuracao.getIntervaloECF() + " "
                                    + Caixa.configuracao.getImpressoraVO().getModeloACBr() + " "
                                    + strDataIni + " "
                                    + strDataFim;
                            try {
                                Caixa.aCBrECF.desativar();
                                Caixa.setTextoMensagem("Aguarde! Gerando arquivo Arquivo MFD...");
                                Mensagem msg = new Mensagem(this, true, true, 1000);
                                msg.setMensagem("Arquivo MFD");
                                msg.setLocationRelativeTo(null);
                                msg.setVisible(true);

                                Process p = Runtime.getRuntime().exec(comando);
                                p.waitFor();

                                Caixa.setTextoMensagem("Arquivo MFD");
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                            } finally {
                                try {
                                    Caixa.aCBrECF.ativar();
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "N??o existe movimento para o per??odo informado!", "Aviso do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }

            } //por reducao
            else if (radioCOO.isSelected()) {
                //TODO : DLL sem suporte ao m??todo. Como fazer?
                //Caixa.ACBrECF.arquivoMFD_DLL(primeiroCOO.getText(),ultimoCOO.getText(),"ArquivoMDF.txt");
                try {
                    //TODO : DLL sem suporte ao m??todo. Como fazer?
                    //Caixa.ACBrECF.leituraMemoriaFiscal(StrToInt(primeiroCRZ.getText()),StrToInt(ultimoCRZ.getText()),true);
                    int primeiro = ((Long) primeiroCOO.getValue()).intValue();
                    int ultimo = ((Long) ultimoCOO.getValue()).intValue();

                    if (ultimo > Integer.valueOf(Caixa.aCBrECF.getNumCOO())) {
                        JOptionPane.showMessageDialog(this, "??ltimo COO fora da faixa!", "Aviso do Sistema", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String comando =
                                System.getProperty("user.dir") + "\\binario\\binario.exe "
                                + "ArquivoMFDCOO "
                                + Caixa.configuracao.getIdEmpresa() + " "
                                + "0 "
                                + "0 "
                                + Caixa.configuracao.getImpressoraVO().getId() + " "
                                + Caixa.configuracao.getPortaECF() + " "
                                + Caixa.configuracao.getTimeOutECF() + " "
                                + Caixa.configuracao.getIntervaloECF() + " "
                                + Caixa.configuracao.getImpressoraVO().getModeloACBr() + " "
                                + primeiro + " "
                                + ultimo;
                        try {
                            Caixa.aCBrECF.desativar();
                            Caixa.setTextoMensagem("Aguarde! Gerando arquivo Arquivo MFD...");
                            Mensagem msg = new Mensagem(this, true, true, 1000);
                            msg.setMensagem("Arquivo MFD");
                            msg.setLocationRelativeTo(null);
                            msg.setVisible(true);

                            Process p = Runtime.getRuntime().exec(comando);
                            p.waitFor();
                            Caixa.setTextoMensagem("Arquivo MFD");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                        } finally {
                            try {
                                Caixa.aCBrECF.ativar();
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (Throwable t) {
                    JOptionPane.showMessageDialog(rootPane, t.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
