package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class NewPasswordTest {

    private NewPassword newPasswordServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        newPasswordServlet = new NewPassword();
    }

    @Test
    void testPasswordReset_Success() throws Exception {
        // Simular parámetros de solicitud y sesión
        when(request.getParameter("password")).thenReturn("newPassword123");
        when(request.getParameter("confPassword")).thenReturn("newPassword123");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");

        // Simular conexión y SQL
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Ejecutar el servlet
        newPasswordServlet.doPost(request, response);

        // Verificar acciones
        verify(session).setAttribute("status", "resetSuccess");
        verify(response).sendRedirect("/webbs/usuario/login.jsp");
    }

    @Test
    void testPasswordReset_Failure() throws Exception {
        // Simular parámetros de solicitud y sesión
        when(request.getParameter("password")).thenReturn("newPassword123");
        when(request.getParameter("confPassword")).thenReturn("newPassword123");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");

        // Simular conexión y SQL
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(0); // Fallo en la actualización

        // Ejecutar el servlet
        newPasswordServlet.doPost(request, response);

        // Verificar acciones
        verify(session).setAttribute("status", "resetFailed");
        verify(response).sendRedirect("/webbs/usuario/login.jsp");
    }

    @Test
    void testPasswordMismatch() throws Exception {
        // Simular parámetros de solicitud
        when(request.getParameter("password")).thenReturn("password123");
        when(request.getParameter("confPassword")).thenReturn("differentPassword123");
        when(request.getRequestDispatcher("/usuario/newPassword.jsp")).thenReturn(dispatcher);

        // Ejecutar el servlet
        newPasswordServlet.doPost(request, response);

        // Verificar acciones
        verify(request).setAttribute("status", "passwordMismatch");
        verify(dispatcher).forward(request, response);
    }

    @Test
    void testExceptionHandling() throws Exception {
        // Simular parámetros de solicitud y sesión
        when(request.getParameter("password")).thenReturn("newPassword123");
        when(request.getParameter("confPassword")).thenReturn("newPassword123");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("email")).thenReturn("user@example.com");

        // Simular una excepción durante la conexión
        when(ConexionBaseDatos.getConnection()).thenThrow(new SQLException("Database error"));

        // Ejecutar el servlet
        newPasswordServlet.doPost(request, response);

        // Verificar acciones
        verify(session).setAttribute("status", "resetFailed");
        verify(response).sendRedirect("/webbs/usuario/login.jsp");
    }
}