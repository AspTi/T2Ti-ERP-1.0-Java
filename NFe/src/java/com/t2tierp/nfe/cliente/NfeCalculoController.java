package com.t2tierp.nfe.cliente;

import com.t2tierp.cadastros.java.EmpresaVO;
import com.t2tierp.nfe.java.NfeCalculoVO;
import com.t2tierp.nfe.java.NfeDestinatarioVO;
import com.t2tierp.nfe.java.NfeDetalheVO;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe que realiza os calculos da NFe.</p>
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
public class NfeCalculoController {

    public NfeCalculoController() {
    }

    public static NfeCalculoVO calculo(NfeDetalheVO item, EmpresaVO empresa, NfeDestinatarioVO destinatario) throws Exception {
        NfeCalculoVO calculo = new NfeCalculoVO();

        calculo.setCrtEmissor(Integer.valueOf(empresa.getCrt()));
        calculo.setUfEmissor(empresa.getCodigoIbgeUf());
        calculo.setTipoCliente(destinatario.getCpfCnpj().length() == 11 ? "F" : "J");
        calculo.setUfCliente(destinatario.getUf());
        calculo.setUfClienteCod(Integer.valueOf(String.valueOf(destinatario.getCodigoMunicipio()).substring(0, 2)));
        calculo.setValorBrutoProdutos(item.getValorBrutoProduto());
        calculo.setValorFrete(item.getValorFrete());
        calculo.setValorSeguro(item.getValorSeguro());
        calculo.setValorOutrasDespesas(item.getValorOutrasDespesas());
        calculo.setValorDesconto(item.getValorDesconto());
        calculo.setCstIcms(item.getNfeDetalheImpostoIcms().getCstIcms());
        calculo.setCsosn(item.getNfeDetalheImpostoIcms().getCsosn());
        calculo.setModalidadeBcIcms(item.getNfeDetalheImpostoIcms().getModalidadeBcIcms());
        calculo.setTaxaReducaoBcIcms(item.getNfeDetalheImpostoIcms().getTaxaReducaoBcIcms());
        calculo.setAliquotaIcms(item.getNfeDetalheImpostoIcms().getAliquotaIcms());
        calculo.setAliquotaIcmsInter(item.getNfeDetalheImpostoIcms().getAliquotaIcms());
        calculo.setModalidadeBcIcmsSt(item.getNfeDetalheImpostoIcms().getModalidadeBcIcmsSt());
        calculo.setPercentualMvaIcmsSt(item.getNfeDetalheImpostoIcms().getPercentualMvaIcmsSt());
        calculo.setReducaoBcIcmsSt(item.getNfeDetalheImpostoIcms().getPercentualReducaoBcIcmsSt());
        calculo.setAliquotaIcmsSt(item.getNfeDetalheImpostoIcms().getAliquotaIcmsSt());
        calculo.setAliquotaCreditoIcmsSn(item.getNfeDetalheImpostoIcms().getAliquotaCreditoIcmsSn());
        calculo.setCstIpi(item.getNfeDetalheImpostoIpi().getCstIpi());
        calculo.setAliquotaIpi(item.getNfeDetalheImpostoIpi().getAliquotaIpi());
        calculo.setCstPis(item.getNfeDetalheImpostoPis().getCstPis());
        calculo.setAliquotaPis(item.getNfeDetalheImpostoPis().getAliquotaPisPercentual());
        calculo.setAliquotaPisReais(item.getNfeDetalheImpostoPis().getAliquotaPisReais());
        calculo.setCstCofins(item.getNfeDetalheImpostoCofins().getCstCofins());
        calculo.setAliquotaCofins(item.getNfeDetalheImpostoCofins().getAliquotaCofinsPercentual());
        calculo.setAliquotaCofinsReais(item.getNfeDetalheImpostoCofins().getAliquotaCofinsReais());

        if (calculo.getCrtEmissor().intValue() == 1) {
            //1 = Simples Nacional

            if (calculo.getCsosn().equals("101")) {
                //101 Tributada pelo Simples Nacional com permiss??o de cr??dito
                calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));

                calculo.setValorCreditoIcmsSn(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaCreditoIcmsSn().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorCreditoIcmsSn(calculo.getValorCreditoIcmsSn().setScale(2, RoundingMode.HALF_DOWN));
            } else if (calculo.getCsosn().equals("102")) {
                //102 Tributada pelo Simples Nacional sem permiss??o de cr??dito
                //nao tem calculo
            } else if (calculo.getCsosn().equals("103")) {
                //103 Isen????o do ICMS no Simples Nacional para faixa de receita bruta
                //nao tem calculo
            } else if (calculo.getCsosn().equals("201")) {
                //201 Tributada pelo Simples Nacional com permiss??o de cr??dito e com cobran??a do ICMS por substitui????o tribut??ria
                calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                    //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                } else {
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                }
                calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                calculo.setValorCreditoIcmsSn(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaCreditoIcmsSn().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorCreditoIcmsSn(calculo.getValorCreditoIcmsSn().setScale(2, RoundingMode.HALF_DOWN));

                //calculo icms proprio
                //calculo icms ST
                if (calculo.getModalidadeBcIcmsSt().equals("0")) {//Pre??o Tabelado ou M??ximo Sugerido
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("1")) {//lista Negativa(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("2")) {//Lista Positiva(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("3")) {//Lista Neutra(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("4")) {//Margem valor Agregado(%)
                    calculo.setBaseCalculoIcmsSt(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().subtract(calculo.getBaseCalculoIcmsSt().multiply(calculo.getPercentualMvaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorReducaoBcIcmsSt(calculo.getBaseCalculoIcmsSt().multiply(calculo.getAliquotaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    calculo.setValorReducaoBcIcmsSt(calculo.getValorReducaoBcIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorIcmsSt(calculo.getValorIcms().subtract(calculo.getValorReducaoBcIcmsSt()));
                } else if (calculo.getModalidadeBcIcmsSt().equals("5")) {//Pauta(valor)
                    //
                }
            } else if (calculo.getCsosn().equals("202")) {
                //202 Tributada pelo Simples Nacional sem permiss??o de cr??dito e com cobran??a do ICMS por substitui????o tribut??ria
                calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                    //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                } else {
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                }
                calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                calculo.setValorCreditoIcmsSn(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaCreditoIcmsSn().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorCreditoIcmsSn(calculo.getValorCreditoIcmsSn().setScale(2, RoundingMode.HALF_DOWN));

                //calculo icms proprio
                //calculo icms ST
                if (calculo.getModalidadeBcIcmsSt().equals("0")) {//Pre??o Tabelado ou M??ximo Sugerido
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("1")) {//lista Negativa(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("2")) {//Lista Positiva(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("3")) {//Lista Neutra(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("4")) {//Margem valor Agregado(%)
                    calculo.setBaseCalculoIcmsSt(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().subtract(calculo.getBaseCalculoIcmsSt().multiply(calculo.getPercentualMvaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorReducaoBcIcmsSt(calculo.getBaseCalculoIcmsSt().multiply(calculo.getAliquotaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    calculo.setValorReducaoBcIcmsSt(calculo.getValorReducaoBcIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorIcmsSt(calculo.getValorIcms().subtract(calculo.getValorReducaoBcIcmsSt()));
                } else if (calculo.getModalidadeBcIcmsSt().equals("5")) {//Pauta(valor)
                    //
                }
            } else if (calculo.getCsosn().equals("203")) {
                //203 Tributada pelo Simples Nacional sem permiss??o de cr??dito e com cobran??a do ICMS por substitui????o tribut??ria
                calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                    //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                } else {
                    calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                }
                calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                calculo.setValorCreditoIcmsSn(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaCreditoIcmsSn().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorCreditoIcmsSn(calculo.getValorCreditoIcmsSn().setScale(2, RoundingMode.HALF_DOWN));

                //calculo icms proprio
                //calculo icms ST
                if (calculo.getModalidadeBcIcmsSt().equals("0")) {//Pre??o Tabelado ou M??ximo Sugerido
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("1")) {//lista Negativa(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("2")) {//Lista Positiva(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("3")) {//Lista Neutra(valor)
                    //
                } else if (calculo.getModalidadeBcIcmsSt().equals("4")) {//Margem valor Agregado(%)
                    calculo.setBaseCalculoIcmsSt(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().subtract(calculo.getBaseCalculoIcmsSt().multiply(calculo.getPercentualMvaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorReducaoBcIcmsSt(calculo.getBaseCalculoIcmsSt().multiply(calculo.getAliquotaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    calculo.setValorReducaoBcIcmsSt(calculo.getValorReducaoBcIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorIcmsSt(calculo.getValorIcms().subtract(calculo.getValorReducaoBcIcmsSt()));
                } else if (calculo.getModalidadeBcIcmsSt().equals("5")) {//Pauta(valor)
                    //
                }
            } else if (calculo.getCsosn().equals("300")) {
                //300 Imune - Classificam-se neste c??digo as opera????es praticadas por optantes pelo Simples Nacional contempladas com imunidade do ICMS
                //nao tem calculo
            } else if (calculo.getCsosn().equals("400")) {
                //400 N??o tributada pelo Simples Nacional - Classificam-se neste c??digo as opera????es praticadas por optantes pelo Simples Nacional n??o sujeitas ?? tributa????o pelo ICMS dentro do Simples Nacional
                //nao tem calculo
            } else if (calculo.getCsosn().equals("500")) {
                //500 ICMS cobrado anteriormente por substitui????o tribut??ria (substitu??do) ou por antecipa????o - Classificam-se neste c??digo as opera????es sujeitas exclusivamente ao regime de substitui????o tribut??ria na condi????o de substitu??do tribut??rio ou no caso de antecipa????es
                //nao tem calculo
            } else if (calculo.getCsosn().equals("900")) {
                //900 Outros - Classificam-se neste c??digo as demais opera????es que n??o se enquadrem nos c??digos 101, 102, 103, 201, 202, 203, 300, 400 e 500
            }
        } else if ((calculo.getCrtEmissor().intValue() == 2) || (calculo.getCrtEmissor().intValue() == 3)) {
            //2 - Simples Nacional - excesso  de sublimite de receita bruta;  3 - Regime Normal.

            //calcula ipi
            if (calculo.getCstIpi().equals("00")
                    || calculo.getCstIpi().equals("49")
                    || calculo.getCstIpi().equals("50")
                    || calculo.getCstIpi().equals("99")) {
                calculo.setBaseCalculoIpi(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                calculo.setValorIpi(calculo.getBaseCalculoIpi().subtract(calculo.getBaseCalculoIpi().multiply(calculo.getPercentualMvaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))));
                calculo.setValorIpi(calculo.getValorIpi().setScale(2, RoundingMode.HALF_DOWN));
            } else {
                calculo.setBaseCalculoIpi(BigDecimal.ZERO);
                calculo.setValorIpi(BigDecimal.ZERO);
            }

            //calcula icms
            if (calculo.getCstIcms().equals("00")) {
                //00 Tributada integralmente
                if (calculo.getModalidadeBcIcms().equals("0")) {
                    //Margem valor Agregado
                } else if (calculo.getModalidadeBcIcms().equals("1")) {
                    //Pauta(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("2")) {
                    //Pre??o Tabelado M??x.(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("3")) {
                    //Valor da Opera????o
                    if (calculo.getTipoCliente().equals("F")) {
                        //pessoa fisica inclui ipi na base de calculo do icms
                        //pessoa fisica sempre usa a aliquota normal da UF emissao
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi())).subtract(calculo.getValorDesconto()));
                        calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    } else {
                        //pessoa juridica n??o inclui ipi na base de calculo do icms
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                        if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        } else {
                            //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        }
                    }
                    calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                }
            } else if (calculo.getCstIcms().equals("10")) {
                //10 Tributada e com cobran??a do ICMS por substitui????o tribut??ria
                if (calculo.getModalidadeBcIcms().equals("0")) {
                    //Margem valor Agregado
                } else if (calculo.getModalidadeBcIcms().equals("1")) {
                    //Pauta(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("2")) {
                    //Pre??o Tabelado M??x.(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("3")) {
                    //Valor da Opera????o
                    if (calculo.getTipoCliente().equals("F")) {
                        //pessoa fisica inclui ipi na base de calculo do icms
                        //pessoa fisica sempre usa a aliquota normal da UF emissao
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi())).subtract(calculo.getValorDesconto()));
                        calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    } else {
                        //pessoa juridica n??o inclui ipi na base de calculo do icms
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                        if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        } else {
                            //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        }
                    }
                    calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                }

                //calculo icms ST
                if (calculo.getModalidadeBcIcmsSt().equals("0")) {
                    //Pre??o Tabelado ou M??ximo Sugerido
                } else if (calculo.getModalidadeBcIcmsSt().equals("1")) {
                    //lista Negativa(valor)
                } else if (calculo.getModalidadeBcIcmsSt().equals("2")) {
                    //Lista Positiva(valor)
                } else if (calculo.getModalidadeBcIcmsSt().equals("3")) {
                    //Lista Neutra(valor)
                } else if (calculo.getModalidadeBcIcmsSt().equals("4")) {
                    //Margem valor Agregado(%)
                    calculo.setBaseCalculoIcmsSt(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi())).subtract(calculo.getValorDesconto()));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().subtract(calculo.getBaseCalculoIcmsSt().multiply(calculo.getPercentualMvaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN))));
                    calculo.setBaseCalculoIcmsSt(calculo.getBaseCalculoIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorReducaoBcIcmsSt(calculo.getBaseCalculoIcmsSt().multiply(calculo.getAliquotaIcmsSt().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    calculo.setValorReducaoBcIcmsSt(calculo.getValorReducaoBcIcmsSt().setScale(2, RoundingMode.HALF_DOWN));
                    calculo.setValorIcmsSt(calculo.getValorIcms().subtract(calculo.getValorReducaoBcIcmsSt()));
                } else if (calculo.getModalidadeBcIcmsSt().equals("5")) {
                    //Pauta(valor)
                }

            } else if (calculo.getCstIcms().equals("20")) {
                //20 Com redu????o de base de c??lculo do ICMS
                if (calculo.getModalidadeBcIcms().equals("0")) {
                    //Margem valor Agregado
                } else if (calculo.getModalidadeBcIcms().equals("1")) {
                    //Pauta(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("2")) {
                    //Pre??o Tabelado M??x.(Valor)
                } else if (calculo.getModalidadeBcIcms().equals("3")) {
                    //Valor da Opera????o
                    if (calculo.getTipoCliente().equals("F")) {
                        //pessoa fisica inclui ipi na base de calculo do icms
                        //pessoa fisica sempre usa a aliquota normal da UF emissao
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi())).subtract(calculo.getValorDesconto()));
                        calculo.setValorReducaoBcIcms(calculo.getBaseCalculoIcms().multiply(calculo.getTaxaReducaoBcIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        calculo.setValorReducaoBcIcms(calculo.getValorReducaoBcIcms().setScale(2, RoundingMode.HALF_DOWN));
                        calculo.setBaseCalculoIcms(calculo.getBaseCalculoIcms().subtract(calculo.getValorReducaoBcIcms()));
                        calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                    } else {
                        //pessoa juridica n??o inclui ipi na base de calculo do icms
                        calculo.setBaseCalculoIcms(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas())).subtract(calculo.getValorDesconto()));
                        calculo.setValorReducaoBcIcms(calculo.getBaseCalculoIcms().multiply(calculo.getTaxaReducaoBcIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        calculo.setValorReducaoBcIcms(calculo.getValorReducaoBcIcms().setScale(2, RoundingMode.HALF_DOWN));
                        if (calculo.getUfClienteCod().intValue() == calculo.getUfEmissor().intValue()) {
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcms().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        } else {
                            //se for uf diferente entre emissor e destinatario usar aliquota de transferencia
                            calculo.setValorIcms(calculo.getBaseCalculoIcms().multiply(calculo.getAliquotaIcmsInter().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                        }
                    }
                    calculo.setValorIcms(calculo.getValorIcms().setScale(2, RoundingMode.HALF_DOWN));
                }
            } else if (calculo.getCstIcms().equals("30")) {
                //30 Isenta ou n??o tributada e com cobran??a do ICMS por substitui????o tribut??ria
            } else if (calculo.getCstIcms().equals("40")) {
                //40 Isenta do ICMS
            } else if (calculo.getCstIcms().equals("41")) {
                //41 Nao tributada no ICMS
            } else if (calculo.getCstIcms().equals("50")) {
                //50 Suspensao do ICMS
            } else if (calculo.getCstIcms().equals("51")) {
                //51 Diferimento do ICMS
            } else if (calculo.getCstIcms().equals("60")) {
                //60 ICMS cobrado anteriormente por substitui????o tribut??ria
            } else if (calculo.getCstIcms().equals("70")) {
                //70 Com redu????o da base de c??lculo e cobran??a do ICMS por substitui????o tribut??ria
            } else if (calculo.getCstIcms().equals("90")) {
                //90 Outras
            }

            //calcula Pis 01
            if (calculo.getCstPis().equals("01")) {
                //Tribut??vel por Al??quota Normal
                calculo.setBaseCalculoPis(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi()).add(calculo.getValorIcms())).subtract(calculo.getValorDesconto()));
                calculo.setValorPis(calculo.getBaseCalculoPis().multiply(calculo.getAliquotaPis().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorPis(calculo.getValorPis().setScale(2, RoundingMode.HALF_DOWN));
            }
            //calcula Pis 02
            if (calculo.getCstPis().equals("02")) {
                //Tribut??vel por Al??quota diferenciada
                calculo.setBaseCalculoPis(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi()).add(calculo.getValorIcms())).subtract(calculo.getValorDesconto()));
                calculo.setValorPis(calculo.getBaseCalculoPis().multiply(calculo.getAliquotaPisReais()));
                calculo.setValorPis(calculo.getValorPis().setScale(2, RoundingMode.HALF_DOWN));
            }
            //calcula Cofins 01
            if (calculo.getCstCofins().equals("01")) {
                //Tribut??vel por Al??quota Normal
                calculo.setBaseCalculoCofins(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi()).add(calculo.getValorIcms())).subtract(calculo.getValorDesconto()));
                calculo.setValorCofins(calculo.getBaseCalculoCofins().multiply(calculo.getAliquotaCofins().divide(BigDecimal.valueOf(100), RoundingMode.HALF_DOWN)));
                calculo.setValorCofins(calculo.getValorCofins().setScale(2, RoundingMode.HALF_DOWN));
            }
            //calcula Cofins 02
            if (calculo.getCstCofins().equals("02")) {
                //Tribut??vel por Al??quota diferenciada
                calculo.setBaseCalculoCofins(calculo.getValorBrutoProdutos().add(calculo.getValorFrete().add(calculo.getValorSeguro()).add(calculo.getValorOutrasDespesas()).add(calculo.getValorIpi()).add(calculo.getValorIcms())).subtract(calculo.getValorDesconto()));
                calculo.setValorCofins(calculo.getBaseCalculoCofins().multiply(calculo.getAliquotaCofinsReais()));
                calculo.setValorCofins(calculo.getValorCofins().setScale(2, RoundingMode.HALF_DOWN));
            }
        }
        return calculo;
    }
}
