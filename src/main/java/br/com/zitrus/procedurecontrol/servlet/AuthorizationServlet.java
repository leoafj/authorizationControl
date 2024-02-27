package br.com.zitrus.procedurecontrol.servlet;

import br.com.zitrus.procedurecontrol.dao.AuthorizationDAO;
import br.com.zitrus.procedurecontrol.dao.ProcedureDAO;
import br.com.zitrus.procedurecontrol.dao.RuleDAO;
import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import br.com.zitrus.procedurecontrol.infra.ConnectionFactory;
import br.com.zitrus.procedurecontrol.model.Authorization;
import br.com.zitrus.procedurecontrol.model.Operation;
import br.com.zitrus.procedurecontrol.service.AuthorizationService;
import br.com.zitrus.procedurecontrol.service.impl.AuthorizationServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/control")
public class AuthorizationServlet extends HttpServlet {

    private AuthorizationService service;
    private final AuthorizationDAO authorizationDAO;
    private final ProcedureDAO procedureDAO;
    private final RuleDAO ruleDAO;

    public AuthorizationServlet(AuthorizationService service, AuthorizationDAO authorizationDAO, ProcedureDAO procedureDAO, RuleDAO ruleDAO) {
        this.service = service;
        this.authorizationDAO = authorizationDAO;
        this.procedureDAO = procedureDAO;
        this.ruleDAO = ruleDAO;
    }

    @Override
    public void init() {
        Connection connection = ConnectionFactory.getConnection();
        this.service = new AuthorizationServiceImpl(new AuthorizationDAO(connection), new RuleDAO(connection), new ProcedureDAO(connection));
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            Long authorizationId = Long.parseLong(request.getParameter("authorizationid"));

            switch (action) {
                case "updateAuthorization":
                    loadSolicitationForEditing(authorizationId, request);
                    break;
                case "deleteAuthorization":
                    authorizationDAO.deleteAuthorization(authorizationId);
                    break;
                case "cancelAuthorization":
                    authorizationDAO.cancelAuthorization(authorizationId);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Action" + action);
            }
        }
        loadIndex(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        service.processAuthorization(request, response);
    }
//    public void doPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//        try {
//            String proceduresqlParam = request.getParameter("proceduresql");
//            String name = request.getParameter("name");
//            String ageParam = request.getParameter("age");
//            String genderParam = request.getParameter("gender");
//
//            Long proceduresqlId = Long.parseLong(proceduresqlParam);
//            int age = Integer.parseInt(ageParam);
//            GenderEnum genderEnum = GenderEnum.fromValue(genderParam);
//
//            Operation proceduresql = procedureDAO.findProcedureById(proceduresqlId);
//
//            Authorization authorization = new Authorization(name, proceduresql, AuthorizationEnum.NOT_ALLOWED, age, genderEnum);
//            boolean validatedAuthorization = ruleDAO.validateAuthorization(authorization);
//
//            String idParam = request.getParameter("id");
//            if (idParam != null && !idParam.isEmpty()) {
//                Long authorizationId = Long.parseLong(idParam);
//                authorization.setId(authorizationId);
//                authorizationDAO.updateAuthorization(authorization, validatedAuthorization);
//            } else {
//                authorizationDAO.createAuthorization(authorization, validatedAuthorization);
//            }
//
//            loadIndex(request, response);
//        } catch (ServletException e) {
//            throw new ServletException("Data to load index.jsp not found", e);
//        }
//    }


    public void loadSolicitationForEditing(Long authorizationId, HttpServletRequest request) {
        Authorization authorization = authorizationDAO.findAuthorizationById(authorizationId);
        request.setAttribute("authorization", authorization);
    }


    public void loadIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<Operation> procedures = procedureDAO.getAllProcedure();
            GenderEnum[] genders = GenderEnum.values();
            List<Authorization> authorizations = authorizationDAO.findAll();

            request.setAttribute("procedures", procedures);
            request.setAttribute("genders", genders);
            request.setAttribute("authorizations", authorizations);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Data to load index.jsp not found", e);
        }
    }

}