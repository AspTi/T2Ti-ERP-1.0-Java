{*******************************************************************************
Title: T2Ti ERP
Description: Classe de controle do produto.

The MIT License

Copyright: Copyright (C) 2010 T2Ti.COM

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

       The author may be contacted at:
           t2ti.com@gmail.com</p>

@author Albert Eije (T2Ti.COM) | Jose Rodrigues de Oliveira Junior
@version 1.0
*******************************************************************************}
unit ProdutoController;

interface

uses
  Classes, SQLExpr, SysUtils, ProdutoVO, Generics.Collections, EstoqueVO;

type
  TProdutoController = class
  protected
  public
    class function Consulta(Codigo: String; Tipo: Integer): TProdutoVO;
    class function ConsultaId(Id: Integer): TProdutoVO;
    class function ConsultaIdProduto(Id: Integer): Boolean;
    class function TabelaProduto: TObjectList<TProdutoVO>; overload;
    class function TabelaProduto(CodigoInicio: Integer; CodigoFim: Integer): TObjectList<TProdutoVO>; overload;
    class function TabelaProduto(NomeInicio: String; NomeFim : String): TObjectList<TProdutoVO>; overload;
    class function ConsultaProdutoSPED(pDataInicial, pDataFinal: String; pPerfilApresentacao: Integer): TObjectList<TProdutoVO>;
    class function ConsultaEstoque: TEstoqueVO;
  end;

implementation

uses UDataModule, Biblioteca, ConfiguracaoController;

var
  ConsultaSQL, ClausulaWhere: String;
  Query: TSQLQuery;

class function TProdutoController.Consulta(Codigo: String; Tipo: Integer): TProdutoVO;
var
  Produto: TProdutoVO;
begin
  case Tipo of
    1:begin      // pesquisa pelo codigo da balanca
      ClausulaWhere := ' where ' +
                      '(P.CODIGO_BALANCA = ' + QuotedStr(Codigo)+')' +
                      ' and (P.ID_UNIDADE_PRODUTO = U.ID)';
    end;
    2:begin     // pesquisa pelo GTIN
       ClausulaWhere := ' where ' +
                       ' (P.GTIN = ' + QuotedStr(Codigo)+ ')' +
                       ' and (P.ID_UNIDADE_PRODUTO = U.ID)';
    end;
    3:begin     // pesquisa pelo CODIGO_INTERNO ou GTIN
       ClausulaWhere := 'where ' +
                       ' ((P.CODIGO_INTERNO = ' + QuotedStr(Codigo)+ ')'+
                       ' or  (P.GTIN = ' + QuotedStr(copy(Codigo,1,14))+  '))' +
                       ' and (P.ID_UNIDADE_PRODUTO = U.ID)';
    end;
    4:begin     // pesquisa pelo Id
       ClausulaWhere := 'where ' +
                       ' (P.ID = ' + QuotedStr(Codigo) + ') '+
                       ' and (P.ID_UNIDADE_PRODUTO = U.ID) ';
    end;
  end;

  ConsultaSQL :=
                  'select ' +
                  ' P.ID, ' +
                  ' P.ID_UNIDADE_PRODUTO, ' +
                  ' P.GTIN, ' +
                  ' P.CODIGO_INTERNO, ' +
                  ' P.NOME AS NOME_PRODUTO, ' +
                  ' P.DESCRICAO, ' +
                  ' P.DESCRICAO_PDV, ' +
                  ' P.VALOR_VENDA, ' +
                  ' P.QTD_ESTOQUE, ' +
                  ' P.QTD_ESTOQUE_ANTERIOR, ' +
                  ' P.ESTOQUE_MIN, ' +
                  ' P.ESTOQUE_MAX, ' +
                  ' P.IAT, ' +
                  ' P.IPPT, ' +
                  ' P.NCM, ' +
                  ' P.TIPO_ITEM_SPED, ' +
                  ' P.DATA_ESTOQUE, ' +
                  ' P.TAXA_IPI, ' +
                  ' P.TAXA_ISSQN, ' +
                  ' P.TAXA_PIS, ' +
                  ' P.TAXA_COFINS, ' +
                  ' P.TAXA_ICMS, ' +
                  ' P.CST, ' +
                  ' P.CSOSN, ' +
                  ' P.TOTALIZADOR_PARCIAL, ' +
                  ' P.ECF_ICMS_ST, ' +
                  ' P.CODIGO_BALANCA, ' +
                  ' P.PAF_P_ST, ' +
                  ' P.HASH_TRIPA, ' +
                  ' P.HASH_INCREMENTO, ' +
                  ' U.NOME AS NOME_UNIDADE, ' +
                  ' U.PODE_FRACIONAR ' +
                  'from ' +
                  ' PRODUTO P, ' +
                  ' UNIDADE_PRODUTO U ' +
                  ClausulaWhere;

  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;

      Produto := TProdutoVO.Create;
      Produto.Id := Query.FieldByName('ID').AsInteger;
      Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
      Produto.GTIN := Query.FieldByName('GTIN').AsString;
      Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
      Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
      Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
      Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
      Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
      Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
      Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
      Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
      Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
      Produto.IAT := Query.FieldByName('IAT').AsString;
      Produto.IPPT := Query.FieldByName('IPPT').AsString;
      Produto.NCM := Query.FieldByName('NCM').AsString;
      Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
      Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
      Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
      Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
      Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
      Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
      Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
      Produto.Cst := Query.FieldByName('CST').AsString;
      Produto.Csosn := Query.FieldByName('CSOSN').AsString;
      Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
      Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
      Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
      Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
      Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
      Produto.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
      Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
      Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
      result := Produto;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.ConsultaId(Id: Integer): TProdutoVO;
