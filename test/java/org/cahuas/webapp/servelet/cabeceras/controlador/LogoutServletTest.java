package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

class LogoutServletTest {

    private LogoutServlet logoutServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logoutServlet = new LogoutServlet();
    }

    @Test
    void testLogout_WithActiveSession() throws IOException, ServletException {
        // Configurar mocks
        when(request.getSession(false)).thenReturn(session);

        // Ejecutar el método
        logoutServlet.doGet(request, response);

        // Verificaciones
        verify(session).invalidate(); // Verifica que la sesión se invalide
        verify(response).sendRedirect(request.getContextPath() + "/usuario/index.jsp"); // Verifica la redirección
    }

    @Test
    void testLogout_WithoutActiveSession() throws IOException, ServletException {
        // Configurar mocks
        when(request.getSession(false)).thenReturn(null);

        // Ejecutar el método
        logoutServlet.doGet(request, response);

        // Verificaciones
        verify(session, never()).invalidate(); // Verifica que no se invalide ninguna sesión
        verify(response).sendRedirect(request.getContextPath() + "/usuario/index.jsp"); // Verifica la redirección
    }
}
