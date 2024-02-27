package br.com.zitrus.procedurecontrol.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthorizationService {

    void processAuthorization(HttpServletRequest request, HttpServletResponse response) throws ServletException;

    void handleAuthorizationAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}