var
  Produto: TProdutoVO;
begin
  ConsultaSQL :=
                  'select ' +
                  ' P.ID, ' +
                  ' P.ID_UNIDADE_PRODUTO, ' +
                  ' P.GTIN, ' +
                  ' P.CODIGO_INTERNO, ' +
                  ' P.NOME AS NOME_PRODUTO, ' +
                  ' P.DESCRICAO, ' +
                  ' P.DESCRICAO_PDV, ' +
                  ' P.VALOR_VENDA, ' +
                  ' P.QTD_ESTOQUE, ' +
                  ' P.QTD_ESTOQUE_ANTERIOR, ' +
                  ' P.ESTOQUE_MIN, ' +
                  ' P.ESTOQUE_MAX, ' +
                  ' P.IAT, ' +
                  ' P.IPPT, ' +
                  ' P.NCM, ' +
                  ' P.TIPO_ITEM_SPED, ' +
                  ' P.DATA_ESTOQUE, ' +
                  ' P.TAXA_IPI, ' +
                  ' P.TAXA_ISSQN, ' +
                  ' P.TAXA_PIS, ' +
                  ' P.TAXA_COFINS, ' +
                  ' P.TAXA_ICMS, ' +
                  ' P.CST, ' +
                  ' P.CSOSN, ' +
                  ' P.TOTALIZADOR_PARCIAL, ' +
                  ' P.ECF_ICMS_ST, ' +
                  ' P.CODIGO_BALANCA, ' +
                  ' P.PAF_P_ST, ' +
                  ' P.HASH_TRIPA, ' +
                  ' P.HASH_INCREMENTO, ' +
                  ' U.NOME AS NOME_UNIDADE, ' +
                  ' U.PODE_FRACIONAR ' +
                  'from ' +
                  ' PRODUTO P, ' +
                  ' UNIDADE_PRODUTO U ' +
                  'where ' +
                  ' (P.ID = ' + IntToStr(Id) + ') '+
                  ' and (P.ID_UNIDADE_PRODUTO = U.ID) ';
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;

      Produto := TProdutoVO.Create;
      Produto.Id := Query.FieldByName('ID').AsInteger;
      Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
      Produto.GTIN := Query.FieldByName('GTIN').AsString;
      Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
      Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
      Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
      Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
      Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
      Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
      Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
      Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
      Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
      Produto.IAT := Query.FieldByName('IAT').AsString;
      Produto.IPPT := Query.FieldByName('IPPT').AsString;
      Produto.NCM := Query.FieldByName('NCM').AsString;
      Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
      Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
      Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
      Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
      Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
      Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
      Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
      Produto.Cst := Query.FieldByName('CST').AsString;
      Produto.Csosn := Query.FieldByName('CSOSN').AsString;
      Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
      Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
      Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
      Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
      Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
      Produto.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
      Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
      Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
      result := Produto;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.ConsultaIdProduto(Id: Integer): Boolean;
