package com.t2tierp.tributacao.servidor;

import com.t2tierp.padrao.java.Constantes;
import com.t2tierp.padrao.servidor.HibernateUtil;
import com.t2tierp.tributacao.java.TributCofinsCodApuracaoVO;
import com.t2tierp.tributacao.java.TributConfiguraOfGtVO;
import com.t2tierp.tributacao.java.TributIcmsUfVO;
import com.t2tierp.tributacao.java.TributIpiDipiVO;
import com.t2tierp.tributacao.java.TributPisCodApuracaoVO;
import java.util.List;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.server.Action;
import org.openswing.swing.server.UserSessionParameters;

/**
 * <p>Title: T2Ti ERP</p>
 * <p>Description: Classe que realiza a persistĂȘncia dos dados de TributConfiguraOfGtDetalhe.</p>
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
public class TributConfiguraOfGtDetalheAction implements Action {

    public TributConfiguraOfGtDetalheAction() {
    }

    public String getRequestName() {
        return "tributConfiguraOfGtDetalheAction";
    }

    public Response executeCommand(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Object[] pars = (Object[]) inputPar;
        Integer acao = (Integer) pars[0];

        switch (acao) {
            case Constantes.LOAD: {
                return load(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.INSERT: {
                return insert(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.UPDATE: {
                return update(inputPar, userSessionPars, request, response, userSession, context);
            }
            case Constantes.DELETE: {
                return delete(inputPar, userSessionPars, request, response, userSession, context);
            }
        }
        return null;
    }

    private Response load(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        Object[] pars = (Object[]) inputPar;
        String pk = (String) pars[1];

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(TributConfiguraOfGtVO.class);
            criteria.add(Restrictions.eq("id", Integer.valueOf(pk)));

            TributConfiguraOfGtVO tributConfiguraOfGt = (TributConfiguraOfGtVO) criteria.uniqueResult();

            return new VOResponse(tributConfiguraOfGt);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception ex1) {
            }
        }
    }

    public Response insert(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            TributConfiguraOfGtVO tributConfiguraOfGt = (TributConfiguraOfGtVO) pars[1];
            List<TributIcmsUfVO> icmsUf = (Vector) pars[2];
            TributPisCodApuracaoVO pisCodApuracao = (TributPisCodApuracaoVO) pars[3];
            TributCofinsCodApuracaoVO cofinsCodApuracao = (TributCofinsCodApuracaoVO) pars[4];
            TributIpiDipiVO ipiDipi = (TributIpiDipiVO) pars[5];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.save(tributConfiguraOfGt);

            for (int i = 0; i < icmsUf.size(); i++) {
                icmsUf.get(i).setTributConfiguraOfGt(tributConfiguraOfGt);
                session.save(icmsUf.get(i));
            }

            pisCodApuracao.setTributConfiguraOfGt(tributConfiguraOfGt);
            session.save(pisCodApuracao);

            cofinsCodApuracao.setTributConfiguraOfGt(tributConfiguraOfGt);
            session.save(cofinsCodApuracao);

            ipiDipi.setTributConfiguraOfGt(tributConfiguraOfGt);
            if (ipiDipi.getTipoReceitaDipi().getId() == null){
                ipiDipi.setTipoReceitaDipi(null);
            }
            session.save(ipiDipi);

            session.getTransaction().commit();

            return new VOResponse(tributConfiguraOfGt);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public Response update(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        Session session = null;
        try {
            Object[] pars = (Object[]) inputPar;
            TributConfiguraOfGtVO tributConfiguraOfGt = (TributConfiguraOfGtVO) pars[2];
            List<TributIcmsUfVO> icmsUf = (Vector) pars[3];
            TributPisCodApuracaoVO pisCodApuracao = (TributPisCodApuracaoVO) pars[4];
            TributCofinsCodApuracaoVO cofinsCodApuracao = (TributCofinsCodApuracaoVO) pars[5];
            TributIpiDipiVO ipiDipi = (TributIpiDipiVO) pars[6];

            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            session.update(tributConfiguraOfGt);

            String sqlExcluir = "delete from TRIBUT_ICMS_UF where ID not in (0";
            for (int i = 0; i < icmsUf.size(); i++) {
                icmsUf.get(i).setTributConfiguraOfGt(tributConfiguraOfGt);
                session.saveOrUpdate(icmsUf.get(i));
                sqlExcluir += "," + icmsUf.get(i).getId();
            }
            if (!icmsUf.isEmpty()) {
                sqlExcluir += ") and ID_TRIBUT_CONFIGURA_OF_GT = :id";
                Query query = session.createSQLQuery(sqlExcluir);
                query.setInteger("id", tributConfiguraOfGt.getId());
                query.executeUpdate();
            }

            session.saveOrUpdate(pisCodApuracao);

            session.saveOrUpdate(cofinsCodApuracao);

            session.getTransaction().commit();

            ipiDipi.setTributConfiguraOfGt(tributConfiguraOfGt);
            if (ipiDipi.getTipoReceitaDipi().getId() == null){
                ipiDipi.setTipoReceitaDipi(null);
            }
            session.saveOrUpdate(ipiDipi);

            return new VOResponse(tributConfiguraOfGt);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
            return new ErrorResponse(ex.getMessage());
        } finally {
            try {
                if (session != null) {
                    session.close();
                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
            }
        }
    }

    public Response delete(Object inputPar, UserSessionParameters userSessionPars, HttpServletRequest request, HttpServletResponse response, HttpSession userSession, ServletContext context) {
        return null;
    }
}
