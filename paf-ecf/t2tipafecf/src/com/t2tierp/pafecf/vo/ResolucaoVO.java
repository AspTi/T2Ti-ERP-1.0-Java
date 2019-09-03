package com.t2tierp.pafecf.vo;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: PAF-ECF + TEF - Objeto de valor referente a tabela Resolucao.</p>
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
public class ResolucaoVO {

    private Integer id;
    private String resolucaoTela;
    private Integer largura;
    private Integer altura;
    private String imagemTela;

    public ResolucaoVO() {
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the resolucaoTela
     */
    public String getResolucaoTela() {
        return resolucaoTela;
    }

    /**
     * @param resolucaoTela the resolucaoTela to set
     */
    public void setResolucaoTela(String resolucaoTela) {
        this.resolucaoTela = resolucaoTela;
    }

    /**
     * @return the largura
     */
    public Integer getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(Integer largura) {
        this.largura = largura;
    }

    /**
     * @return the altura
     */
    public Integer getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    /**
     * @return the imagemTela
     */
    public String getImagemTela() {
        return imagemTela;
    }

    /**
     * @param imagemTela the imagemTela to set
     */
    public void setImagemTela(String imagemTela) {
        this.imagemTela = imagemTela;
    }
}