begin
  ConsultaSQL := 'select ID from PRODUTO where (ID = :pID) ';
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.ParamByName('pID').AsInteger:=Id;
      Query.Open;
      if not Query.IsEmpty then
        result := true
      else
        result := false;
    except
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.TabelaProduto: TObjectList<TProdutoVO>;
var
  ListaProduto: TObjectList<TProdutoVO>;
  Produto: TProdutoVO;
  TotalRegistros: Integer;
begin
  try
    try
      //verifica se existem produtos
      ConsultaSQL :=
        'select count(*) as TOTAL '+
        'from PRODUTO P, UNIDADE_PRODUTO U '+
        'where (P.ID_UNIDADE_PRODUTO = U.ID)';

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        //continua com a execu??o do procedimento
        ConsultaSQL :=
                      'select ' +
                      ' P.ID, ' +
                      ' P.ID_UNIDADE_PRODUTO, ' +
                      ' P.GTIN, ' +
                      ' P.CODIGO_INTERNO, ' +
                      ' P.NOME AS NOME_PRODUTO, ' +
                      ' P.DESCRICAO, ' +
                      ' P.DESCRICAO_PDV, ' +
                      ' P.VALOR_VENDA, ' +
                      ' P.QTD_ESTOQUE, ' +
                      ' P.QTD_ESTOQUE_ANTERIOR, ' +
                      ' P.ESTOQUE_MIN, ' +
                      ' P.ESTOQUE_MAX, ' +
                      ' P.IAT, ' +
                      ' P.IPPT, ' +
                      ' P.NCM, ' +
                      ' P.TIPO_ITEM_SPED, ' +
                      ' P.DATA_ESTOQUE, ' +
                      ' P.TAXA_IPI, ' +
                      ' P.TAXA_ISSQN, ' +
                      ' P.TAXA_PIS, ' +
                      ' P.TAXA_COFINS, ' +
                      ' P.TAXA_ICMS, ' +
                      ' P.CST, ' +
                      ' P.CSOSN, ' +
                      ' P.TOTALIZADOR_PARCIAL, ' +
                      ' P.ECF_ICMS_ST, ' +
                      ' P.CODIGO_BALANCA, ' +
                      ' P.PAF_P_ST, ' +
                      ' P.HASH_TRIPA, ' +
                      ' P.HASH_INCREMENTO, ' +
                      ' U.NOME AS NOME_UNIDADE, ' +
                      ' U.PODE_FRACIONAR ' +
                      'from ' +
                      ' PRODUTO P, UNIDADE_PRODUTO U '+
                      'where ' +
                      ' (P.ID_UNIDADE_PRODUTO = U.ID)';

        Query.sql.Text := ConsultaSQL;
        Query.Open;

        ListaProduto := TObjectList<TProdutoVO>.Create;

        Query.First;
        while not Query.Eof do
        begin
          Produto := TProdutoVO.Create;
          Produto.Id := Query.FieldByName('ID').AsInteger;
          Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
          Produto.GTIN := Query.FieldByName('GTIN').AsString;
          Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
          Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
          Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
          Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
          Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
          Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
          Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
          Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
          Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
          Produto.IAT := Query.FieldByName('IAT').AsString;
          Produto.IPPT := Query.FieldByName('IPPT').AsString;
          Produto.NCM := Query.FieldByName('NCM').AsString;
          Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
          Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
          Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
          Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
          Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
          Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
          Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
          Produto.Cst := Query.FieldByName('CST').AsString;
          Produto.Csosn := Query.FieldByName('CSOSN').AsString;
          Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
          Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
          Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
          Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
          Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
          Produto.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
          Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
          Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
          ListaProduto.Add(Produto);
          Query.next;
        end;
        result := ListaProduto;
      end
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.TabelaProduto(CodigoInicio: Integer; CodigoFim : Integer) : TObjectList<TProdutoVO>;
var
  ListaProduto: TObjectList<TProdutoVO>;
  Produto: TProdutoVO;
  TotalRegistros: Integer;
