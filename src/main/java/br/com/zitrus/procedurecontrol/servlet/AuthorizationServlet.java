package br.com.zitrus.procedurecontrol.servlet;

import br.com.zitrus.procedurecontrol.dao.impl.AuthorizationDaoImpl;
import br.com.zitrus.procedurecontrol.dao.impl.ProcedureDaoImpl;
import br.com.zitrus.procedurecontrol.dao.impl.RuleDaoImpl;
import br.com.zitrus.procedurecontrol.infra.ConnectionFactory;
import br.com.zitrus.procedurecontrol.service.AuthorizationService;
import br.com.zitrus.procedurecontrol.service.impl.AuthorizationServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/control")
@RequiredArgsConstructor
public class AuthorizationServlet extends HttpServlet {

    AuthorizationService service;
    AuthorizationDaoImpl authorizationDAO;
    ProcedureDaoImpl procedureDAO;
    RuleDaoImpl ruleDAO;

    public AuthorizationServlet(AuthorizationService service) {
        this.service = service;
    }

    @Override
    public void init() {
        Connection connection = ConnectionFactory.getConnection();
        authorizationDAO = new AuthorizationDaoImpl(connection);
        procedureDAO = new ProcedureDaoImpl(connection);
        ruleDAO = new RuleDaoImpl(connection);
        service = new AuthorizationServiceImpl(authorizationDAO, ruleDAO, procedureDAO);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        service.processAuthorization(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        service.handleAuthorizationAction(request, response);
    }

}