package br.com.zitrus.procedurecontrol.servlet;

import br.com.zitrus.procedurecontrol.dao.AuthorizationDao;
import br.com.zitrus.procedurecontrol.dao.ProcedureDao;
import br.com.zitrus.procedurecontrol.dao.RuleDao;
import br.com.zitrus.procedurecontrol.enums.AuthorizationEnum;
import br.com.zitrus.procedurecontrol.enums.GenderEnum;
import br.com.zitrus.procedurecontrol.model.Authorization;
import br.com.zitrus.procedurecontrol.model.Proceduresql;
import br.com.zitrus.procedurecontrol.service.impl.AuthorizationServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import static org.mockito.Mockito.*;


class AuthorizationServletTest {

    @Mock
    private Connection connection;

    @Mock
    private AuthorizationDao authorizationDAO;

    @Mock
    private ProcedureDao procedureDAO;

    @Mock
    private RuleDao ruleDAO;

    @InjectMocks
    private AuthorizationServiceImpl service;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private AuthorizationServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoGet_UpdateAuthorizationAction() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("updateAuthorization");
        when(request.getParameter("authorizationid")).thenReturn("1");
        Proceduresql proceduresql = new Proceduresql(2L, "1234");
        when(authorizationDAO.findAuthorizationById(1L)).thenReturn(new Authorization("leo", proceduresql, AuthorizationEnum.NOT_ALLOWED, 18, GenderEnum.MALE));
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        service.handleAuthorizationAction(request, response);

        verify(authorizationDAO, times(1)).findAuthorizationById(1L);
        verify(request).setAttribute(eq("authorization"), any(Authorization.class));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_CreateAuthorization() throws ServletException, IOException {
        when(request.getParameter("proceduresql")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Joao");
        when(request.getParameter("age")).thenReturn("25");
        when(request.getParameter("gender")).thenReturn("MALE");
        when(request.getParameter("id")).thenReturn(null);

        when(procedureDAO.findProcedureById(1L)).thenReturn(new Proceduresql(1L, "leo"));
        when(ruleDAO.validateAuthorization(any(Authorization.class))).thenReturn(true);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        service.processAuthorization(request, response);

        verify(request, times(5)).getParameter(anyString());
        verify(procedureDAO, times(1)).findProcedureById(anyLong());
        verify(ruleDAO, times(1)).validateAuthorization(any(Authorization.class));
        verify(authorizationDAO, times(1)).createAuthorization(any(Authorization.class), anyBoolean());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost_UpdateAuthorization() throws ServletException, IOException {
        when(request.getParameter("proceduresql")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("Updated Name");
        when(request.getParameter("age")).thenReturn("30");
        when(request.getParameter("gender")).thenReturn("FEMALE");
        when(request.getParameter("id")).thenReturn("2");

        when(procedureDAO.findProcedureById(1L)).thenReturn(new Proceduresql(1L, "leo"));
        when(ruleDAO.validateAuthorization(any(Authorization.class))).thenReturn(true);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        service.processAuthorization(request, response);
        verify(procedureDAO, times(1)).findProcedureById(1L);
        verify(ruleDAO, times(1)).validateAuthorization(any(Authorization.class));
        verify(authorizationDAO, times(1)).updateAuthorization(any(Authorization.class), eq(true));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testLoadSolicitationForEditing() {
        Proceduresql proceduresql = new Proceduresql(2L, "1234");
        when(authorizationDAO.findAuthorizationById(1L)).thenReturn(new Authorization("leo", proceduresql, AuthorizationEnum.NOT_ALLOWED, 18, GenderEnum.MALE));

        service.loadSolicitationForEditing(1L, request);

        verify(request).setAttribute(eq("authorization"), any(Authorization.class));
    }

    @Test
    void testLoadIndex() throws ServletException, IOException {
        List<Proceduresql> procedures = List.of(new Proceduresql(2L, "1234"));
        GenderEnum[] genders = GenderEnum.values();
        Proceduresql proceduresql = new Proceduresql(2L, "1234");
        List<Authorization> authorizations = List.of(new Authorization("leo", proceduresql, AuthorizationEnum.NOT_ALLOWED, 18, GenderEnum.MALE));

        when(procedureDAO.getAllProcedure()).thenReturn(procedures);
        when(authorizationDAO.findAll()).thenReturn(authorizations);
        when(request.getRequestDispatcher("index.jsp")).thenReturn(requestDispatcher);

        service.loadIndex(request, response);

        verify(request).setAttribute("procedures", procedures);
        verify(request).setAttribute("genders", genders);
        verify(request).setAttribute("authorizations", authorizations);
        verify(requestDispatcher).forward(request, response);
    }
}
