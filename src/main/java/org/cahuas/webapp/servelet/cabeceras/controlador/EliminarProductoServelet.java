package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/eliminar-producto")
public class EliminarProductoServelet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            // Obtén el id del producto del formulario
            Integer idProducto;
            try {
                idProducto = Integer.valueOf(req.getParameter("idProducto"));
            } catch (NumberFormatException e){
                idProducto = 0;
            }
            conn = ConexionBaseDatos.getConnection();
            ProductoService service = new ProductoServiceJdbcImpl(conn);
            Optional<Producto>  o = service.porId(idProducto);
            if (o.isPresent()) {
            service.eliminar(idProducto);
            resp.sendRedirect(req.getContextPath() +"/productos ");
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido en la tabla");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
        }
    }
}
