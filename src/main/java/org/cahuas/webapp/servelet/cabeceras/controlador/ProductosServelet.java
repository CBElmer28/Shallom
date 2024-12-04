package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginService;
import org.cahuas.webapp.servelet.cabeceras.models.services.LoginServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet({"/usuario/productos","/productos"})
public class ProductosServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();
            ProductoService service = new ProductoServiceJdbcImpl(conn);
            List<Producto> productos = service.listar();
            req.setAttribute("productos", productos);
            String requestURI = req.getRequestURI();
            if (requestURI.endsWith("/usuario/productos")) {
                getServletContext().getRequestDispatcher("/usuario/shop.jsp").forward(req, resp);
            } else if (requestURI.endsWith("/productos")) {
                getServletContext().getRequestDispatcher("/admin/inventario.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
