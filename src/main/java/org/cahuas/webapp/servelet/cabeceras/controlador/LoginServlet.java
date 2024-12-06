package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.DetalleVenta;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Venta;
import org.cahuas.webapp.servelet.cabeceras.models.services.*;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

/**
 * Servlet que maneja el proceso de autenticación de usuarios.
 * Este servlet valida las credenciales del usuario y redirige al usuario a la página correspondiente 
 * dependiendo de su rol (administrador o usuario).
 *
 * @author Team Shalom
 * @version 1.5
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Método que maneja la solicitud POST para iniciar sesión de un usuario.
     * Valida las credenciales del usuario y, dependiendo de su tipo (admin o usuario),
     * redirige a la página de inicio correspondiente y carga el historial de ventas.
     *
     * @param req la solicitud HTTP que contiene los parámetros de inicio de sesión.
     * @param resp la respuesta HTTP que se envía al cliente.
     * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        // Recupera los parámetros de usuario y contraseña del formulario
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            // Establece la conexión a la base de datos
            Connection conn = ConexionBaseDatos.getConnection();
            // Crea una instancia del servicio de autenticación
            LoginServiceJdbcImpl usu = new LoginServiceJdbcImpl(conn);
            // Verifica las credenciales del usuario
            Usuario ne = usu.UsuarioSesion(username, password);

            if (ne != null) {
                // Si las credenciales son correctas, crea una nueva sesión
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("usuario", ne);

                // Cargar el historial de ventas del usuario
                VentaServiceJdbcImpl ventaService = new VentaServiceJdbcImpl(conn);
                List<Venta> historialVentas = ventaService.obtenerHistorialVentas(ne.getId());

                // Verifica el tipo de usuario y redirige a la página correspondiente
                if ("admin".equals(ne.getTipo())) {
                    // Si el usuario es un administrador, carga el historial completo de ventas
                    List<Venta> historialVentaT = ventaService.obtenerHistorialVentas(4);
                    List<DetalleVenta> todoDetalleventa = ventaService.obtenerTodosLosDetallesDeVenta();
                    session.setAttribute("historialVentaTotal", historialVentaT);
                    session.setAttribute("todoDetalleVenta", todoDetalleventa);
                    resp.sendRedirect(req.getContextPath() + "/admin/index.jsp");
                } else if ("usu".equals(ne.getTipo())) {
                    // Si el usuario es un cliente, carga su historial de compras
                    ClienteServiceJdbcImpl cli = new ClienteServiceJdbcImpl(conn);
                    Cliente c = cli.buscarPorUsuarioId(ne.getId());
                    session.setAttribute("usuario", ne);
                    session.setAttribute("cliente", c);
                    session.setAttribute("historialCompras", historialVentas);
                    resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");
                }
            } else {
                // Si las credenciales no son válidas, redirige al login
                resp.sendRedirect(req.getContextPath() + "/usuario/login.jsp");
            }
        } catch (SQLException ex) {
            // En caso de error en la base de datos, registra el error y redirige al inicio
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            resp.sendRedirect(req.getContextPath() + "/usuario/index.jsp");
        }
    }
}