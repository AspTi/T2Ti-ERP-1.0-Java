package com.t2tierp.padrao.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.cadastros.java.UsuarioVO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.*;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.util.client.*;
import org.openswing.swing.permissions.client.*;
import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.domains.java.Domain;
import org.openswing.swing.internationalization.java.*;
import org.openswing.swing.message.receive.java.*;
import org.openswing.swing.permissions.java.ButtonsAuthorizations;

public class Menu implements MDIController, LoginController {

    private Fachada fachadaCliente = null;
    private Hashtable domains = new Hashtable();
    private String nomeUsuario = null;

    public Menu() {
        ClientUtils.setObjectSender(new HessianObjectSender());
        fachadaCliente = new Fachada();
        LoginDialog d = new LoginDialog(null, false, this);
    }

    /**
     * @return client facade, invoked by the MDI Frame tree/menu
     */
    public ClientFacade getClientFacade() {
        return fachadaCliente;
    }

    /**
     * Method used to destroy application.
     */
    public void stopApplication() {
        ClientUtils.getData("closeApplication", Boolean.TRUE);
        System.exit(0);
    }

    /**
     * Defines if application functions must be viewed inside a tree panel of MDI Frame.
     * @return <code>true</code> if application functions must be viewed inside a tree panel of MDI Frame, <code>false</code> no tree is viewed
     */
    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    /**
     * Defines if application functions must be viewed in the menubar of MDI Frame.
     * @return <code>true</code> if application functions must be viewed in the menubar of MDI Frame, <code>false</code> otherwise
     */
    public boolean viewFunctionsInMenuBar() {
        return true;
    }

    /**
     * @return <code>true</code> if the MDI frame must show a login menu in the menubar, <code>false</code> no login menu item will be added
     */
    public boolean viewLoginInMenuBar() {
        return true;
    }

    /**
     * @return application title
     */
    public String getMDIFrameTitle() {
        return "T2Ti ERP";
    }

    /**
     * @return text to view in the about dialog window
     */
    public String getAboutText() {
        return "Aplica????o: T2Ti ERP\n"
                + "\n"
                + "The MIT License\n"
                + "Copyright: Copyright (C) 2011 T2Ti.COM\n"
                + "\n"
                + "Permission is hereby granted, free of charge, to any person\n"
                + "obtaining a copy of this software and associated documentation\n"
                + "files (the 'Software'), to deal in the Software without\n"
                + "restriction, including without limitation the rights to use,\n"
                + "copy, modify, merge, publish, distribute, sublicense, and/or sell\n"
                + "copies of the Software, and to permit persons to whom the\n"
                + "Software is furnished to do so, subject to the following\n"
                + "conditions:\n"
                + "\n"
                + "The above copyright notice and this permission notice shall be\n"
                + "included in all copies or substantial portions of the Software.\n"
                + "\n"
                + "THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,\n"
                + "EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES\n"
                + "OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND\n"
                + "NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT\n"
                + "HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,\n"
                + "WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING\n"
                + "FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR\n"
                + "OTHER DEALINGS IN THE SOFTWARE.\n";
    }

    /**
     * @return image name to view in the about dialog window
     */
    public String getAboutImage() {
        return "about.jpg";
    }

    /**
     * @param parentFrame parent frame
     * @return a dialog window to logon the application; the method can return null if viewLoginInMenuBar returns false
     */
    public JDialog viewLoginDialog(JFrame parentFrame) {
        JDialog d = new LoginDialog(parentFrame, true, this);
        return d;
    }

    /**
     * @return maximum number of failed login
     */
    public int getMaxAttempts() {
        return 3;
    }

    /**
     * Method called by MDI Frame to authenticate the user.
     * @param loginInfo login information, like nomeUsuario, password, ...
     * @return <code>true</code> if user is correcly authenticated, <code>false</code> otherwise
     */
    public boolean authenticateUser(Map loginInfo) throws Exception {
        nomeUsuario = (String) loginInfo.get("username");
        String password = (String) loginInfo.get("password");

        if (nomeUsuario == null || password == null) {
            return false;
        }

        nomeUsuario = nomeUsuario.toUpperCase();
        password = password.toUpperCase();
        loginInfo.put("username", nomeUsuario);
        loginInfo.put("password", password);

        Response response = ClientUtils.getData("login", new String[]{nomeUsuario, password});

        if (response.isError()) {
            throw new Exception(response.getErrorMessage());
        }

        String languageId = ((TextResponse) response).getMessage();

        response = ClientUtils.getData("containerAction", new String[]{nomeUsuario, password});
        if (response.isError()) {
            throw new Exception(response.getErrorMessage());
        }

        UsuarioVO usuarioVO = (UsuarioVO) ((VOResponse) response).getVo();
        Container.setContainer(usuarioVO);

        response = ClientUtils.getData("getButtonAuthorizations", new Object[0]);
        if (response.isError()) {
            throw new Exception(response.getErrorMessage());
        }

        ButtonsAuthorizations buttonsAuthorizations = (ButtonsAuthorizations) ((VOResponse) response).getVo();

        Hashtable xmlFiles = new Hashtable();
        xmlFiles.put("EN", "Resources_en.xml");
        xmlFiles.put("IT", "Resources_it.xml");
        xmlFiles.put("ES", "Resources_es.xml");
        xmlFiles.put("PT_BR", "Resources_ptBR.xml");

        ClientSettings clientSettings = new ClientSettings(
                new XMLResourcesFactory(xmlFiles, true),
                domains,
                buttonsAuthorizations,
                false);

        ClientSettings.PERC_TREE_FOLDER = "folder3.gif";
        ClientSettings.BACKGROUND = "background4.jpg";
        ClientSettings.TREE_BACK = "treeback2.jpg";
        ClientSettings.FILTER_PANEL_ON_GRID = true;
        ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.birosoft.liquid.LiquidLookAndFeel";
        com.birosoft.liquid.LiquidLookAndFeel.setLiquidDecorations(true);
        ClientSettings.MAX_NR_OF_LOOPS_IN_ANALYZE_VO = 3;

        ClientSettings.getInstance().setLanguage(languageId);

        return true;
    }

    public static void main(String[] argv) {
        new Menu();
    }

