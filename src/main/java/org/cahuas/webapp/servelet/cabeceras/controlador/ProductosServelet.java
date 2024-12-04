
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
import java.util.List;

@WebServlet({"/usuario/productos","/productos"})
public class ProductosServelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            // Establecer la conexión con la base de datos
            conn = ConexionBaseDatos.getConnection();
            
            // Crear el servicio para manejar productos
            ProductoService service = new ProductoServiceJdbcImpl(conn);
            
            // Obtener la lista de productos desde el servicio
            List<Producto> productos = service.listar();
            
            // Establecer los productos como atributo en la solicitud
            req.setAttribute("productos", productos);
            
            // Obtener la URI de la solicitud
            String requestURI = req.getRequestURI();
            
            // Redirigir a la vista adecuada según la URI solicitada
            if (requestURI.endsWith("/usuario/productos")) {
                // Si la solicitud es para el usuario, redirigir a shop.jsp
                getServletContext().getRequestDispatcher("/usuario/shop.jsp").forward(req, resp);
            } else if (requestURI.endsWith("/productos")) {
                // Si la solicitud es para el administrador, redirigir a inventario.jsp
                getServletContext().getRequestDispatcher("/admin/inventario.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            // Manejo de excepciones, si ocurre un error con la base de datos
            throw new RuntimeException(e);
        }
    }
}
