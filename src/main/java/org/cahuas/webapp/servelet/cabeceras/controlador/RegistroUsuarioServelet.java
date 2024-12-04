package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.ClienteServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Servlet encargado de manejar el registro de nuevos usuarios en la aplicación.
 * Este servlet procesa la información del formulario de registro, valida los datos,
 * crea una nueva cuenta de usuario y un cliente asociado en la base de datos.
 * En caso de éxito, redirige al usuario a la página principal; si ocurre un error,
 * se gestiona la transacción y se muestra un mensaje de error.
 * 
 * @author Team Shalom
 * @version 1.5
 */
@WebServlet("/registro")
public class RegistroUsuarioServelet  extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP POST para registrar un nuevo usuario.
     * Este método procesa los datos del formulario de registro, valida los campos, 
     * crea una cuenta de usuario en la base de datos y asocia un cliente con esa cuenta.
     * Si se produce un error, se deshace la transacción y se muestra un mensaje de error.
     * 
     * @param req la solicitud HTTP que contiene los parámetros del formulario de registro.
     * @param resp la respuesta HTTP que se envía al cliente después de procesar la solicitud.
     * @throws ServletException si ocurre un error en la ejecución del servlet.
     * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los datos del formulario
        String username = req.getParameter("nombre");
        String user = req.getParameter("user");
        String correo = req.getParameter("correo");
        int telefono;
        
        // Manejo de excepciones para convertir el teléfono a número
        try {
            telefono = Integer.valueOf(req.getParameter("telefono"));
        } catch (NumberFormatException e) {
            telefono = 0;
        }
        
        // Obtener el DNI y manejar excepciones
        int dni = Integer.parseInt(req.getParameter("DNI"));
        try {
            dni = Integer.valueOf(req.getParameter("DNI"));
        } catch (NumberFormatException e) {
            dni = 0;
        }
        
        String password = req.getParameter("password");

        // Validar los datos (opcional)
        if (username == null || user == null || correo == null  || password == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        // Establecer conexión con la base de datos
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Iniciar la transacción para crear el usuario y el cliente
        try {
            conn.setAutoCommit(false);
            ClienteServiceJdbcImpl pro = new ClienteServiceJdbcImpl(conn);
            LoginServiceJdbcImpl usuario = new LoginServiceJdbcImpl(conn);
            String t = "usu"; // Tipo de usuario
            usuario.crearCuenta(dni, user, password, t);
            conn.commit();

            // Verificar si el usuario fue creado y asociar al cliente
            Usuario nuevo = usuario.existeUsuario(user);
            pro.crearCliente(correo, telefono, nuevo.getId(), username);
            conn.commit();

            // Redirigir al usuario a la página principal
            resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");

        } catch (SQLException e) {
            // En caso de error, deshacer la transacción
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            throw new ServletException("Error al registrar usuario y cliente", e);
        } finally {
            // Restaura el auto-commit y cierra la conexión
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
