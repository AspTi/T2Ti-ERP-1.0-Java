/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Janela para importar um produto.</p>
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

import com.t2tierp.pafecf.controller.ProdutoController;
import com.t2tierp.pafecf.infra.MenuFiscalAction;
import com.t2tierp.pafecf.infra.ProdutoColumnModel;
import com.t2tierp.pafecf.infra.ProdutoTableModel;
import com.t2tierp.pafecf.vo.ProdutoVO;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

public class ImportaProduto extends javax.swing.JDialog {

    private List<ProdutoVO> listaProduto = new ArrayList<ProdutoVO>();
    boolean cancelado = false;
    boolean produtoProprio = false;
    boolean produtoTerceiro = false;

    public ImportaProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configuraTela();
    }

    public ImportaProduto(java.awt.Frame parent, boolean modal, boolean produtoProprio, boolean produtoTerceiro) {
        super(parent, modal);
        initComponents();
        this.produtoProprio = produtoProprio;
        this.produtoTerceiro = produtoTerceiro;
        configuraTela();
    }

    private void configuraTela(){
        int r = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(0, 3));
        int g = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(4, 7));
        int b = Integer.valueOf(Caixa.configuracao.getCorJanelasInternas().substring(8, 11));

        //TODO : ?? necess??rio configurar a cor de cada Panel?
        panelPrincipal.setBackground(new Color(r, g, b));
        panelComponentes.setBackground(new Color(r, g, b));
        panelGrid.setBackground(new Color(r, g, b));
        panelLocaliza.setBackground(new Color(r, g, b));
        panelBotoes.setBackground(new Color(r, g, b));

        ProdutoController produtoControl = new ProdutoController();
        //TODO : O que pode dar errado nessa rotina?
        if (produtoProprio) {
            configuraGridProduto(produtoControl.produtoFiltro("a", "P"));
        } else if (produtoTerceiro) {
            configuraGridProduto(produtoControl.produtoFiltro("a", "T"));
        } else {
            configuraGridProduto(produtoControl.produtoFiltro("a"));
        }

        CancelaAction cancelaAction = new CancelaAction();
        botaoCancela.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelaAction");
        botaoCancela.getActionMap().put("cancelaAction", cancelaAction);

        ConfirmaAction confirmaAction = new ConfirmaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "confirmaAction");
        botaoConfirma.getActionMap().put("confirmaAction", confirmaAction);

        LocalizaAction localizaAction = new LocalizaAction();
        botaoConfirma.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "localizaAction");
        botaoConfirma.getActionMap().put("localizaAction", localizaAction);

        MenuFiscalAction menuFiscalAction = new MenuFiscalAction(this);
        editLocaliza.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), "menuFiscal");
        editLocaliza.getActionMap().put("menuFiscal", menuFiscalAction);

        //troca TAB por ENTER
        HashSet conj = new HashSet(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        conj.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);
        gridProduto.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, conj);

        editLocaliza.requestFocus();

        this.setPreferredSize(new Dimension(700, 430));
        this.pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelComponentes = new javax.swing.JPanel();
        panelGrid = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridProduto = new javax.swing.JTable();
        panelLocaliza = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        botaoLocaliza = new javax.swing.JButton();
        editLocaliza = new javax.swing.JTextField();
        panelBotoes = new javax.swing.JPanel();
        botaoConfirma = new javax.swing.JButton();
        botaoCancela = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Localiza e Importa Produto");
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        panelPrincipal.setLayout(new java.awt.GridBagLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/telas/telaLupa06.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        panelPrincipal.add(jLabel1, gridBagConstraints);

        panelComponentes.setLayout(new java.awt.GridBagLayout());

        panelGrid.setBackground(new Color(255,255,255,0));
        panelGrid.setBorder(javax.swing.BorderFactory.createTitledBorder("Rela????o de Produtos:"));
        panelGrid.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(452, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 200));

        gridProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(gridProduto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        panelGrid.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
        panelComponentes.add(panelGrid, gridBagConstraints);

        panelLocaliza.setBackground(new Color(255,255,255,0));
        panelLocaliza.setBorder(javax.swing.BorderFactory.createTitledBorder("Informe dados para localiza????o:"));
        panelLocaliza.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Procurar por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        panelLocaliza.add(jLabel2, gridBagConstraints);

        botaoLocaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/imgBotoes/botaoLocalizar.png"))); // NOI18N
        botaoLocaliza.setText("Localiza (F2)");
        botaoLocaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLocalizaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelLocaliza.add(botaoLocaliza, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        panelLocaliza.add(editLocaliza, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        panelComponentes.add(panelLocaliza, gridBagConstraints);

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

    private void botaoConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmaActionPerformed
        dispose();
}//GEN-LAST:event_botaoConfirmaActionPerformed

    private void botaoCancelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelaActionPerformed
        cancelado = true;
        dispose();
}//GEN-LAST:event_botaoCancelaActionPerformed

    private void botaoLocalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLocalizaActionPerformed
        localiza();
    }//GEN-LAST:event_botaoLocalizaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                ImportaProduto dialog = new ImportaProduto(new javax.swing.JFrame(), true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancela;
    private javax.swing.JButton botaoConfirma;
    private javax.swing.JButton botaoLocaliza;
    private javax.swing.JTextField editLocaliza;
    private javax.swing.JTable gridProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotoes;
    private javax.swing.JPanel panelComponentes;
    private javax.swing.JPanel panelGrid;
    private javax.swing.JPanel panelLocaliza;
    private javax.swing.JPanel panelPrincipal;
    // End of variables declaration//GEN-END:variables

    private class ConfirmaAction extends AbstractAction {

        public ConfirmaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    private class CancelaAction extends AbstractAction {

        public CancelaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            cancelado = true;
            dispose();
        }
    }

    private class LocalizaAction extends AbstractAction {

        public LocalizaAction() {
        }

        public void actionPerformed(ActionEvent e) {
            localiza();
        }
    }

    private void localiza() {
        ProdutoController produtoControl = new ProdutoController();
        if (produtoProprio) {
            listaProduto = produtoControl.produtoFiltro(editLocaliza.getText(), "P");
        } else if (produtoTerceiro) {
            listaProduto = produtoControl.produtoFiltro(editLocaliza.getText(), "T");
        } else {
            listaProduto = produtoControl.produtoFiltro(editLocaliza.getText());
        }
        if (listaProduto != null) {
            configuraGridProduto(listaProduto);
            gridProduto.setRowSelectionInterval(0, 0);
            gridProduto.requestFocus();
        } else {
            configuraGridProduto(new ArrayList<ProdutoVO>());
            JOptionPane.showMessageDialog(this, "Nenhum produto encontrado!", "Aviso do sistema", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void configuraGridProduto(List<ProdutoVO> listaProduto) {
        this.listaProduto = listaProduto;

        gridProduto.setModel(new ProdutoTableModel(listaProduto));
        gridProduto.setSelectionModel(new DefaultListSelectionModel() {

            public String toString() {
                return "gridProduto";
            }
        });

        gridProduto.setAutoCreateColumnsFromModel(false);
        gridProduto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        FontMetrics fm = gridProduto.getFontMetrics(gridProduto.getFont());
        gridProduto.setColumnModel(new ProdutoColumnModel(fm));
    }

    public String getGTIN() {
        if (gridProduto.getSelectedRow() != -1) {
            AbstractTableModel modelo = (AbstractTableModel) gridProduto.getModel();
            return ((String) modelo.getValueAt(gridProduto.getSelectedRow(), 0));
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    public ProdutoVO getProduto(){
        if (gridProduto.getSelectedRow() != -1) {
            ProdutoTableModel modelo = (ProdutoTableModel) gridProduto.getModel();
            return modelo.getValues(gridProduto.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado!", "Aviso do Sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
