package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.ClienteServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class RegistroUsuarioServeletTest {

    @InjectMocks
    private RegistroUsuarioServelet registroUsuarioServelet;

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private Connection connection;

    @Mock
    private LoginServiceJdbcImpl loginService;

    @Mock
    private ClienteServiceJdbcImpl clienteService;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(ConexionBaseDatos.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(mock(java.sql.PreparedStatement.class));
        doNothing().when(connection).setAutoCommit(false);
        doNothing().when(connection).commit();
        doNothing().when(connection).rollback();
        doNothing().when(connection).setAutoCommit(true);
        doNothing().when(connection).close();
    }

    @Test
    void testDoPost_Success() throws ServletException, IOException, SQLException {
        // Simula los datos enviados por el formulario
        when(req.getParameter("nombre")).thenReturn("TestUser");
        when(req.getParameter("user")).thenReturn("testuser");
        when(req.getParameter("correo")).thenReturn("test@example.com");
        when(req.getParameter("telefono")).thenReturn("123456789");
        when(req.getParameter("DNI")).thenReturn("987654321");
        when(req.getParameter("password")).thenReturn("password123");

        // Simula el comportamiento de los servicios
        doNothing().when(loginService).crearCuenta(anyInt(), anyString(), anyString(), anyString());
        when(loginService.existeUsuario(anyString())).thenReturn(new Usuario(1, "testuser", "password123", "usu"));
        doNothing().when(clienteService).crearCliente(anyString(), anyInt(), anyInt(), anyString());

        // Ejecuta el método
        registroUsuarioServelet.doPost(req, resp);

        // Verifica las interacciones
        verify(connection, times(2)).commit();
        verify(resp).sendRedirect(anyString());
    }

    @Test
    void testDoPost_InvalidParameters() throws ServletException, IOException {
        // Simula datos faltantes en el formulario
        when(req.getParameter("nombre")).thenReturn(null);

        // Ejecuta el método
        registroUsuarioServelet.doPost(req, resp);

        // Verifica que se haya enviado un error
        verify(resp).sendError(eq(HttpServletResponse.SC_BAD_REQUEST), anyString());
    }

    @Test
    void testDoPost_SQLException() throws ServletException, IOException, SQLException {
        // Simula los datos enviados por el formulario
        when(req.getParameter("nombre")).thenReturn("TestUser");
        when(req.getParameter("user")).thenReturn("testuser");
        when(req.getParameter("correo")).thenReturn("test@example.com");
        when(req.getParameter("telefono")).thenReturn("123456789");
        when(req.getParameter("DNI")).thenReturn("987654321");
        when(req.getParameter("password")).thenReturn("password123");

        // Simula una excepción SQL
        doThrow(SQLException.class).when(connection).commit();

        // Ejecuta el método
        registroUsuarioServelet.doPost(req, resp);

        // Verifica que se haya llamado al rollback y enviado un error
        verify(connection).rollback();
        verify(resp).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, anyString());
    }
}
