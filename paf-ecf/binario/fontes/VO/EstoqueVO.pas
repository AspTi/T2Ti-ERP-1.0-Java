{ *******************************************************************************
  Title: T2Ti ERP
  Description: VO do estoque.

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

  @author Cl?udio de Barros (T2Ti.COM)
  @version 1.0
  ******************************************************************************* }
unit EstoqueVO;

interface

type
  TEstoqueVO = class
  private
    FID: Integer;
    FID_ECF_EMPRESA: Integer;
    FID_ECF_IMPRESSORA: Integer;
    FNUMERO_SERIE_ECF: String;
    FDATA_ATUALIZACAO: String;
    FHORA_ATUALIZACAO: String;
    FHASH_TRIPA: String;
    FHASH_INCREMENTO: Integer;

  published
    property Id: Integer read FID write FID;
    property IdEcfEmpresa: Integer read FID_ECF_EMPRESA write FID_ECF_EMPRESA;
    property IdEcfImpressora: Integer read FID_ECF_IMPRESSORA write FID_ECF_IMPRESSORA;
    property NumeroSerieEcf: String read FNUMERO_SERIE_ECF write FNUMERO_SERIE_ECF;
    property DataAtualizacao: String read FDATA_ATUALIZACAO write FDATA_ATUALIZACAO;
    property HoraAtualizacao: String read FHORA_ATUALIZACAO write FHORA_ATUALIZACAO;
    property HashTripa: String read FHASH_TRIPA write FHASH_TRIPA;
    property HashIncremento: Integer read FHASH_INCREMENTO write FHASH_INCREMENTO;

  end;

implementation

end.
