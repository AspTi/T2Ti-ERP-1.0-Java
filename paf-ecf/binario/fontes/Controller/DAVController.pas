{*******************************************************************************
Title: T2Ti ERP
Description: Classe de controle do DAV.

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

@author Albert Eije (T2Ti.COM)
@version 1.0
*******************************************************************************}
unit DAVController;

interface

uses
  Classes, SQLExpr, SysUtils, DAVDetalheVO, Generics.Collections, DB, Biblioteca,
  dialogs, DavCabecalhoVO;

type
  TDAVController = class
  protected
  public
    class function ListaDAVPeriodo(DataInicio:String; DataFim:String): TObjectList<TDavCabecalhoVO>;
    class function ConsultaDAVId(Id: Integer): TDavCabecalhoVO;
    class function ListaDavDetalhe(Id: Integer): TObjectList<TDavDetalheVO>;
  end;

implementation

uses UDataModule, ClienteVO, Constantes;

var
  ConsultaSQL: String;
  Query: TSQLQuery;

class function TDAVController.ListaDAVPeriodo(DataInicio:String; DataFim:String): TObjectList<TDavCabecalhoVO>;
var
  ListaDAV: TObjectList<TDavCabecalhoVO>;
  DAVCabecalho: TDavCabecalhoVO;
  TotalRegistros: Integer;
begin
  ConsultaSQL :=
    'select count(*) AS TOTAL from DAV_CABECALHO where SITUACAO =' + QuotedStr('E') + ' and (DATA_EMISSAO between :pDataInicio and :pDataFim)';
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.ParamByName('pDataInicio').AsDateTime := StrToDate(DataInicio);
      Query.ParamByName('pDataFim').AsDateTime := StrToDate(DataFim);
      Query.Open;

      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;
      if TotalRegistros > 0 then
      begin
        ListaDAV := TObjectList<TDavCabecalhoVO>.Create;
        ConsultaSQL :=
       'select * from DAV_CABECALHO where SITUACAO =' + QuotedStr('E') + ' and (DATA_EMISSAO between :pDataInicio and :pDataFim)';
        Query.sql.Text := ConsultaSQL;
        Query.ParamByName('pDataInicio').AsDateTime := StrToDate(DataInicio);
        Query.ParamByName('pDataFim').AsDateTime := StrToDate(DataFim);
        Query.Open;
        Query.First;
        while not Query.Eof do
        begin
          DAVCabecalho := TDavCabecalhoVO.Create;
          DAVCabecalho.Id := Query.FieldByName('ID').AsInteger;
          DAVCabecalho.IdPessoa := Query.FieldByName('ID_PESSOA').AsInteger;
          DAVCabecalho.CCF := Query.FieldByName('CCF').AsInteger;
          DAVCabecalho.COO := Query.FieldByName('COO').AsInteger;
          DAVCabecalho.NumeroDav := Query.FieldByName('NUMERO_DAV').AsString;
          DAVCabecalho.NumeroEcf := Query.FieldByName('NUMERO_ECF').AsString;
          DAVCabecalho.NomeDestinatario := Query.FieldByName('NOME_DESTINATARIO').AsString;
          DAVCabecalho.CpfCnpjDestinatario := Query.FieldByName('CPF_CNPJ_DESTINATARIO').AsString;
          DAVCabecalho.DataEmissao := Query.FieldByName('DATA_EMISSAO').AsString;
          DAVCabecalho.HoraEmissao := Query.FieldByName('HORA_EMISSAO').AsString;
          DAVCabecalho.Situacao := Query.FieldByName('SITUACAO').AsString;
          DAVCabecalho.TaxaAcrescimo := Query.FieldByName('TAXA_ACRESCIMO').AsFloat;
          DAVCabecalho.Acrescimo := Query.FieldByName('ACRESCIMO').AsFloat;
          DAVCabecalho.TaxaDesconto := Query.FieldByName('TAXA_DESCONTO').AsFloat;
          DAVCabecalho.Desconto := Query.FieldByName('DESCONTO').AsFloat;
          DAVCabecalho.SubTotal := Query.FieldByName('SUBTOTAL').AsFloat;
          DAVCabecalho.Valor := Query.FieldByName('VALOR').AsFloat;
          DAVCabecalho.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
          DAVCabecalho.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
          ListaDAV.Add(DAVCabecalho);
          Query.next;
        end;
        result := ListaDAV;
      end
      // caso n?o exista a relacao, retorna um ponteiro nulo
      else
        result := nil;
    finally
      Query.Free;
    end;
  except
    result := nil;
  end;
end;

class function TDAVController.ConsultaDAVId(Id:Integer): TDavCabecalhoVO;
var
  DAVCabecalho: TDavCabecalhoVO;
