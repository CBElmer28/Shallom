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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/usuario/producto")
public class ProductoServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idProducto;
        try {
            idProducto = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e){
            idProducto = 0;
        }
        Producto producto = null;
        try {
            Connection connection = ConexionBaseDatos.getConnection();
            ProductoService service = new ProductoServiceJdbcImpl(connection);
            producto = service.buscarId(idProducto);
            if (producto!=null) {
            req.setAttribute("producto", producto);
               req.getRequestDispatcher("shop-single.jsp").forward(req, resp);
            }else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