    /**
     * Method called after MDI creation.
     */
    public void afterMDIcreation(MDIFrame frame) {
        GenericStatusPanel userPanel = new GenericStatusPanel();
        userPanel.setColumns(12);
        MDIFrame.addStatusComponent(userPanel);
        userPanel.setText(nomeUsuario);
        MDIFrame.addStatusComponent(new Clock());

        JButton botaoRelatorio = new JButton("Imprimir Relat??rio");
        frame.addButtonToToolBar(botaoRelatorio);
        botaoRelatorio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String janela;
                    if (MDIFrame.getSelectedFrame() != null) {

                        janela = MDIFrame.getSelectedFrame().getClass().getName();
                        String arquivoRelatorio = janela.substring(janela.lastIndexOf(".") + 1, janela.length() - 4);

                        if (!janela.endsWith("Grid")) {
                            JOptionPane.showMessageDialog(null, "Relat??rio n??o dispon??vel para a janela selecionada.", "Informa????o do Sistema", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Method metodo = MDIFrame.getSelectedFrame().getClass().getDeclaredMethod("getGrid1");
                            GridControl grid = (GridControl) metodo.invoke(MDIFrame.getSelectedFrame());

                            if (grid != null) {
                                List listaRelatorio = grid.getVOListTableModel().getDataVector();
                                EmpresaVO empresa = (EmpresaVO) Container.getContainer().get("empresa");

                                ImageIcon logoEmpresa = new ImageIcon(this.getClass().getResource("/images/t2ti.jpg"));
                                Map parametros = new HashMap();
                                parametros.put("LOGO_EMPRESA", logoEmpresa.getImage());
                                parametros.put("NOME_FANTASIA", empresa.getNomeFantasia());
                                parametros.put("RAZAO_SOCIAL", empresa.getRazaoSocial());
                                parametros.put("NOME_SOFTWARE_HOUSE", "T2Ti.com");

                                JRBeanCollectionDataSource jrbean = new JRBeanCollectionDataSource(listaRelatorio);
                                InputStream ip = this.getClass().getResourceAsStream("/relatorios/" + arquivoRelatorio + ".jasper");
                                if (ip == null) {
                                    throw new Exception("Arquivo do relat??rio n??o dispon??vel.");
                                }
                                JasperPrint jp = JasperFillManager.fillReport(ip, parametros, jrbean);
                                JasperViewer.viewReport(jp, false);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhuma janela selecionada.", "Informa????o do Sistema", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao gerar o relat??rio.\n" + ex.getMessage(), "Erro do Sistema", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * @see JFrame getExtendedState method
     */
    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    /**
     * Method called by LoginDialog to notify the sucessful login.
     * @param loginInfo login information, like nomeUsuario, password, ...
     */
    public void loginSuccessful(Map loginInfo) {

        Domain tipoPessoa = new Domain("tipoPessoa");
        tipoPessoa.addDomainPair("F", "F??sica");
        tipoPessoa.addDomainPair("J", "Jur??dica");

        Domain tipoSangue = new Domain("tipoSangue");
        tipoSangue.addDomainPair("A+", "A+");
        tipoSangue.addDomainPair("B+", "B+");
        tipoSangue.addDomainPair("O+", "O+");
        tipoSangue.addDomainPair("AB+", "AB+");
        tipoSangue.addDomainPair("A-", "A-");
        tipoSangue.addDomainPair("B-", "B-");
        tipoSangue.addDomainPair("AB-", "AB-");
        tipoSangue.addDomainPair("O-", "O-");

        Domain dominioSexo = new Domain("sexo");
        dominioSexo.addDomainPair("M", "Masculino");
        dominioSexo.addDomainPair("F", "Feminino");

        Domain dominioRacaCor = new Domain("racaCor");
        dominioRacaCor.addDomainPair("B", "Branco");
        dominioRacaCor.addDomainPair("N", "Negro");
        dominioRacaCor.addDomainPair("P", "Pardo");
        dominioRacaCor.addDomainPair("I", "Indio");

        Domain dominioSimNao = new Domain("simnao");
        dominioSimNao.addDomainPair("S", "Sim");
        dominioSimNao.addDomainPair("N", "N??o");

        Domain dominioNaoSim = new Domain("naosim");
        dominioNaoSim.addDomainPair("N", "N??o");
        dominioNaoSim.addDomainPair("S", "Sim");

        Domain dominioMes = new Domain("mes");
        dominioMes.addDomainPair("01", "Janeiro");
        dominioMes.addDomainPair("02", "Fevereiro");
        dominioMes.addDomainPair("03", "Mar??o");
        dominioMes.addDomainPair("04", "Abril");
        dominioMes.addDomainPair("05", "Maio");
        dominioMes.addDomainPair("06", "Junho");
        dominioMes.addDomainPair("07", "Julho");
        dominioMes.addDomainPair("08", "Agosto");
        dominioMes.addDomainPair("09", "Setembro");
        dominioMes.addDomainPair("10", "Outubro");
        dominioMes.addDomainPair("11", "Novembro");
        dominioMes.addDomainPair("12", "Dezembro");

        Domain dominioTipoCentroResultado = new Domain("tipoCentroResultado");
        dominioTipoCentroResultado.addDomainPair("A", "Anal??tico");
        dominioTipoCentroResultado.addDomainPair("S", "Sint??tico");

        //dominios NF-e
        Domain dominioStatusNota = new Domain("statusNotaFiscalEletronica");
        dominioStatusNota.addDomainPair("0", "0 - Em Edi????o");
        dominioStatusNota.addDomainPair("1", "1 - Salva");
        dominioStatusNota.addDomainPair("2", "2 - Validada");
        dominioStatusNota.addDomainPair("3", "3 - Assinada");
        dominioStatusNota.addDomainPair("4", "4 - Enviada");
        dominioStatusNota.addDomainPair("5", "5 - Autorizada");
        dominioStatusNota.addDomainPair("6", "6 - Cancelada");

        Domain dominioTipoOperacao = new Domain("tipoOperacao");
        dominioTipoOperacao.addDomainPair("0", "0 - Entrada");
        dominioTipoOperacao.addDomainPair("1", "1 - Sa??da");

        Domain dominioFormaPagamento = new Domain("formaPagamento");
        dominioFormaPagamento.addDomainPair("0", "0 - A Vista");
        dominioFormaPagamento.addDomainPair("1", "1 - A Prazo");
        dominioFormaPagamento.addDomainPair("2", "2 - Outros");

        Domain dominioFormaEmissao = new Domain("formaEmissao");
        dominioFormaEmissao.addDomainPair("1", "1 - Normal");
        dominioFormaEmissao.addDomainPair("2", "2 - Contig??ncia");
        dominioFormaEmissao.addDomainPair("3", "3 - Conting??ncia SCAN");
        dominioFormaEmissao.addDomainPair("4", "4 - Conting??ncia DPEC");
        dominioFormaEmissao.addDomainPair("5", "5 - Conting??ncia FS-DA");

        Domain dominioFinalidadeEmissao = new Domain("finalidadeEmissao");
        dominioFinalidadeEmissao.addDomainPair("1", "1 - Normal");
        dominioFinalidadeEmissao.addDomainPair("2", "2 - Complementar");
        dominioFinalidadeEmissao.addDomainPair("3", "3 - Ajuste");

        Domain dominioTipoImpressaoDanfe = new Domain("tipoImpressaoDanfe");
        dominioTipoImpressaoDanfe.addDomainPair("1", "1 - Retrato");
        dominioTipoImpressaoDanfe.addDomainPair("2", "2 - Paisagem");

        Domain dominioOrigemMercadoria = new Domain("origemMercadoria");
        dominioOrigemMercadoria.addDomainPair("0", "Nacional");
        dominioOrigemMercadoria.addDomainPair("1", "Estrangeira - Importa????o direta");
        dominioOrigemMercadoria.addDomainPair("2", "Estrangeira - Adquirida no mercado interno");

        Domain dominioCRT = new Domain("crt");
        dominioCRT.addDomainPair("1", "1 - Simples Nacional");
        dominioCRT.addDomainPair("2", "2 - Simples Nac - Excesso");
        dominioCRT.addDomainPair("3", "3 - Regime Normal");

        Domain dominioModeloCupomFiscal = new Domain("modeloCupomFiscal");
        dominioModeloCupomFiscal.addDomainPair("2B", "2B - Emitido em M??quina Registradora (N??o ECF)");
        dominioModeloCupomFiscal.addDomainPair("2C", "2C - Cupom Fiscal PDV");
        dominioModeloCupomFiscal.addDomainPair("2D", "2D - Cupom Fiscal Emitido por ECF");

        Domain dominioTipoReajuste = new Domain("tipoReajuste");
        dominioTipoReajuste.addDomainPair("A", "Aumentar");
        dominioTipoReajuste.addDomainPair("D", "Diminuir");

        Domain dominioMetodoDepreciacao = new Domain("metodoDepreciacao");
        dominioMetodoDepreciacao.addDomainPair("1", "Linear");
        dominioMetodoDepreciacao.addDomainPair("2", "Soma dos Algarismos dos Anos");
        dominioMetodoDepreciacao.addDomainPair("3", "Horas de Trabalho");
        dominioMetodoDepreciacao.addDomainPair("4", "Unidades Produzidas");

        Domain dominioTipoDepreciacao = new Domain("tipoDepreciacao");
        dominioTipoDepreciacao.addDomainPair("N", "Normal");
        dominioTipoDepreciacao.addDomainPair("A", "Acelerada");
        dominioTipoDepreciacao.addDomainPair("I", "Incentivada");

        Domain dominioTipoHorarioPonto = new Domain("tipoHorarioPonto");
        dominioTipoHorarioPonto.addDomainPair("F", "Fixo");
        dominioTipoHorarioPonto.addDomainPair("D", "Di??rio");
        dominioTipoHorarioPonto.addDomainPair("S", "Semanal");
        dominioTipoHorarioPonto.addDomainPair("M", "Mensal");

        Domain dominioTipoTrabalho = new Domain("tipoTrabalho");
        dominioTipoTrabalho.addDomainPair("N", "Normal");
        dominioTipoTrabalho.addDomainPair("C", "Compensa????o");
        dominioTipoTrabalho.addDomainPair("F", "Folga");

        Domain dominioNaoRegistroPonto = new Domain("tipoNaoRegistroPonto");
        dominioNaoRegistroPonto.addDomainPair("F", "Falta");
        dominioNaoRegistroPonto.addDomainPair("I", "Ignora");
        dominioNaoRegistroPonto.addDomainPair("A", "Aviso de Ocorr??ncia");

        Domain dominioUtilizacaoRelogioPonto = new Domain("utilizacaoRelogioPonto");
        dominioUtilizacaoRelogioPonto.addDomainPair("P", "Ponto");
        dominioUtilizacaoRelogioPonto.addDomainPair("R", "Refeit??rio");
        dominioUtilizacaoRelogioPonto.addDomainPair("C", "Circula????o");

        Domain dominioSituacaoBancoHoras = new Domain("situacaoBancoHoras");
        dominioSituacaoBancoHoras.addDomainPair("N", "N??o Utilizado");
        dominioSituacaoBancoHoras.addDomainPair("U", "Utilizado");
        dominioSituacaoBancoHoras.addDomainPair("P", "Utilizado Parcialmente");

        Domain dominioTipoMarcacaoPonto = new Domain("tipoMarcacaoPonto");
        dominioTipoMarcacaoPonto.addDomainPair("E", "Entrada");
        dominioTipoMarcacaoPonto.addDomainPair("S", "Sa??da");
        dominioTipoMarcacaoPonto.addDomainPair("D", "Desconsiderar");

        Domain dominioTipoRegistroPonto = new Domain("tipoRegistroPonto");
        dominioTipoRegistroPonto.addDomainPair("O", "Original");
        dominioTipoRegistroPonto.addDomainPair("I", "Inclu??do por Digita????o");
        dominioTipoRegistroPonto.addDomainPair("P", "Intervalo Pr??-assinalado");

        Domain dominioPontoTratamentoHoraMais = new Domain("pontoTratamentoHoraMais");
        dominioPontoTratamentoHoraMais.addDomainPair("B", "Banco de Horas");
        dominioPontoTratamentoHoraMais.addDomainPair("E", "Pagar como Extra");

        Domain dominioPontoTratamentoHoraMenos = new Domain("pontoTratamentoHoraMenos");
        dominioPontoTratamentoHoraMenos.addDomainPair("B", "Banco de Horas");
        dominioPontoTratamentoHoraMenos.addDomainPair("F", "Descontar como Falta");

        Domain dominioPontoModalidadeHoraExtra = new Domain("pontoModalidadeHoraExtra");
        dominioPontoModalidadeHoraExtra.addDomainPair("D", "Diurna");
        dominioPontoModalidadeHoraExtra.addDomainPair("N", "Noturna");

        Domain dominioPontoHoraCompensar = new Domain("pontoHoraCompensar");
        dominioPontoHoraCompensar.addDomainPair("1", "Horas a mais");
        dominioPontoHoraCompensar.addDomainPair("2", "Horas a Menos");

        Domain dominioProporcionalidadeFolha = new Domain("proporcionalidadeFolha");
        dominioProporcionalidadeFolha.addDomainPair("0", "30 dias");
        dominioProporcionalidadeFolha.addDomainPair("I", "Conforme dias do m??s");

        Domain dominioTipoGps = new Domain("tipoGps");
        dominioTipoGps.addDomainPair("1", "Emite GPS de apenas uma filial (servi??os que representam a pr??pria empresa, um tomador de servi??o ou obra por empreitada parcial)");
        dominioTipoGps.addDomainPair("2", "Emite GPS somente de um servi??o (obra pr??pria/empreitada total)");
        dominioTipoGps.addDomainPair("3", "Emite GPS apenas de uma filial referente aos cooperados (tomadores de servi??o de cooperativa de trabalho)");

        Domain dominioBeneficiarioPlanoSaude = new Domain("beneficiarioPlanoSaude");
        dominioBeneficiarioPlanoSaude.addDomainPair("1", "Somente Colaborador");
        dominioBeneficiarioPlanoSaude.addDomainPair("2", "Colaborador e Dependentes");
        dominioBeneficiarioPlanoSaude.addDomainPair("3", "Somente Dependentes");

        Domain dominioBaseCalculoEventoFolha = new Domain("baseCalculoEventoFolha");
        dominioBaseCalculoEventoFolha.addDomainPair("01", "Sal??rio contratual: define que a base de c??lculo deve ser calculada sobre o valor do sal??rio contratual");
        dominioBaseCalculoEventoFolha.addDomainPair("02", "Sal??rio m??nimo: define que a base de c??lculo deve ser calculada sobre o valor do sal??rio m??nimo");
        dominioBaseCalculoEventoFolha.addDomainPair("03", "Sal??rio m??nimo: define que a base de c??lculo deve ser calculada sobre o valor do sal??rio m??nimo");
        dominioBaseCalculoEventoFolha.addDomainPair("04", "L??quido: define que a base de c??lculo deve ser calculada sobre o l??quido da folha");

        Domain dominioTipoEventoFolha = new Domain("tipoEventoFolha");
        dominioTipoEventoFolha.addDomainPair("P", "Provento");
        dominioTipoEventoFolha.addDomainPair("D", "Desconto");

        Domain dominioUnidadeEventoFolha = new Domain("unidadeEventoFolha");
        dominioUnidadeEventoFolha.addDomainPair("V", "Valor");
        dominioUnidadeEventoFolha.addDomainPair("P", "Percentual");

        Domain dominioSituacaoFerias = new Domain("situacaoFerias");
        dominioSituacaoFerias.addDomainPair("0", "Em Aberto");
        dominioSituacaoFerias.addDomainPair("1", "Gozado");
        dominioSituacaoFerias.addDomainPair("2", "Parcialmente gozado");
        dominioSituacaoFerias.addDomainPair("3", "Perda por Afastamento");
        dominioSituacaoFerias.addDomainPair("4", "Perda por Falta");
        dominioSituacaoFerias.addDomainPair("5", "Cancelado");

        Domain dominioTipoLancamentoFolha = new Domain("tipoLancamentoFolha");
        dominioTipoLancamentoFolha.addDomainPair("1", "Folha Mensal");
        dominioTipoLancamentoFolha.addDomainPair("2", "Rescis??o");

        Domain dominioTipoFatorRiscoPpp = new Domain("tipoFatorRiscoPpp");
        dominioTipoFatorRiscoPpp.addDomainPair("F", "F??sico");
        dominioTipoFatorRiscoPpp.addDomainPair("Q", "Qu??mico");
        dominioTipoFatorRiscoPpp.addDomainPair("E", "Ergon??mico/Psicossocial");
        dominioTipoFatorRiscoPpp.addDomainPair("M", "Mec??nico/de Acidente");

        Domain dominioTipoExameMedicoPpp = new Domain("tipoExameMedicoPpp");
        dominioTipoExameMedicoPpp.addDomainPair("A", "Admissional");
        dominioTipoExameMedicoPpp.addDomainPair("P", "Peri??dico");
        dominioTipoExameMedicoPpp.addDomainPair("R", "Retorno ao Trabalho");
        dominioTipoExameMedicoPpp.addDomainPair("D", "Demissional");

        Domain dominioExameMedicoPpp = new Domain("exameMedicoPpp");
        dominioExameMedicoPpp.addDomainPair("R", "Referencial");
        dominioExameMedicoPpp.addDomainPair("S", "Sequencial");

        Domain dominioInformarContaContabil = new Domain("informarContaContabil");
        dominioInformarContaContabil.addDomainPair("C", "C??digo");
        dominioInformarContaContabil.addDomainPair("M", "M??scara");

        Domain dominioFormaEscrituracaoSped = new Domain("formaEscrituracaoSped");
        dominioFormaEscrituracaoSped.addDomainPair("LDC", "Livro Di??rio Completo");
        dominioFormaEscrituracaoSped.addDomainPair("LDE", "Livro Di??rio com Escritura????o Resumida");
        dominioFormaEscrituracaoSped.addDomainPair("LBD", "Livro Balancete Di??rio e Balan??os");

        Domain dominioPeriodicidadeInciceContabil = new Domain("periodicidadeInciceContabil");
        dominioPeriodicidadeInciceContabil.addDomainPair("D", "Di??rio");
        dominioPeriodicidadeInciceContabil.addDomainPair("M", "Mensal");

        Domain dominioTipoPlanoContaSped = new Domain("tipoPlanoContaSped");
        dominioTipoPlanoContaSped.addDomainPair("S", "Sint??tica");
        dominioTipoPlanoContaSped.addDomainPair("A", "An??litica");

        Domain dominioTipoContaContabil = new Domain("tipoContaContabil");
        dominioTipoContaContabil.addDomainPair("S", "Sint??tica");
        dominioTipoContaContabil.addDomainPair("A", "An??litica");

        Domain dominioSituacaoContaContabil = new Domain("situacaoContaContabil");
        dominioSituacaoContaContabil.addDomainPair("A", "Ativa");
        dominioSituacaoContaContabil.addDomainPair("I", "Inativa");

        Domain dominioNaturezaContaContabil = new Domain("naturezaContaContabil");
        dominioNaturezaContaContabil.addDomainPair("C", "Credora");
        dominioNaturezaContaContabil.addDomainPair("D", "Devedora");

        Domain dominioPatrimonioResultadoContaContabil = new Domain("patrimonioResultadoContaContabil");
        dominioPatrimonioResultadoContaContabil.addDomainPair("P", "Patrim??nio");
        dominioPatrimonioResultadoContaContabil.addDomainPair("R", "Resultado");

        Domain dominioDfcContaContabil = new Domain("dfcContaContabil");
        dominioDfcContaContabil.addDomainPair("N", "N??o participa");
        dominioDfcContaContabil.addDomainPair("O", "Atividades Operacionais");
        dominioDfcContaContabil.addDomainPair("F", "Atividades de Financiamento");
        dominioDfcContaContabil.addDomainPair("I", "Atividades de Investimento");

        Domain dominioCriterioLancamentoFechamento = new Domain("criterioLancamentoFechamento");
        dominioCriterioLancamentoFechamento.addDomainPair("L", "Livre");
        dominioCriterioLancamentoFechamento.addDomainPair("A", "Avisar");
        dominioCriterioLancamentoFechamento.addDomainPair("N", "N??o permitir (para lan??amentos efetuados fora do per??odo informado)");

        Domain dominioTipoLancamentoProgramado = new Domain("tipoLancamentoProgramado");
        dominioTipoLancamentoProgramado.addDomainPair("UDVC", "Um D??bito para V??rios Cr??ditos");
        dominioTipoLancamentoProgramado.addDomainPair("UCVD", "Um Cr??dito para V??rios D??bitos");
        dominioTipoLancamentoProgramado.addDomainPair("UDUC", "Um D??bito para Um Cr??dito");
        dominioTipoLancamentoProgramado.addDomainPair("VDVC", "V??rios D??bitos para V??rios Cr??ditos");

        Domain dominioTipoLancamento = new Domain("tipoLancamento");
        dominioTipoLancamento.addDomainPair("C", "Cr??dito");
        dominioTipoLancamento.addDomainPair("D", "D??bito");

        Domain dominioFormaCalculoDre = new Domain("formaCalculoDre");
        dominioFormaCalculoDre.addDomainPair("S", "Sint??tica [soma contas filhas - sinal de mais ou de menos]");
        dominioFormaCalculoDre.addDomainPair("V", "Vinculada [vinculada a conta do balancete - recupera o sinal da conta m??e]");
        dominioFormaCalculoDre.addDomainPair("R", "Resultado de Opera????es da DRE [soma das opera????es - sinal de igual]");

        Domain dominioSinalDre = new Domain("sinalDre");
        dominioSinalDre.addDomainPair("+", "+");
        dominioSinalDre.addDomainPair("-", "-");
        dominioSinalDre.addDomainPair("=", "=");

        Domain dominioAberturaFechamento = new Domain("aberturaFechamento");
        dominioAberturaFechamento.addDomainPair("A", "Abertura");
        dominioAberturaFechamento.addDomainPair("F", "Fechamento");

        Domain dominioTipoContaCaixa = new Domain("tipoContaCaixa");
        dominioTipoContaCaixa.addDomainPair("C", "Corrente");
        dominioTipoContaCaixa.addDomainPair("P", "Poupan??a");
        dominioTipoContaCaixa.addDomainPair("I", "Investimento");
        dominioTipoContaCaixa.addDomainPair("X", "Caixa Interno");

        Domain dominioTipoRegimeEmpresa = new Domain("tipoRegimeEmpresa");
        dominioTipoRegimeEmpresa.addDomainPair("1", "Lucro Real");
        dominioTipoRegimeEmpresa.addDomainPair("2", "Lucro Presumido");
        dominioTipoRegimeEmpresa.addDomainPair("3", "Simples Nacional");

        Domain dominioApuracaoRegime = new Domain("apuracaoRegime");
        dominioApuracaoRegime.addDomainPair("1", "Regime Compet??ncia");
        dominioApuracaoRegime.addDomainPair("2", "Regime de Caixa");

        Domain dominioRetencao = new Domain("retencao");
        dominioRetencao.addDomainPair("E", "Emiss??o");
        dominioRetencao.addDomainPair("P", "Pagamento");

        Domain dominioCalculoPisCofins = new Domain("calculoPisCofins");
        dominioCalculoPisCofins.addDomainPair("AB", "Al??quota B??sica");
        dominioCalculoPisCofins.addDomainPair("AD", "Al??quota Diferenciada");
        dominioCalculoPisCofins.addDomainPair("UM", "Unidade de Medida de Produto");

        Domain dominioSimplesNacionalCabecalho = new Domain("simplesNacionalCabecalho");
        dominioSimplesNacionalCabecalho.addDomainPair("1", "Federal");
        dominioSimplesNacionalCabecalho.addDomainPair("2", "Estadual");

        Domain dominioCompetenciaImposto = new Domain("competenciaImposto");
        dominioCompetenciaImposto.addDomainPair("F", "Federal");
        dominioCompetenciaImposto.addDomainPair("E", "Estadual");
        dominioCompetenciaImposto.addDomainPair("M", "Municipal");

        Domain dominioPeriodicidadeImposto = new Domain("periodicidadeImposto");
        dominioPeriodicidadeImposto.addDomainPair("1", "mensal");
        dominioPeriodicidadeImposto.addDomainPair("2", "quinzenal");
        dominioPeriodicidadeImposto.addDomainPair("3", "decendial");
        dominioPeriodicidadeImposto.addDomainPair("4", "trimestral");
        dominioPeriodicidadeImposto.addDomainPair("5", "semanal");
        dominioPeriodicidadeImposto.addDomainPair("6", "semestral");
        dominioPeriodicidadeImposto.addDomainPair("7", "por nota");

        Domain dominioTipoImposto = new Domain("tipoImposto");
        dominioTipoImposto.addDomainPair("L", "Lan??ado");
        dominioTipoImposto.addDomainPair("C", "Calculado");

        Domain dominioCRTString = new Domain("crtString");
        dominioCRTString.addDomainPair("1", "Simples Nacional");
        dominioCRTString.addDomainPair("2", "Simples Nac - Excesso");
        dominioCRTString.addDomainPair("3", "Regime Normal");

        Domain dominioAberturaEncerramento = new Domain("aberturaEncerramento");
        dominioAberturaEncerramento.addDomainPair("A", "Abertura");
        dominioAberturaEncerramento.addDomainPair("E", "Encerramento");

        Domain dominioContratoStatusSolicitacao = new Domain("contratoStatusSolicitacaoServico");
        dominioContratoStatusSolicitacao.addDomainPair("A", "Aguardando");
        dominioContratoStatusSolicitacao.addDomainPair("D", "Deferido");
        dominioContratoStatusSolicitacao.addDomainPair("I", "Indeferido");

        Domain dominioPatrimTipoAquisicaoBem = new Domain("patrimTipoAquisicaoBem");
        dominioPatrimTipoAquisicaoBem.addDomainPair("01", "Compra");
        dominioPatrimTipoAquisicaoBem.addDomainPair("02", "Permuta");
        dominioPatrimTipoAquisicaoBem.addDomainPair("03", "Doa????o");
        dominioPatrimTipoAquisicaoBem.addDomainPair("04", "Loca????o");
        dominioPatrimTipoAquisicaoBem.addDomainPair("05", "Comodato");
        dominioPatrimTipoAquisicaoBem.addDomainPair("06", "Leasing");
        dominioPatrimTipoAquisicaoBem.addDomainPair("07", "Transfer??ncia");

        Domain dominioPatrimTipoMovimentacao = new Domain("patrimTipoMovimentacao");
        dominioPatrimTipoMovimentacao.addDomainPair("01", "Distribui????o");
        dominioPatrimTipoMovimentacao.addDomainPair("02", "Remanejamento");
        dominioPatrimTipoMovimentacao.addDomainPair("03", "Sa??da Provis??ria");
        dominioPatrimTipoMovimentacao.addDomainPair("04", "Empr??stimo");
        dominioPatrimTipoMovimentacao.addDomainPair("05", "Arrendamento");

        Domain dominioPatrimEstadoConservacao = new Domain("patrimEstadoConservacao");
        dominioPatrimEstadoConservacao.addDomainPair("01", "Novo");
        dominioPatrimEstadoConservacao.addDomainPair("02", "Bom");
        dominioPatrimEstadoConservacao.addDomainPair("03", "Recuper??vel");
        dominioPatrimEstadoConservacao.addDomainPair("04", "Inserv??vel");

        Domain dominioCompraTipoRequisicao = new Domain("compraTipoRequisicao");
        dominioCompraTipoRequisicao.addDomainPair("01", "Interna");
        dominioCompraTipoRequisicao.addDomainPair("02", "Externa");

        Domain dominioCompraTipoPedido = new Domain("compraTipoPedido");
        dominioCompraTipoPedido.addDomainPair("01", "Normal");
        dominioCompraTipoPedido.addDomainPair("02", "Planejado");
        dominioCompraTipoPedido.addDomainPair("03", "Aberto");

        Domain dominioCompraSituacaoCotacao = new Domain("compraSituacaoCotacao");
        dominioCompraSituacaoCotacao.addDomainPair("A", "Aberta");
        dominioCompraSituacaoCotacao.addDomainPair("C", "Confirmada");
        dominioCompraSituacaoCotacao.addDomainPair("F", "Fechada");

        Domain dominioCompraTipoFrete = new Domain("compraTipoFrete");
        dominioCompraTipoFrete.addDomainPair("C", "CIF");
        dominioCompraTipoFrete.addDomainPair("F", "FOB");

        Domain dominioTipoNaturezaFinanceira = new Domain("tipoNaturezaFinanceira");
        dominioTipoNaturezaFinanceira.addDomainPair("R", "Receita");
        dominioTipoNaturezaFinanceira.addDomainPair("D", "Despesa");

        Domain dominioLayoutRemessa = new Domain("layoutRemessa");
        dominioLayoutRemessa.addDomainPair("240", "240");
        dominioLayoutRemessa.addDomainPair("400", "400");

        Domain dominioEspecieCobranca = new Domain("especieCobranca");
        dominioEspecieCobranca.addDomainPair("DM", "Duplicata Mercantil");
        dominioEspecieCobranca.addDomainPair("DS", "Duplicata de Servi??os");
        dominioEspecieCobranca.addDomainPair("RC", "Recibo");
        dominioEspecieCobranca.addDomainPair("NP", "Nota Promiss??ria");

        Domain dominioVendaOrcamentoTipo = new Domain("vendaOrcamentoTipo");
        dominioVendaOrcamentoTipo.addDomainPair("O", "Or??amento");
        dominioVendaOrcamentoTipo.addDomainPair("P", "Pedido");

        Domain dominioVendaOrcamentoSituacao = new Domain("vendaOrcamentoSituacao");
        dominioVendaOrcamentoSituacao.addDomainPair("D", "Digitacao");
        dominioVendaOrcamentoSituacao.addDomainPair("P", "Producao");
        dominioVendaOrcamentoSituacao.addDomainPair("X", "Expedicao");
        dominioVendaOrcamentoSituacao.addDomainPair("F", "Faturado");
        dominioVendaOrcamentoSituacao.addDomainPair("E", "Entregue");

        Domain dominioVendaResponsavelFrete = new Domain("VendaResponsavelFrete");
        dominioVendaResponsavelFrete.addDomainPair("1", "Emitente");
        dominioVendaResponsavelFrete.addDomainPair("2", "Destinat??rio");

        Domain dominioVendaRomaneioSituacao = new Domain("vendaRomaneioSituacao");
        dominioVendaRomaneioSituacao.addDomainPair("A", "Aberto");
        dominioVendaRomaneioSituacao.addDomainPair("T", "Tr??nsito");
        dominioVendaRomaneioSituacao.addDomainPair("E", "Encerrado");

        Domain dominioTributIcmsBaseCalculo = new Domain("tributIcmsBaseCalculo");
        dominioTributIcmsBaseCalculo.addDomainPair("0", "Margem Valor Agregado (%)");
        dominioTributIcmsBaseCalculo.addDomainPair("1", "Pauta (Valor)");
        dominioTributIcmsBaseCalculo.addDomainPair("2", "Pre??o Tabelado M??x. (valor)");
        dominioTributIcmsBaseCalculo.addDomainPair("3", "Valor da Opera????o");

        Domain dominioTributIcmsStBaseCalculo = new Domain("tributIcmsStBaseCalculo");
        dominioTributIcmsStBaseCalculo.addDomainPair("0", "Pre??o tabelado ou m??ximo sugerido");
        dominioTributIcmsStBaseCalculo.addDomainPair("1", "Lista Negativa (valor)");
        dominioTributIcmsStBaseCalculo.addDomainPair("2", "Lista Positiva (valor)");
        dominioTributIcmsStBaseCalculo.addDomainPair("3", "Lista Neutra (valor)");
        dominioTributIcmsStBaseCalculo.addDomainPair("4", "Margem Valor Agregado(%)");
        dominioTributIcmsStBaseCalculo.addDomainPair("5", "Pauta (valor)");

        Domain dominioTributPisModalidadeBaseCalculo = new Domain("pisModalidadeBaseCalculo");
        dominioTributPisModalidadeBaseCalculo.addDomainPair("0", "Percentual");
        dominioTributPisModalidadeBaseCalculo.addDomainPair("1", "Unidade");

        Domain dominioTributIssModalidadeBaseCalculo = new Domain("issModalidadeBaseCalculo");
        dominioTributIssModalidadeBaseCalculo.addDomainPair("0", "Valor Opera????o");
        dominioTributIssModalidadeBaseCalculo.addDomainPair("9", "Outros");

        Domain dominioTributIssCodigoTributacao = new Domain("issCodigoTributacao");
        dominioTributIssCodigoTributacao.addDomainPair("N", "Normal");
        dominioTributIssCodigoTributacao.addDomainPair("R", "Retida");
        dominioTributIssCodigoTributacao.addDomainPair("S", "Substituta");
        dominioTributIssCodigoTributacao.addDomainPair("I", "Isenta");

        Domain dominioProdutoTipo = new Domain("produtoTipo");
        dominioProdutoTipo.addDomainPair("V", "Venda");
        dominioProdutoTipo.addDomainPair("C", "Composi????o");
        dominioProdutoTipo.addDomainPair("P", "Produ????o");
        dominioProdutoTipo.addDomainPair("I", "Insumo");
        dominioProdutoTipo.addDomainPair("U", "Uso Proprio");

        Domain dominioProdutoClasse = new Domain("produtoClasse");
        dominioProdutoClasse.addDomainPair("A", "A");
        dominioProdutoClasse.addDomainPair("B", "B");
        dominioProdutoClasse.addDomainPair("C", "C");

        Domain dominioProdutoIat = new Domain("produtoIat");
        dominioProdutoIat.addDomainPair("A", "Arredondamento");
        dominioProdutoIat.addDomainPair("T", "Truncamento");

        Domain dominioProdutoIppt = new Domain("produtoIppt");
        dominioProdutoIppt.addDomainPair("P", "Pr??prio");
        dominioProdutoIppt.addDomainPair("T", "Terceiro");

        Domain dominioEmpresaTipo = new Domain("empresaTipo");
        dominioEmpresaTipo.addDomainPair("M", "Matriz");
        dominioEmpresaTipo.addDomainPair("F", "Filial");
        dominioEmpresaTipo.addDomainPair("D", "Dep??sito");

        Domain dominioProdutoTipoItemSped = new Domain("produtoTipoItemSped");
        dominioProdutoTipoItemSped.addDomainPair("00", "Mercadoria para Revenda");
        dominioProdutoTipoItemSped.addDomainPair("01", "Mat??ria-Prima");
        dominioProdutoTipoItemSped.addDomainPair("02", "Embalagem");
        dominioProdutoTipoItemSped.addDomainPair("03", "Produto em Processo");
        dominioProdutoTipoItemSped.addDomainPair("04", "Produto Acabado");
        dominioProdutoTipoItemSped.addDomainPair("05", "Subproduto");
        dominioProdutoTipoItemSped.addDomainPair("06", "Produto Intermedi??rio");
        dominioProdutoTipoItemSped.addDomainPair("07", "Material de Uso e Consumo");
        dominioProdutoTipoItemSped.addDomainPair("08", "Ativo Imobilizado");
        dominioProdutoTipoItemSped.addDomainPair("09", "Servi??os");
        dominioProdutoTipoItemSped.addDomainPair("10", "Outros Insumos");
        dominioProdutoTipoItemSped.addDomainPair("99", "Outras");

        Domain dominioClienteIndicadorPreco = new Domain("clienteIndicadorPreco");
        dominioClienteIndicadorPreco.addDomainPair("T", "Tabela");
        dominioClienteIndicadorPreco.addDomainPair("P", "??ltimo Pedido");

        Domain dominioClienteTipoFrete = new Domain("clienteTipoFrete");
        dominioClienteTipoFrete.addDomainPair("E", "Emitente");
        dominioClienteTipoFrete.addDomainPair("D", "Destinatario");
        dominioClienteTipoFrete.addDomainPair("S", "Sem frete");
        dominioClienteTipoFrete.addDomainPair("T", "Terceiros");

        Domain dominioClienteFormaDesconto = new Domain("clienteFormaDesconto");
        dominioClienteFormaDesconto.addDomainPair("P", "Produto");
        dominioClienteFormaDesconto.addDomainPair("F", "Fim do Pedido");

        Domain dominioFornecedorLocalizacao = new Domain("fornecedorLocalizacao");
        dominioFornecedorLocalizacao.addDomainPair("N", "Nacional");
        dominioFornecedorLocalizacao.addDomainPair("E", "Exterior");

        Domain dominioColaboradorFormaPagamento = new Domain("colaboradorFormaPagamento");
        dominioColaboradorFormaPagamento.addDomainPair("1", "Dinheiro");
        dominioColaboradorFormaPagamento.addDomainPair("2", "Cheque");
        dominioColaboradorFormaPagamento.addDomainPair("3", "Conta");

        Domain dominioSindicatoTipo = new Domain("sindicatoTipo");
        dominioSindicatoTipo.addDomainPair("E", "Empregados");
        dominioSindicatoTipo.addDomainPair("P", "Patronal");

        Domain dominioTalonarioChequeStatus = new Domain("talonarioChequeStatus");
        dominioTalonarioChequeStatus.addDomainPair("N", "Normal");
        dominioTalonarioChequeStatus.addDomainPair("C", "Cancelado");
        dominioTalonarioChequeStatus.addDomainPair("E", "Extraviado");
        dominioTalonarioChequeStatus.addDomainPair("U", "Utilizado");

        Domain dominioChequeStatus = new Domain("chequeStatus");
        dominioChequeStatus.addDomainPair("E", "Em Ser");
        dominioChequeStatus.addDomainPair("B", "Baixado");
        dominioChequeStatus.addDomainPair("U", "Utilizado");
        dominioChequeStatus.addDomainPair("C", "Compensado");
        dominioChequeStatus.addDomainPair("N", "Cancelado");

        Domain dominioFeriadosAbrangencia = new Domain("feriadosAbrangencia");
        dominioFeriadosAbrangencia.addDomainPair("F", "Federal");
        dominioFeriadosAbrangencia.addDomainPair("E", "Estadual");
        dominioFeriadosAbrangencia.addDomainPair("M", "Municipal");

        Domain dominioFeriadosTipo = new Domain("feriadosTipo");
        dominioFeriadosTipo.addDomainPair("F", "Fixo");
        dominioFeriadosTipo.addDomainPair("M", "M??vel");

        Domain dominioContabilLivroFormaEscrituracao = new Domain("contabilLivroFormaEscrituracao");
        dominioContabilLivroFormaEscrituracao.addDomainPair("G", "Di??rio Geral");
        dominioContabilLivroFormaEscrituracao.addDomainPair("R", "Di??rio com Escritura????o Resumida");
        dominioContabilLivroFormaEscrituracao.addDomainPair("A", "Di??rio Auxiliar");
        dominioContabilLivroFormaEscrituracao.addDomainPair("Z", "Raz??o Auxiliar");
        dominioContabilLivroFormaEscrituracao.addDomainPair("B", "Livro de Balancetes Di??rios e Balan??os");

        domains.clear();
        domains.put(tipoPessoa.getDomainId(), tipoPessoa);
        domains.put(tipoSangue.getDomainId(), tipoSangue);
        domains.put(dominioSexo.getDomainId(), dominioSexo);
        domains.put(dominioRacaCor.getDomainId(), dominioRacaCor);
        domains.put(dominioSimNao.getDomainId(), dominioSimNao);
        domains.put(dominioNaoSim.getDomainId(), dominioNaoSim);
        domains.put(dominioMes.getDomainId(), dominioMes);
        domains.put(dominioTipoCentroResultado.getDomainId(), dominioTipoCentroResultado);
        domains.put(dominioStatusNota.getDomainId(), dominioStatusNota);
        domains.put(dominioTipoOperacao.getDomainId(), dominioTipoOperacao);
        domains.put(dominioFormaPagamento.getDomainId(), dominioFormaPagamento);
        domains.put(dominioFormaEmissao.getDomainId(), dominioFormaEmissao);
        domains.put(dominioFinalidadeEmissao.getDomainId(), dominioFinalidadeEmissao);
        domains.put(dominioTipoImpressaoDanfe.getDomainId(), dominioTipoImpressaoDanfe);
        domains.put(dominioCRT.getDomainId(), dominioCRT);
        domains.put(dominioModeloCupomFiscal.getDomainId(), dominioModeloCupomFiscal);
        domains.put(dominioTipoReajuste.getDomainId(), dominioTipoReajuste);
        domains.put(dominioMetodoDepreciacao.getDomainId(), dominioMetodoDepreciacao);
        domains.put(dominioTipoDepreciacao.getDomainId(), dominioTipoDepreciacao);
        domains.put(dominioTipoHorarioPonto.getDomainId(), dominioTipoHorarioPonto);
        domains.put(dominioTipoTrabalho.getDomainId(), dominioTipoTrabalho);
        domains.put(dominioNaoRegistroPonto.getDomainId(), dominioNaoRegistroPonto);
        domains.put(dominioUtilizacaoRelogioPonto.getDomainId(), dominioUtilizacaoRelogioPonto);
        domains.put(dominioSituacaoBancoHoras.getDomainId(), dominioSituacaoBancoHoras);
        domains.put(dominioTipoMarcacaoPonto.getDomainId(), dominioTipoMarcacaoPonto);
        domains.put(dominioTipoRegistroPonto.getDomainId(), dominioTipoRegistroPonto);
        domains.put(dominioProporcionalidadeFolha.getDomainId(), dominioProporcionalidadeFolha);
        domains.put(dominioTipoGps.getDomainId(), dominioTipoGps);
        domains.put(dominioBeneficiarioPlanoSaude.getDomainId(), dominioBeneficiarioPlanoSaude);
        domains.put(dominioBaseCalculoEventoFolha.getDomainId(), dominioBaseCalculoEventoFolha);
        domains.put(dominioTipoEventoFolha.getDomainId(), dominioTipoEventoFolha);
        domains.put(dominioUnidadeEventoFolha.getDomainId(), dominioUnidadeEventoFolha);
        domains.put(dominioSituacaoFerias.getDomainId(), dominioSituacaoFerias);
        domains.put(dominioTipoLancamentoFolha.getDomainId(), dominioTipoLancamentoFolha);
        domains.put(dominioTipoFatorRiscoPpp.getDomainId(), dominioTipoFatorRiscoPpp);
        domains.put(dominioTipoExameMedicoPpp.getDomainId(), dominioTipoExameMedicoPpp);
        domains.put(dominioExameMedicoPpp.getDomainId(), dominioExameMedicoPpp);
        domains.put(dominioInformarContaContabil.getDomainId(), dominioInformarContaContabil);
        domains.put(dominioFormaEscrituracaoSped.getDomainId(), dominioFormaEscrituracaoSped);
        domains.put(dominioPeriodicidadeInciceContabil.getDomainId(), dominioPeriodicidadeInciceContabil);
        domains.put(dominioTipoPlanoContaSped.getDomainId(), dominioTipoPlanoContaSped);
        domains.put(dominioTipoContaContabil.getDomainId(), dominioTipoContaContabil);
        domains.put(dominioSituacaoContaContabil.getDomainId(), dominioSituacaoContaContabil);
        domains.put(dominioNaturezaContaContabil.getDomainId(), dominioNaturezaContaContabil);
        domains.put(dominioPatrimonioResultadoContaContabil.getDomainId(), dominioPatrimonioResultadoContaContabil);
        domains.put(dominioDfcContaContabil.getDomainId(), dominioDfcContaContabil);
        domains.put(dominioCriterioLancamentoFechamento.getDomainId(), dominioCriterioLancamentoFechamento);
        domains.put(dominioTipoLancamentoProgramado.getDomainId(), dominioTipoLancamentoProgramado);
        domains.put(dominioTipoLancamento.getDomainId(), dominioTipoLancamento);
        domains.put(dominioFormaCalculoDre.getDomainId(), dominioFormaCalculoDre);
        domains.put(dominioSinalDre.getDomainId(), dominioSinalDre);
        domains.put(dominioAberturaFechamento.getDomainId(), dominioAberturaFechamento);
        domains.put(dominioTipoContaCaixa.getDomainId(), dominioTipoContaCaixa);
        domains.put(dominioTipoRegimeEmpresa.getDomainId(), dominioTipoRegimeEmpresa);
        domains.put(dominioApuracaoRegime.getDomainId(), dominioApuracaoRegime);
        domains.put(dominioRetencao.getDomainId(), dominioRetencao);
        domains.put(dominioCalculoPisCofins.getDomainId(), dominioCalculoPisCofins);
        domains.put(dominioSimplesNacionalCabecalho.getDomainId(), dominioSimplesNacionalCabecalho);
        domains.put(dominioCompetenciaImposto.getDomainId(), dominioCompetenciaImposto);
        domains.put(dominioPeriodicidadeImposto.getDomainId(), dominioPeriodicidadeImposto);
        domains.put(dominioTipoImposto.getDomainId(), dominioTipoImposto);
        domains.put(dominioCRTString.getDomainId(), dominioCRTString);
        domains.put(dominioAberturaEncerramento.getDomainId(), dominioAberturaEncerramento);
        domains.put(dominioContratoStatusSolicitacao.getDomainId(), dominioContratoStatusSolicitacao);
        domains.put(dominioPatrimTipoAquisicaoBem.getDomainId(), dominioPatrimTipoAquisicaoBem);
        domains.put(dominioPatrimTipoMovimentacao.getDomainId(), dominioPatrimTipoMovimentacao);
        domains.put(dominioPatrimEstadoConservacao.getDomainId(), dominioPatrimEstadoConservacao);
        domains.put(dominioCompraTipoRequisicao.getDomainId(), dominioCompraTipoRequisicao);
        domains.put(dominioCompraTipoPedido.getDomainId(), dominioCompraTipoPedido);
        domains.put(dominioCompraSituacaoCotacao.getDomainId(), dominioCompraSituacaoCotacao);
        domains.put(dominioCompraTipoFrete.getDomainId(), dominioCompraTipoFrete);
        domains.put(dominioPontoTratamentoHoraMais.getDomainId(), dominioPontoTratamentoHoraMais);
        domains.put(dominioPontoTratamentoHoraMenos.getDomainId(), dominioPontoTratamentoHoraMenos);
        domains.put(dominioPontoModalidadeHoraExtra.getDomainId(), dominioPontoModalidadeHoraExtra);
        domains.put(dominioPontoHoraCompensar.getDomainId(), dominioPontoHoraCompensar);
        domains.put(dominioTipoNaturezaFinanceira.getDomainId(), dominioTipoNaturezaFinanceira);
        domains.put(dominioLayoutRemessa.getDomainId(), dominioLayoutRemessa);
        domains.put(dominioEspecieCobranca.getDomainId(), dominioEspecieCobranca);
        domains.put(dominioVendaOrcamentoTipo.getDomainId(), dominioVendaOrcamentoTipo);
        domains.put(dominioVendaOrcamentoSituacao.getDomainId(), dominioVendaOrcamentoSituacao);
        domains.put(dominioVendaResponsavelFrete.getDomainId(), dominioVendaResponsavelFrete);
        domains.put(dominioVendaRomaneioSituacao.getDomainId(), dominioVendaRomaneioSituacao);
        domains.put(dominioOrigemMercadoria.getDomainId(), dominioOrigemMercadoria);
        domains.put(dominioTributIcmsBaseCalculo.getDomainId(), dominioTributIcmsBaseCalculo);
        domains.put(dominioTributIcmsStBaseCalculo.getDomainId(), dominioTributIcmsStBaseCalculo);
        domains.put(dominioTributIssModalidadeBaseCalculo.getDomainId(), dominioTributIssModalidadeBaseCalculo);
        domains.put(dominioTributIssCodigoTributacao.getDomainId(), dominioTributIssCodigoTributacao);
        domains.put(dominioProdutoTipo.getDomainId(), dominioProdutoTipo);
        domains.put(dominioProdutoClasse.getDomainId(), dominioProdutoClasse);
        domains.put(dominioProdutoIat.getDomainId(), dominioProdutoIat);
        domains.put(dominioProdutoIppt.getDomainId(), dominioProdutoIppt);
        domains.put(dominioProdutoTipoItemSped.getDomainId(), dominioProdutoTipoItemSped);
        domains.put(dominioEmpresaTipo.getDomainId(), dominioEmpresaTipo);
        domains.put(dominioClienteIndicadorPreco.getDomainId(), dominioClienteIndicadorPreco);
        domains.put(dominioClienteTipoFrete.getDomainId(), dominioClienteTipoFrete);
        domains.put(dominioClienteFormaDesconto.getDomainId(), dominioClienteFormaDesconto);
        domains.put(dominioFornecedorLocalizacao.getDomainId(), dominioFornecedorLocalizacao);
        domains.put(dominioColaboradorFormaPagamento.getDomainId(), dominioColaboradorFormaPagamento);
        domains.put(dominioSindicatoTipo.getDomainId(), dominioSindicatoTipo);
        domains.put(dominioTalonarioChequeStatus.getDomainId(), dominioTalonarioChequeStatus);
        domains.put(dominioChequeStatus.getDomainId(), dominioChequeStatus);
        domains.put(dominioFeriadosAbrangencia.getDomainId(), dominioFeriadosAbrangencia);
        domains.put(dominioFeriadosTipo.getDomainId(), dominioFeriadosTipo);
        domains.put(dominioContabilLivroFormaEscrituracao.getDomainId(), dominioContabilLivroFormaEscrituracao);

        MDIFrame mdi = new MDIFrame(this);

    }

    /**
     * @return <code>true</code> if the MDI frame must show a change language menu in the menubar, <code>false</code> no change language menu item will be added
     */
    public boolean viewChangeLanguageInMenuBar() {
        return true;
    }

    /**
     * @return list of languages supported by the application
     */
    public ArrayList getLanguages() {
        ArrayList list = new ArrayList();
        list.add(new Language("EN", "English"));
        list.add(new Language("IT", "Italiano"));
        list.add(new Language("ES", "Espanhol"));
        list.add(new Language("PT_BR", "Portugu??s do Brasil"));
        return list;
    }

    /**
     * @return application functions (ApplicationFunction objects), organized as a tree
     */
    public DefaultTreeModel getApplicationFunctions() {
        return (DefaultTreeModel) ((VOResponse) ClientUtils.getData("getFunctionAuthorizations", new Object[0])).getVo();
    }

    /**
     * @return <code>true</code> if the MDI frame must show a panel in the bottom, containing last opened window icons, <code>false</code> no panel is showed
     */
    public boolean viewOpenedWindowIcons() {
        return true;
    }

    public boolean viewFileMenu() {
        return true;
    }
}