begin
  try
    try
      //verifica se existem produtos
      ConsultaSQL :=
        'select count(*) as TOTAL '+
        'from PRODUTO P, UNIDADE_PRODUTO U '+
        'where (P.ID_UNIDADE_PRODUTO = U.ID) '+
        'and P.ID between '+IntToStr(CodigoInicio)+' and '+IntToStr(CodigoFim);

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        //continua com a execu??o do procedimento
        ConsultaSQL :=
                      'select ' +
                      ' P.ID, ' +
                      ' P.ID_UNIDADE_PRODUTO, ' +
                      ' P.GTIN, ' +
                      ' P.CODIGO_INTERNO, ' +
                      ' P.NOME AS NOME_PRODUTO, ' +
                      ' P.DESCRICAO, ' +
                      ' P.DESCRICAO_PDV, ' +
                      ' P.VALOR_VENDA, ' +
                      ' P.QTD_ESTOQUE, ' +
                      ' P.QTD_ESTOQUE_ANTERIOR, ' +
                      ' P.ESTOQUE_MIN, ' +
                      ' P.ESTOQUE_MAX, ' +
                      ' P.IAT, ' +
                      ' P.IPPT, ' +
                      ' P.NCM, ' +
                      ' P.TIPO_ITEM_SPED, ' +
                      ' P.DATA_ESTOQUE, ' +
                      ' P.TAXA_IPI, ' +
                      ' P.TAXA_ISSQN, ' +
                      ' P.TAXA_PIS, ' +
                      ' P.TAXA_COFINS, ' +
                      ' P.TAXA_ICMS, ' +
                      ' P.CST, ' +
                      ' P.CSOSN, ' +
                      ' P.TOTALIZADOR_PARCIAL, ' +
                      ' P.ECF_ICMS_ST, ' +
                      ' P.CODIGO_BALANCA, ' +
                      ' P.PAF_P_ST, ' +
                      ' P.HASH_TRIPA, ' +
                      ' P.HASH_INCREMENTO, ' +
                      ' U.NOME AS NOME_UNIDADE, ' +
                      ' U.PODE_FRACIONAR ' +
                      'from ' +
                      ' PRODUTO P, UNIDADE_PRODUTO U '+
                      'where ' +
                      ' (P.ID_UNIDADE_PRODUTO = U.ID) '+
                      'and P.ID between '+IntToStr(CodigoInicio)+' and '+IntToStr(CodigoFim);

        Query.sql.Text := ConsultaSQL;
        Query.Open;

        ListaProduto := TObjectList<TProdutoVO>.Create;

        Query.First;
        while not Query.Eof do
        begin
          Produto := TProdutoVO.Create;
          Produto.Id := Query.FieldByName('ID').AsInteger;
          Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
          Produto.GTIN := Query.FieldByName('GTIN').AsString;
          Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
          Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
          Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
          Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
          Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
          Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
          Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
          Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
          Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
          Produto.IAT := Query.FieldByName('IAT').AsString;
          Produto.IPPT := Query.FieldByName('IPPT').AsString;
          Produto.NCM := Query.FieldByName('NCM').AsString;
          Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
          Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
          Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
          Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
          Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
          Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
          Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
          Produto.Cst := Query.FieldByName('CST').AsString;
          Produto.Csosn := Query.FieldByName('CSOSN').AsString;
          Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
          Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
          Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
          Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
          Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
          Produto.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
          Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
          Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
          ListaProduto.Add(Produto);
          Query.next;
        end;
        result := ListaProduto;
      end
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.TabelaProduto(NomeInicio: String ; NomeFim: String): TObjectList<TProdutoVO>;
var
  ListaProduto: TObjectList<TProdutoVO>;
  Produto: TProdutoVO;
  TotalRegistros: Integer;
