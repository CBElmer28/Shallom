package org.cahuas.webapp.servelet.cabeceras.controlador;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.ClienteServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class configUsuarioServeletTest {

    private configUsuarioServelet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private ClienteServiceJdbcImpl clienteService;
    private LoginServiceJdbcImpl loginService;
    private Connection connection;

    @BeforeEach
    void setUp() {
        servlet = new configUsuarioServelet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        clienteService = mock(ClienteServiceJdbcImpl.class);
        loginService = mock(LoginServiceJdbcImpl.class);
        connection = mock(Connection.class);

        mockStatic(ConexionBaseDatos.class);
    }

    @Test
    void doPost_ConfigUsuarioExitoso() throws ServletException, IOException, SQLException {
        // Mock de la conexión de base de datos
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        // Mock de los parámetros del request
        when(request.getParameter("nombre")).thenReturn("John Doe");
        when(request.getParameter("user")).thenReturn("johndoe");
        when(request.getParameter("correo")).thenReturn("johndoe@example.com");
        when(request.getParameter("telefono")).thenReturn("123456789");
        when(request.getParameter("DNI")).thenReturn("987654321");
        when(request.getParameter("password")).thenReturn("securepassword");
        when(request.getSession()).thenReturn(session);

        // Mock de objetos de sesión
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1);
        Cliente mockCliente = new Cliente();
        mockCliente.setId(1);
        when(session.getAttribute("usuario")).thenReturn(mockUsuario);
        when(session.getAttribute("cliente")).thenReturn(mockCliente);

        // Ejecutar el método
        servlet.doPost(request, response);

        // Verificar que los servicios fueron invocados correctamente
        verify(loginService).editarCuenta(eq(1), eq(987654321), eq("johndoe"), eq("securepassword"), eq("usu"));
        verify(clienteService).editarCliente(eq(1), eq("johndoe@example.com"), eq(123456789), eq(1), eq("John Doe"));

        // Verificar que se haya hecho commit y redirección
        verify(connection).commit();
        verify(response).sendRedirect(contains("/usuario/index.jsp"));
    }

    @Test
    void doPost_FaltanParametros() throws ServletException, IOException {
        // Mock de los parámetros incompletos
        when(request.getParameter("nombre")).thenReturn(null);

        // Ejecutar el método
        servlet.doPost(request, response);

        // Verificar que responde con error
        verify(response).sendError(eq(HttpServletResponse.SC_BAD_REQUEST), contains("Todos los campos son obligatorios."));
    }

    @Test
    void doPost_ErrorEnLaBaseDeDatos() throws ServletException, IOException, SQLException {
        // Mock de la conexión de base de datos
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);

        // Mock de los parámetros del request
        when(request.getParameter("nombre")).thenReturn("John Doe");
        when(request.getParameter("user")).thenReturn("johndoe");
        when(request.getParameter("correo")).thenReturn("johndoe@example.com");
        when(request.getParameter("telefono")).thenReturn("123456789");
        when(request.getParameter("DNI")).thenReturn("987654321");
        when(request.getParameter("password")).thenReturn("securepassword");
        when(request.getSession()).thenReturn(session);

        // Mock de objetos de sesión
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1);
        Cliente mockCliente = new Cliente();
        mockCliente.setId(1);
        when(session.getAttribute("usuario")).thenReturn(mockUsuario);
        when(session.getAttribute("cliente")).thenReturn(mockCliente);

        // Simular una excepción en el servicio
        doThrow(SQLException.class).when(loginService).editarCuenta(anyInt(), anyInt(), anyString(), anyString(), anyString());

        // Ejecutar el método
        assertThrows(ServletException.class, () -> servlet.doPost(request, response));

        // Verificar que se haya hecho rollback
        verify(connection).rollback();
    }
}