begin
  ConsultaSQL :=
    'select * from DAV_CABECALHO where ID = ' + IntToStr(Id);

  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      Query.First;

      DAVCabecalho := TDavCabecalhoVO.Create;

      DAVCabecalho.Id := Query.FieldByName('ID').AsInteger;
      DAVCabecalho.IdPessoa := Query.FieldByName('ID_PESSOA').AsInteger;
      DAVCabecalho.CCF := Query.FieldByName('CCF').AsInteger;
      DAVCabecalho.COO := Query.FieldByName('COO').AsInteger;
      DAVCabecalho.NumeroDav := Query.FieldByName('NUMERO_DAV').AsString;
      DAVCabecalho.NumeroEcf := Query.FieldByName('NUMERO_ECF').AsString;
      DAVCabecalho.NomeDestinatario := Query.FieldByName('NOME_DESTINATARIO').AsString;
      DAVCabecalho.CpfCnpjDestinatario := Query.FieldByName('CPF_CNPJ_DESTINATARIO').AsString;
      DAVCabecalho.DataEmissao := Query.FieldByName('DATA_EMISSAO').AsString;
      DAVCabecalho.HoraEmissao := Query.FieldByName('HORA_EMISSAO').AsString;
      DAVCabecalho.Situacao := Query.FieldByName('SITUACAO').AsString;
      DAVCabecalho.TaxaAcrescimo := Query.FieldByName('TAXA_ACRESCIMO').AsFloat;
      DAVCabecalho.Acrescimo := Query.FieldByName('ACRESCIMO').AsFloat;
      DAVCabecalho.TaxaDesconto := Query.FieldByName('TAXA_DESCONTO').AsFloat;
      DAVCabecalho.Desconto := Query.FieldByName('DESCONTO').AsFloat;
      DAVCabecalho.SubTotal := Query.FieldByName('SUBTOTAL').AsFloat;
      DAVCabecalho.Valor := Query.FieldByName('VALOR').AsFloat;
      DAVCabecalho.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
      DAVCabecalho.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;

      result := DAVCabecalho;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;

end;

class function TDAVController.ListaDavDetalhe(Id: Integer): TObjectList<TDavDetalheVO>;
var
  ListaDavDetalhe: TObjectList<TDavDetalheVO>;
  DavDetalhe: TDavDetalheVO;
  TotalRegistros: Integer;
begin
  //verifica se existem itens para o ID passado
  ConsultaSQL :=
    'select count(*) AS TOTAL from DAV_DETALHE where ID_DAV_CABECALHO = ' + IntToStr(Id);
  try
    try
      Query := TSQLQuery.Create(nil);
      Query.SQLConnection := FDataModule.Conexao;
      Query.sql.Text := ConsultaSQL;
      Query.Open;
      TotalRegistros := Query.FieldByName('TOTAL').AsInteger;

      if TotalRegistros > 0 then
      begin
        ListaDavDetalhe := TObjectList<TDavDetalheVO>.Create;
        ConsultaSQL :=
          'select * ' +
          ' from DAV_DETALHE '+
          ' where ID_DAV_CABECALHO = ' + IntToStr(Id);
        Query.sql.Text := ConsultaSQL;
        Query.Open;
        Query.First;
        while not Query.Eof do
        begin
          DavDetalhe := TDavDetalheVO.Create;
          DavDetalhe.Id := Query.FieldByName('ID').AsInteger;
          DavDetalhe.IdDavCabecalho := Query.FieldByName('ID_DAV_CABECALHO').AsInteger;
          DavDetalhe.IdProduto := Query.FieldByName('ID_PRODUTO').AsInteger;
          DavDetalhe.NumeroDav := Query.FieldByName('NUMERO_DAV').AsString;
          DavDetalhe.DataEmissao := Query.FieldByName('DATA_EMISSAO').AsString;
          DavDetalhe.GtinProduto := Query.FieldByName('GTIN_PRODUTO').AsString;
          DavDetalhe.NomeProduto := Query.FieldByName('NOME_PRODUTO').AsString;
          DavDetalhe.UnidadeProduto := Query.FieldByName('UNIDADE_PRODUTO').AsString;
          DavDetalhe.IdProduto := Query.FieldByName('ID_PRODUTO').AsInteger;
          DavDetalhe.Item := Query.FieldByName('ITEM').AsInteger;
          DavDetalhe.Quantidade := Query.FieldByName('QUANTIDADE').AsFloat;
          DavDetalhe.ValorUnitario := Query.FieldByName('VALOR_UNITARIO').AsFloat;
          DavDetalhe.ValorTotal := Query.FieldByName('VALOR_TOTAL').AsFloat;
          DavDetalhe.Cancelado := Query.FieldByName('CANCELADO').AsString;
          DavDetalhe.MesclaProduto := Query.FieldByName('MESCLA_PRODUTO').AsString;
          DavDetalhe.TotalizadorParcial := Query.FieldByName('TOTALIZADOR_PARCIAL').AsString;
          DavDetalhe.HashTripa := Query.FieldByName('HASH_TRIPA').AsString;
          DavDetalhe.HashIncremento := Query.FieldByName('HASH_INCREMENTO').AsInteger;
          ListaDavDetalhe.Add(DavDetalhe);
          Query.next;
        end;
        result := ListaDavDetalhe;
      end
      //caso n?o exista a relacao, retorna um ponteiro nulo
      else
        result := nil;
    except
      result := nil;
    end;
  finally
    Query.Free;
  end;
end;

end.