begin
  try
    try
      //verifica se existem produtos
      ConsultaSQL :=
        'select count(*) as TOTAL '+
        'from PRODUTO P, UNIDADE_PRODUTO U '+
        'where (P.ID_UNIDADE_PRODUTO = U.ID) '+
        'and (P.NOME like "%'+Trim(NomeInicio)+'%" or P.NOME like "%'+Trim(NomeFim) + '%")';


      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        //continua com a execu??o do procedimento
        ConsultaSQL :=
                      'select ' +
                      ' P.ID, ' +
                      ' P.ID_UNIDADE_PRODUTO, ' +
                      ' P.GTIN, ' +
                      ' P.CODIGO_INTERNO, ' +
                      ' P.NOME AS NOME_PRODUTO, ' +
                      ' P.DESCRICAO, ' +
                      ' P.DESCRICAO_PDV, ' +
                      ' P.VALOR_VENDA, ' +
                      ' P.QTD_ESTOQUE, ' +
                      ' P.QTD_ESTOQUE_ANTERIOR, ' +
                      ' P.ESTOQUE_MIN, ' +
                      ' P.ESTOQUE_MAX, ' +
                      ' P.IAT, ' +
                      ' P.IPPT, ' +
                      ' P.NCM, ' +
                      ' P.TIPO_ITEM_SPED, ' +
                      ' P.DATA_ESTOQUE, ' +
                      ' P.TAXA_IPI, ' +
                      ' P.TAXA_ISSQN, ' +
                      ' P.TAXA_PIS, ' +
                      ' P.TAXA_COFINS, ' +
                      ' P.TAXA_ICMS, ' +
                      ' P.CST, ' +
                      ' P.CSOSN, ' +
                      ' P.TOTALIZADOR_PARCIAL, ' +
                      ' P.ECF_ICMS_ST, ' +
                      ' P.CODIGO_BALANCA, ' +
                      ' P.PAF_P_ST, ' +
                      ' P.HASH_TRIPA, ' +
                      ' P.HASH_INCREMENTO, ' +
                      ' U.NOME AS NOME_UNIDADE, ' +
                      ' U.PODE_FRACIONAR ' +
                      'from ' +
                      ' PRODUTO P, UNIDADE_PRODUTO U '+
                      ' where (P.ID_UNIDADE_PRODUTO = U.ID) '+
                      ' and (P.NOME like "%'+Trim(NomeInicio)+'%" or P.NOME like "%'+Trim(NomeFim) + '%")';

        Query.sql.Text := ConsultaSQL;
        Query.Open;

        ListaProduto := TObjectList<TProdutoVO>.Create;

        Query.First;
        while not Query.Eof do
        begin
          Produto := TProdutoVO.Create;
          Produto.Id := Query.FieldByName('ID').AsInteger;
          Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
          Produto.GTIN := Query.FieldByName('GTIN').AsString;
          Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
          Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
          Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
          Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
          Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
          Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
          Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
          Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
          Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
          Produto.IAT := Query.FieldByName('IAT').AsString;
          Produto.IPPT := Query.FieldByName('IPPT').AsString;
          Produto.NCM := Query.FieldByName('NCM').AsString;
          Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
          Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
          Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
          Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
          Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
          Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
          Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
          Produto.Cst := Query.FieldByName('CST').AsString;
          Produto.Csosn := Query.FieldByName('CSOSN').AsString;
          Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
          Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
          Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
          Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
          Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
          Produto.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
          Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
          Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
          ListaProduto.Add(Produto);
          Query.next;
        end;
        result := ListaProduto;
      end
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.ConsultaProdutoSPED(pDataInicial, pDataFinal: String; pPerfilApresentacao : Integer): TObjectList<TProdutoVO>;
var
  ListaProduto: TObjectList<TProdutoVO>;
  Produto: TProdutoVO;
  TotalRegistros, Perfil: Integer;
  DataInicio, DataFim : String ;
