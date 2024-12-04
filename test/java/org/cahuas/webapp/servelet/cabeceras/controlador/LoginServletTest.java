package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.VentaServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.ClienteServiceJdbcImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.*;

class LoginServletTest {

    private LoginServlet loginServlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private Connection connection;
    @Mock
    private LoginServiceJdbcImpl loginService;
    @Mock
    private VentaServiceJdbcImpl ventaService;
    @Mock
    private ClienteServiceJdbcImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginServlet = new LoginServlet();
    }

    @Test
    void testLogin_AdminUser() throws Exception {
        // Datos simulados
        String username = "adminUser";
        String password = "adminPass";
        Usuario mockAdminUser = new Usuario(1, username, password, "admin");

        // Configurar mocks
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(loginService.UsuarioSesion(username, password)).thenReturn(mockAdminUser);
        when(ventaService.obtenerHistorialVentas(1)).thenReturn(Collections.emptyList());

        // Ejecutar método
        loginServlet.doPost(request, response);

        // Verificaciones
        verify(session).setAttribute("username", username);
        verify(session).setAttribute("usuario", mockAdminUser);
        verify(session).setAttribute("historialCompras", Collections.emptyList());
        verify(response).sendRedirect(request.getContextPath() + "/admin/index.jsp");
    }

    @Test
    void testLogin_RegularUser() throws Exception {
        // Datos simulados
        String username = "regularUser";
        String password = "userPass";
        Usuario mockUser = new Usuario(2, username, password, "usu");

        // Configurar mocks
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(loginService.UsuarioSesion(username, password)).thenReturn(mockUser);
        when(clienteService.buscarPorUsuarioId(2)).thenReturn(null); // Sin cliente
        when(ventaService.obtenerHistorialVentas(2)).thenReturn(Collections.emptyList());

        // Ejecutar método
        loginServlet.doPost(request, response);

        // Verificaciones
        verify(session).setAttribute("username", username);
        verify(session).setAttribute("usuario", mockUser);
        verify(session).setAttribute("cliente", null);
        verify(session).setAttribute("historialCompras", Collections.emptyList());
        verify(response).sendRedirect(request.getContextPath() + "/usuario/index.jsp");
    }

    @Test
    void testLogin_InvalidCredentials() throws Exception {
        // Configurar mocks
        String username = "invalidUser";
        String password = "wrongPass";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(loginService.UsuarioSesion(username, password)).thenReturn(null);

        // Ejecutar método
        loginServlet.doPost(request, response);

        // Verificar redirección al login
        verify(response).sendRedirect(request.getContextPath() + "/usuario/login.jsp");
    }

    @Test
    void testLogin_SQLException() throws Exception {
        // Configurar mocks
        String username = "errorUser";
        String password = "errorPass";
        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(loginService.UsuarioSesion(username, password)).thenThrow(new SQLException("Database error"));

        // Ejecutar método
        loginServlet.doPost(request, response);

        // Verificar redirección a página de error
        verify(response).sendRedirect(request.getContextPath() + "/usuario/index.jsp");
    }
}
