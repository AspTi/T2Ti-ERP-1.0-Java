package com.t2tierp.folhapagamento.java;

import com.t2tierp.cadastros.java.EmpresaVO;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.openswing.swing.message.receive.java.ValueObjectImpl;


/**
* <p>Title: T2Ti ERP
* <p>Description:  VO relacionado a tabela [FOLHA_FERIAS_COLETIVAS]
*
* <p>The MIT License
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
@Entity
@Table(name = "FOLHA_FERIAS_COLETIVAS")
public class FolhaFeriasColetivasVO extends ValueObjectImpl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_INICIO")
    private Date dataInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_FIM")
    private Date dataFim;
    @Column(name = "DIAS_GOZO")
    private Integer diasGozo;
    @Temporal(TemporalType.DATE)
    @Column(name = "ABONO_PECUNIARIO_INICIO")
    private Date abonoPecuniarioInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "ABONO_PECUNIARIO_FIM")
    private Date abonoPecuniarioFim;
    @Column(name = "DIAS_ABONO")
    private Integer diasAbono;
    @Temporal(TemporalType.DATE)
    @Column(name = "DATA_PAGAMENTO")
    private Date dataPagamento;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private EmpresaVO empresa;

    public FolhaFeriasColetivasVO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getDiasGozo() {
        return diasGozo;
    }

    public void setDiasGozo(Integer diasGozo) {
        this.diasGozo = diasGozo;
    }

    public Date getAbonoPecuniarioInicio() {
        return abonoPecuniarioInicio;
    }

    public void setAbonoPecuniarioInicio(Date abonoPecuniarioInicio) {
        this.abonoPecuniarioInicio = abonoPecuniarioInicio;
    }

    public Date getAbonoPecuniarioFim() {
        return abonoPecuniarioFim;
    }

    public void setAbonoPecuniarioFim(Date abonoPecuniarioFim) {
        this.abonoPecuniarioFim = abonoPecuniarioFim;
    }

    public Integer getDiasAbono() {
        return diasAbono;
    }

    public void setDiasAbono(Integer diasAbono) {
        this.diasAbono = diasAbono;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public EmpresaVO getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaVO empresa) {
        this.empresa = empresa;
    }


}
