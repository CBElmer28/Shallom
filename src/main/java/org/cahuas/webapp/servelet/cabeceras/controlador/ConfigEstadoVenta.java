package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Venta;
import org.cahuas.webapp.servelet.cabeceras.models.services.VentaServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet para configurar el estado de una venta.
 * Este servlet actualiza el estado de una venta y actualiza el historial completo de ventas en la sesión.
 *
 * URL: /configVenta
 */
@WebServlet("/configVenta")
public class ConfigEstadoVenta extends HttpServlet {

    /**
     * Procesa las solicitudes HTTP POST para actualizar el estado de una venta.
     *
     * @param req  el objeto HttpServletRequest que contiene la solicitud del cliente.
     * @param resp el objeto HttpServletResponse para enviar la respuesta al cliente.
     * @throws ServletException si ocurre un error en el procesamiento del servlet.
     * @throws IOException      si ocurre un error de entrada/salida al procesar la solicitud.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtiene los parámetros "id" y "estado" de la solicitud.
            String idParam = req.getParameter("id");
            String estado = req.getParameter("estado");

            // Validación de parámetros.
            if (idParam == null || estado == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Datos incompletos");
                return;
            }

            // Convierte el parámetro "id" a un entero.
            int id = Integer.parseInt(idParam);

            // Obtiene una conexión a la base de datos.
            Connection c = ConexionBaseDatos.getConnection();

            // Crea una instancia del servicio de ventas y actualiza el estado.
            VentaServiceJdbcImpl v = new VentaServiceJdbcImpl(c);
            v.configurarEstadoVenta(id, estado);

            // Actualiza el historial completo de ventas.
            VentaServiceJdbcImpl ventaService = new VentaServiceJdbcImpl(c);
            List<Venta> historialVentaT = ventaService.obtenerHistorialVentasTotal();

            // Almacena el historial en la sesión del usuario.
            HttpSession session = req.getSession();
            session.setAttribute("historialVentaTotal", historialVentaT);

            // Redirige a la página de configuración de ventas.
            resp.sendRedirect(req.getContextPath() + "/admin/configVenta.jsp");
        } catch (SQLException e) {
            // Manejo de errores de la base de datos.
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error actualizando el estado");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Manejo de errores de formato en el parámetro "id".
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        }
    }
}