begin
  try
    try

     DataInicio := QuotedStr(FormatDateTime('yyyy-mm-dd', StrToDate(pDataInicial)));
     DataFim := QuotedStr(FormatDateTime('yyyy-mm-dd', StrToDate(pDataFinal)));
     Perfil := pPerfilApresentacao;

        ConsultaSQL :=
            ' select count(*) as total '+
            ' from  PRODUTO P, UNIDADE_PRODUTO U, ECF_VENDA_CABECALHO V, ECF_VENDA_DETALHE D'+
            ' where V.DATA_VENDA between '+DataInicio+' and '+DataFim+
            ' and (P.ID_UNIDADE_PRODUTO = U.ID)'+
            ' and (V.ID=D.ID_ECF_VENDA_CABECALHO)'+
            ' and (D.ID_ECF_PRODUTO=P.ID) group by D.ID_ECF_PRODUTO';

        Query := TSQLQuery.Create(nil);
        Query.SQLConnection := FDataModule.Conexao;
        Query.sql.Text := ConsultaSQL;
        Query.Open;
        TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

    if TotalRegistros > 0 then
    begin
      ListaProduto := TObjectList<TProdutoVO>.Create;
      case Perfil of
       0 : begin
       // Perfil A
        ClausulaWhere :=
                      ' where v.DATA_VENDA between '+DataInicio+' and '+DataFim+
                      ' and D.CANCELADO <> ' + QuotedStr('S') +
                      ' and (P.ID_UNIDADE_PRODUTO = U.ID)'+
                      ' and (v.id=d.id_ecf_venda_cabecalho)'+
                      ' and (d.id_ecf_produto=p.id)';
       end;
       1 : begin
       // Perfil B
        ClausulaWhere :=
                      ' where v.DATA_VENDA between '+DataInicio+' and '+DataFim+
                      ' and (P.ID_UNIDADE_PRODUTO = U.ID)'+
                      ' and (v.id=d.id_ecf_venda_cabecalho)'+
                      ' and (d.id_ecf_produto=p.id)';
       end;
       2 : begin
       // Perfil C
        ClausulaWhere :=
                      ' where v.DATA_VENDA between '+DataInicio+' and '+DataFim+
                      ' and (P.ID_UNIDADE_PRODUTO = U.ID)'+
                      ' and (v.id=d.id_ecf_venda_cabecalho)'+
                      ' and (d.id_ecf_produto=p.id)';
       end;
      end;

        ConsultaSQL :=
                      'select distinct ' +
                      ' P.ID, ' +
                      ' P.ID_UNIDADE_PRODUTO, ' +
                      ' P.GTIN, ' +
                      ' P.CODIGO_INTERNO, ' +
                      ' P.NOME AS NOME_PRODUTO, ' +
                      ' P.DESCRICAO, ' +
                      ' P.DESCRICAO_PDV, ' +
                      ' P.VALOR_VENDA, ' +
                      ' P.QTD_ESTOQUE, ' +
                      ' P.QTD_ESTOQUE_ANTERIOR, ' +
                      ' P.ESTOQUE_MIN, ' +
                      ' P.ESTOQUE_MAX, ' +
                      ' P.IAT, ' +
                      ' P.IPPT, ' +
                      ' P.NCM, ' +
                      ' P.TIPO_ITEM_SPED, ' +
                      ' P.DATA_ESTOQUE, ' +
                      ' P.TAXA_IPI, ' +
                      ' P.TAXA_ISSQN, ' +
                      ' P.TAXA_PIS, ' +
                      ' P.TAXA_COFINS, ' +
                      ' P.TAXA_ICMS, ' +
                      ' P.CST, ' +
                      ' P.CSOSN, ' +
                      ' P.TOTALIZADOR_PARCIAL, ' +
                      ' P.ECF_ICMS_ST, ' +
                      ' P.CODIGO_BALANCA, ' +
                      ' P.PAF_P_ST, ' +
                      ' P.HASH_TRIPA, ' +
                      ' U.NOME AS NOME_UNIDADE, ' +
                      ' U.PODE_FRACIONAR ' +
                      'from ' +
                      ' PRODUTO P, UNIDADE_PRODUTO U, ECF_VENDA_CABECALHO V, ECF_VENDA_DETALHE D'+
                      ClausulaWhere;

      Query.sql.Text := ConsultaSQL;
      Query.Open;
      Query.First;

      while not Query.Eof do
      begin
        Produto := TProdutoVO.Create;
        Produto.Id := Query.FieldByName('ID').AsInteger;
        Produto.IdUnidade := Query.FieldByName('ID_UNIDADE_PRODUTO').AsInteger;
        Produto.GTIN := Query.FieldByName('GTIN').AsString;
        Produto.CodigoInterno := Query.FieldByName('CODIGO_INTERNO').AsString;
        Produto.Nome := Query.FieldByName('NOME_PRODUTO').AsString;
        Produto.Descricao := Query.FieldByName('DESCRICAO').AsString;
        Produto.DescricaoPDV := Query.FieldByName('DESCRICAO_PDV').AsString;
        Produto.ValorVenda := Query.FieldByName('VALOR_VENDA').AsFloat;
        Produto.QtdeEstoque := Query.FieldByName('QTD_ESTOQUE').AsFloat;
        Produto.QtdeEstoqueAnterior := Query.FieldByName('QTD_ESTOQUE_ANTERIOR').AsFloat;
        Produto.EstoqueMinimo := Query.FieldByName('ESTOQUE_MIN').AsFloat;
        Produto.EstoqueMaximo := Query.FieldByName('ESTOQUE_MAX').AsFloat;
        Produto.IAT := Query.FieldByName('IAT').AsString;
        Produto.IPPT := Query.FieldByName('IPPT').AsString;
        Produto.NCM := Query.FieldByName('NCM').AsString;
        Produto.TipoItemSped := Query.FieldByName('TIPO_ITEM_SPED').AsString;
        Produto.DataEstoque := Query.FieldByName('DATA_ESTOQUE').AsString;
        Produto.AliquotaIpi := Query.FieldByName('TAXA_IPI').AsFloat;
        Produto.AliquotaIssqn := Query.FieldByName('TAXA_ISSQN').AsFloat;
        Produto.AliquotaPis := Query.FieldByName('TAXA_PIS').AsFloat;
        Produto.AliquotaCofins := Query.FieldByName('TAXA_COFINS').AsFloat;
        Produto.AliquotaIcms := Query.FieldByName('TAXA_ICMS').AsFloat;
        Produto.Cst := Query.FieldByName('CST').AsString;
        Produto.Csosn := Query.FieldByName('CSOSN').AsString;
        Produto.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
        Produto.ECFICMS := Query.FieldByName('ECF_ICMS_ST').AsString;
        Produto.CodigoBalanca:= Query.FieldByName('CODIGO_BALANCA').AsInteger;
        Produto.PafProdutoSt := Query.FieldByName('PAF_P_ST').AsString;
        Produto.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
        Produto.UnidadeProduto := Query.FieldByName('NOME_UNIDADE').AsString;
        Produto.PodeFracionarUnidade := Query.FieldByName('PODE_FRACIONAR').AsString;
        ListaProduto.Add(Produto);
        Query.next;
      end;
      result := ListaProduto;
     end
     else
       result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

