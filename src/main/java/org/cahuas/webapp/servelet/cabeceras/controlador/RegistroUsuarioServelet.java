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

@WebServlet("/registro")
public class RegistroUsuarioServelet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los datos del formulario
        String username = req.getParameter("nombre");
        String user = req.getParameter("user");
        String correo = req.getParameter("correo");
        int telefono;
        try {
            telefono = Integer.valueOf(req.getParameter("telefono"));
        } catch (NumberFormatException e) {
            telefono = 0;
        }
        int dni = Integer.parseInt(req.getParameter("DNI"));
        try {
            dni = Integer.valueOf(req.getParameter("DNI"));
        } catch (NumberFormatException e) {
            dni = 0;
        }
        String password = req.getParameter("password");

        // Validar los datos (opcional)
        if (username == null || user == null || correo == null || password == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Todos los campos son obligatorios.");
            return;
        }

        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.setAutoCommit(false);  // Desactivar el autocommit para realizar la transacción manualmente
            ClienteServiceJdbcImpl pro = new ClienteServiceJdbcImpl(conn);
            LoginServiceJdbcImpl usuario = new LoginServiceJdbcImpl(conn);

            String t = "usu";  // Asignar un tipo de usuario, en este caso 'usu'
            usuario.crearCuenta(dni, user, password, t);  // Crear la cuenta de usuario en la base de datos

            conn.commit();  // Confirmar la transacción

            Usuario nuevo = usuario.existeUsuario(user);  // Obtener el usuario recién creado
            pro.crearCliente(correo, telefono, nuevo.getId(), username);  // Crear el cliente asociado

            conn.commit();  // Confirmar la transacción

            // Redirigir al usuario a la página de inicio
            resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");
        } catch (SQLException e) {
            try {
                conn.rollback();  // Si ocurre un error, deshacer los cambios realizados
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace();
            throw new ServletException("Error al registrar usuario y cliente", e);
        } finally {
            try {
                conn.setAutoCommit(true);  // Restaurar el comportamiento de autocommit por defecto
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                conn.close();  // Cerrar la conexión para liberar recursos
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
