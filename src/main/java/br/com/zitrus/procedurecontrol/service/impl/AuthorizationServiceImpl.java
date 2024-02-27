package br.com.zitrus.procedurecontrol.service.impl;

import br.com.zitrus.procedurecontrol.dao.AuthorizationDao;
import br.com.zitrus.procedurecontrol.dao.ProcedureDao;
import br.com.zitrus.procedurecontrol.dao.RuleDao;
import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import br.com.zitrus.procedurecontrol.model.Authorization;
import br.com.zitrus.procedurecontrol.model.Proceduresql;
import br.com.zitrus.procedurecontrol.service.AuthorizationService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class AuthorizationServiceImpl implements AuthorizationService {

    private final AuthorizationDao authorizationDAO;
    private final RuleDao ruleDAO;
    private final ProcedureDao procedureDAO;

    public AuthorizationServiceImpl(AuthorizationDao authorizationDAO, RuleDao ruleDAO, ProcedureDao procedureDAO) {
        this.authorizationDAO = authorizationDAO;
        this.ruleDAO = ruleDAO;
        this.procedureDAO = procedureDAO;
    }

    @Override
    public void processAuthorization(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Authorization authorization = authorizationFromRequest(request);
            boolean validatedAuthorization = ruleDAO.validateAuthorization(authorization);
            saveOrUpdateAuthorization(request, authorization, validatedAuthorization);
            loadIndex(request, response);
        } catch (ServletException e) {
            throw new ServletException("Data to load index.jsp not found", e);
        }
    }

    private Authorization authorizationFromRequest(HttpServletRequest request) {
        String proceduresqlParam = request.getParameter("proceduresql");
        String name = request.getParameter("name");
        String ageParam = request.getParameter("age");
        String genderParam = request.getParameter("gender");

        Long proceduresqlId = Long.parseLong(proceduresqlParam);
        int age = Integer.parseInt(ageParam);
        GenderEnum genderEnum = GenderEnum.fromValue(genderParam);

        Proceduresql proceduresql = procedureDAO.findProcedureById(proceduresqlId);

        return new Authorization(name, proceduresql, AuthorizationEnum.NOT_ALLOWED, age, genderEnum);
    }

    private void saveOrUpdateAuthorization(HttpServletRequest request, Authorization authorization, boolean validatedAuthorization) {
        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long authorizationId = Long.parseLong(idParam);
            authorization.setId(authorizationId);
            authorizationDAO.updateAuthorization(authorization, validatedAuthorization);
        } else {
            authorizationDAO.createAuthorization(authorization, validatedAuthorization);
        }
    }

    @Override
    public void handleAuthorizationAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    public void loadSolicitationForEditing(Long authorizationId, HttpServletRequest request) {
        Authorization authorization = authorizationDAO.findAuthorizationById(authorizationId);
        request.setAttribute("authorization", authorization);
    }

    public void loadIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            List<Proceduresql> procedures = procedureDAO.getAllProcedure();
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