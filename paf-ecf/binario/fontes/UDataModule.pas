{*******************************************************************************
Title: T2Ti ERP
Description: Datamodule.

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
unit UDataModule;

interface

uses
SysUtils, Forms, ACBrDevice, ACBrBase, ACBrECF, DBXMySql, FMTBcd,
Provider, DBClient, DB, SqlExpr, Classes, WideStrings, StdCtrls, Controls,
Windows, ACBrUtil, dateutils, strutils, dialogs,
ACBrPAF, ACBrPAF_D, ACBrPAF_E, ACBrPAF_P, ACBrPAF_R, ACBrPAF_T, ACBrPAFRegistros,
Math, ACBrSpedFiscal, ACBrSintegra, ConfiguracaoVO, ConfiguracaoController,
  ACBrECFClass;

type
  TFDataModule = class(TDataModule)
    ACBrECF: TACBrECF;
    ACBrPAF: TACBrPAF;
    ACBrSintegra: TACBrSintegra;
    ACBrSPEDFiscal: TACBrSPEDFiscal;
    Conexao: TSQLConnection;
    procedure ACBrPAFPAFCalcEAD(Arquivo: string);
    procedure DataModuleCreate(Sender: TObject);
    procedure ACBrECFMsgPoucoPapel(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    QuantidadeECF: Integer;
    EmpresaID: Integer;
    IdEmpresa: String;
    IdCaixa: String;
    IdOperador: String;
    IdImpressora: String;
    Configuracao: TConfiguracaoVO;
    ModeloAcbr: String;
  end;

var
  FDataModule: TFDataModule;

implementation

uses UPAF, USintegra, USpedFiscal;

{$R *.dfm}

procedure TFDataModule.ACBrECFMsgPoucoPapel(Sender: TObject);
begin
//
end;

procedure TFDataModule.ACBrPAFPAFCalcEAD(Arquivo: string);
begin
//
end;

procedure TFDataModule.DataModuleCreate(Sender: TObject);
begin
  { ***  PARAMETROS  ***
    01 - o m?todo que ser? chamado
    02 - id da empresa
    03 - id do caixa
    04 - id do operador
    05 - id da impressora
    06 - porta acbr
    07 - timeout acbr
    08 - ACBrECF.IntervaloAposComando
    09 - ACBrECF.Modelo

    10 - data inicio
    11 - data fim

    12 - GeraMovimentoECF - data do movimento

    12 - GerarArquivoSintegra - pCodigoConvenio
    13 - GerarArquivoSintegra - pNaturezaInformacao
    14 - GerarArquivoSintegra - pFinalidadeArquivo

    12 - GerarArquivoSpedFiscal - pVersao
    13 - GerarArquivoSpedFiscal - pFinalidade
    14 - GerarArquivoSpedFiscal - pPerfil

    10 - LMFCCRZ - primeiroCRZ
    11 - LMFCCRZ - ultimoCRZ

    10 - LMFSCRZ - primeiroCRZ
    11 - LMFSCRZ - ultimoCRZ

    10 - EspelhoMFDCOO - primeiroCOO
    11 - EspelhoMFDCOO - ultimoCOO

    10 - ArquivoMFDCOO - primeiroCOO
    11 - ArquivoMFDCOO - ultimoCOO

    10 - EstoqueParcialCodigo - primeiroCodigo
    11 - EstoqueParcialCodigo - ultimoCodigo

    10 - EstoqueParcialDescricao - parte01
    11 - EstoqueParcialDescricao - parte02

    10 - GeraMD5 - Arquivo1
    11 - GeraMD5 - Arquivo2
    12 - GeraMD5 - Arquivo3

  }

  QuantidadeECF := 1;
  Configuracao := TConfiguracaoController.PegaConfiguracao;
  //
  {FDataModule.EmpresaId := 1;
  FDataModule.IdEmpresa := '1';
  FDataModule.IdCaixa := '1';
  FDataModule.IdOperador := '1';
  FDataModule.IdImpressora := '2';
  FDataModule.ACBrECF.Porta := 'COM1';
  FDataModule.ACBrECF.TimeOut := 10;
  FDataModule.ACBrECF.IntervaloAposComando := 250;
  FDataModule.ACBrECF.Modelo := TACBrECFModelo(4);}
  //FDataModule.ACBrECF.Device.Baud := 115200;


  FDataModule.EmpresaId := StrToInt(ParamStr(2));
  FDataModule.IdEmpresa := ParamStr(2);
  FDataModule.IdCaixa := ParamStr(3);
  FDataModule.IdOperador := ParamStr(4);
  FDataModule.IdImpressora := ParamStr(5);
  FDataModule.ACBrECF.Porta := ParamStr(6);
  FDataModule.ACBrECF.TimeOut := StrToInt(ParamStr(7));
  FDataModule.ACBrECF.IntervaloAposComando := StrToInt(ParamStr(8));
  FDataModule.ACBrECF.Modelo := TACBrECFModelo(StrToInt(ParamStr(9)));

  if (ParamStr(1) <> 'geraMovimentoECF') And (ParamStr(1) <> 'DAVEmitidos')
      And (ParamStr(1) <> 'EstoqueTotal') And (ParamStr(1) <> 'EstoqueParcialCodigo')
      And (ParamStr(1) <> 'EstoqueParcialDescricao') And (ParamStr(1) <> 'geraTabelaProdutos')
      And (ParamStr(1) <> 'gerarArquivoSintegra') And (ParamStr(1) <> 'gerarArquivoSpedFiscal')
      And (ParamStr(1) <> 'ArquivoMD5') then
  begin
    FDataModule.ACBrECF.Ativar;
    FDataModule.ACBrECF.CarregaAliquotas;
    FDataModule.ACBrECF.CarregaFormasPagamento;
  end;

  if ParamStr(1) = 'geraMovimentoECF' then
    UPaf.GeraMovimentoECF(ParamStr(10),ParamStr(11),ParamStr(12),StrToInt(IdImpressora))
  else if ParamStr(1) = 'geraTabelaProdutos' then
    UPaf.GeraTabelaProdutos
  else if ParamStr(1) = 'DAVEmitidos' then
    UPaf.DAVEmitidos(ParamStr(10),ParamStr(11))
  else if ParamStr(1) = 'gerarArquivoSintegra' then
    USintegra.GerarArquivoSintegra(ParamStr(10),ParamStr(11),StrToInt(ParamStr(12)),StrToInt(ParamStr(13)),StrToInt(ParamStr(14)))
  else if ParamStr(1) = 'gerarArquivoSpedFiscal' then
    USpedFiscal.GerarArquivoSpedFiscal(ParamStr(10),ParamStr(11),StrToInt(ParamStr(12)),StrToInt(ParamStr(13)),StrToInt(ParamStr(14)))
  else if ParamStr(1) = 'ImprimeParametrosConfiguracao' then
    UPaf.ParametrodeConfiguracao
  else if ParamStr(1) = 'ImprimeIdentificacaoPaf' then
    UPaf.IdentificacaoPafEcf
  else if ParamStr(1) = 'LMFC' then
    FDataModule.ACBrECF.LeituraMemoriaFiscal(StrToDate(ParamStr(10)),StrToDate(ParamStr(11)), False)
  else if ParamStr(1) = 'LMFCArquivo' then
    UPaf.LMFCArquivo(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'LMFCCRZ' then
    FDataModule.ACBrECF.LeituraMemoriaFiscal(StrToInt(ParamStr(10)),StrToInt(ParamStr(11)),False)
  else if ParamStr(1) = 'LMFCCRZArquivo' then
    UPaf.LMFCCRZArquivo(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'LMFS' then
    FDataModule.ACBrECF.LeituraMemoriaFiscal(StrToDate(ParamStr(10)),StrToDate(ParamStr(11)), True)
  else if ParamStr(1) = 'LMFSArquivo' then
    UPaf.LMFSArquivo(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'LMFSCRZ' then
    FDataModule.ACBrECF.LeituraMemoriaFiscal(StrToInt(ParamStr(10)),StrToInt(ParamStr(11)),True)
  else if ParamStr(1) = 'LMFSCRZArquivo' then
    UPaf.LMFSCRZArquivo(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'EspelhoMFDData' then
    UPaf.EspelhoMFDData(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'EspelhoMFDCOO' then
    UPaf.EspelhoMFDCOO(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'ArquivoMFDData' then
    UPaf.ArquivoMFDData(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'ArquivoMFDCOO' then
    UPaf.ArquivoMFDCOO(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'EstoqueTotal' then
    UPaf.GeraArquivoEstoque
  else if ParamStr(1) = 'EstoqueParcialCodigo' then
    UPaf.GeraArquivoEstoque(StrToInt(ParamStr(10)), StrToInt(ParamStr(11)))
  else if ParamStr(1) = 'EstoqueParcialDescricao' then
    UPaf.GeraArquivoEstoque(ParamStr(10), ParamStr(11))
  else if ParamStr(1) = 'ArquivoMD5' then
    UPaf.GeraMD5(ParamStr(10), ParamStr(11), ParamStr(12))
  else if ParamStr(1) = 'DadosReduzaoZ' then
    begin
      UPaf.DadosReducaoZ(ParamStr(3), ParamStr(4), ParamStr(5));
      UPaf.GeraMovimentoECF(ParamStr(10),ParamStr(11),ParamStr(12),StrToInt(IdImpressora))
    end;

  if (ParamStr(1) <> 'geraMovimentoECF') And (ParamStr(1) <> 'DAVEmitidos')
   And (ParamStr(1) <> 'EstoqueTotal') And (ParamStr(1) <> 'EstoqueParcialCodigo')
   And (ParamStr(1) <> 'EstoqueParcialDescricao') And (ParamStr(1) <> 'geraTabelaProdutos')
   And (ParamStr(1) <> 'gerarArquivoSintegra') And (ParamStr(1) <> 'gerarArquivoSpedFiscal')
   And (ParamStr(1) <> 'ArquivoMD5') then
  begin
    FDataModule.ACBrECF.Desativar;
  end;

end;

end.