class function TProdutoController.ConsultaEstoque: TEstoqueVO;
var
  Estoque: TEstoqueVO;
begin
  try
    try
        ConsultaSQL :=
                      'select ' +
                      ' ID, ' +
                      ' ID_ECF_EMPRESA, ' +
                      ' ID_ECF_IMPRESSORA, ' +
                      ' NUMERO_SERIE_ECF, ' +
                      ' DATA_ATUALIZACAO, ' +
                      ' HORA_ATUALIZACAO, ' +
                      ' HASH_TRIPA, ' +
                      ' HASH_INCREMENTO ' +
                      'from ' +
                      ' ECF_ESTOQUE ';

      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      Query.First;

      Estoque := TEstoqueVO.Create;
      Estoque.Id := Query.FieldByName('ID').AsInteger;
      Estoque.IdEcfEmpresa := Query.FieldByName('ID_ECF_EMPRESA').AsInteger;
      Estoque.IdEcfImpressora := Query.FieldByName('ID_ECF_IMPRESSORA').AsInteger;
      Estoque.NumeroSerieEcf := Query.FieldByName('NUMERO_SERIE_ECF').AsString;
      Estoque.DataAtualizacao := Query.FieldByName('DATA_ATUALIZACAO').AsString;
      Estoque.HoraAtualizacao := Query.FieldByName('HORA_ATUALIZACAO').AsString;
      Estoque.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
      Estoque.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;

      result := Estoque;
    except
      result := nil;
    end
  finally
    Query.Free;
  end;
end;

end.