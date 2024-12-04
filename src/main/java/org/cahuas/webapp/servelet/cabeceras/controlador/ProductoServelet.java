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

@WebServlet("/usuario/producto")
public class ProductoServelet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Intentar obtener el ID del producto de los parámetros de la solicitud
        Integer idProducto;
        try {
            idProducto = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            idProducto = 0;  // Si no es válido, asignar 0
        }

        Producto producto = null;
        try {
            // Establecer la conexión con la base de datos
            Connection connection = ConexionBaseDatos.getConnection();
            
            // Crear el servicio para manejar productos
            ProductoService service = new ProductoServiceJdbcImpl(connection);
            
            // Buscar el producto por su ID
            producto = service.buscarId(idProducto);

            // Si el producto existe, lo asigna al request y redirige a la página JSP
            if (producto != null) {
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("shop-single.jsp").forward(req, resp);
            } else {
                // Si no se encuentra el producto, enviar un error 400
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO");
            }

        } catch (SQLException e) {
            // En caso de error con la base de datos, lanzar una excepción
            throw new RuntimeException(e);
        }
    }
}
