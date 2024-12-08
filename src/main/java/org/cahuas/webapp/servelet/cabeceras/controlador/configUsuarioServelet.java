package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.ClienteServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet encargado de procesar la configuración del usuario. Este servlet se activa cuando
 * el usuario realiza una solicitud POST para actualizar sus datos personales y de cuenta.
 * El servlet valida los datos recibidos, realiza las actualizaciones correspondientes en la base de datos,
 * y redirige al usuario a su página de inicio si la operación es exitosa. En caso de error, realiza
 * un rollback y muestra un mensaje de error.
 * 
 * @author Team Shalom
 * @version 1.5
 */
@WebServlet("/config")
public class configUsuarioServelet extends HttpServlet {
    
    /**
     * Maneja las solicitudes POST para actualizar la información del usuario.
     * Recibe los parámetros del formulario de configuración del usuario, valida los datos,
     * y realiza las actualizaciones correspondientes en la base de datos utilizando
     * los servicios `ClienteServiceJdbcImpl` y `LoginServiceJdbcImpl`. En caso de éxito, 
     * redirige al usuario a la página de inicio. En caso de error, realiza un rollback de la transacción.
     *
     * @param req la solicitud HTTP que contiene los parámetros del formulario de configuración del usuario.
     * @param resp la respuesta HTTP que se envía al usuario, redirigiéndolo a la página de inicio si la operación es exitosa.
     * @throws ServletException si ocurre un error durante el procesamiento de la solicitud.
     * @throws IOException si ocurre un error al enviar la respuesta al cliente.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los parámetros de la solicitud HTTP
        String username = req.getParameter("nombre");
        String user = req.getParameter("user");
        String correo = req.getParameter("correo");
        int telefono;
        try {
            telefono = Integer.valueOf(req.getParameter("telefono"));
        } catch (NumberFormatException e) {
            telefono = 0;  // En caso de que no se ingrese un número válido
        }
        int dni = Integer.parseInt(req.getParameter("DNI"));
        try {
            dni = Integer.valueOf(req.getParameter("DNI"));
        } catch (NumberFormatException e) {
            dni = 0;  // En caso de que no se ingrese un número válido
        }
        String password = req.getParameter("password");

        // Validación de los datos (si algún campo está vacío, se envía un error)
        if (username == null || user == null || correo == null || password == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        // Establecer la conexión con la base de datos
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();  // Establecer la conexión a la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            // Iniciar la sesión y comenzar la transacción
            HttpSession session = req.getSession();
            conn.setAutoCommit(false); // Desactivar el auto-commit para gestionar transacciones manualmente

            // Crear instancias de los servicios necesarios para realizar las actualizaciones
            ClienteServiceJdbcImpl pro = new ClienteServiceJdbcImpl(conn);
            LoginServiceJdbcImpl usuario = new LoginServiceJdbcImpl(conn);

            // Obtener el usuario actual de la sesión
            Usuario nuevo = (Usuario) session.getAttribute("usuario");
            String t = "usu"; // Definir tipo de usuario

            // Actualizar los datos del usuario en la base de datos
            usuario.editarCuenta(nuevo.getId(), dni, user, password, t);
            conn.commit(); // Confirmar la transacción de actualización de la cuenta del usuario

            // Obtener el cliente actual de la sesión y actualizar sus datos
            Cliente c = (Cliente) session.getAttribute("cliente");
            pro.editarCliente(c.getId(), correo, telefono, nuevo.getId(), username);
            conn.commit(); // Confirmar la transacción de actualización de los datos del cliente

            // Redirigir al usuario a la página principal de su cuenta
            resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");


        } catch (SQLException e) {
            // Si ocurre un error, deshacer la transacción
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            throw new ServletException("Error al registrar usuario y cliente", e);
        } finally {
            try {
                conn.setAutoCommit(true); // Restaurar el auto-commit por defecto
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.close(); // Cerrar la conexión con la base de datos para liberar recursos
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

