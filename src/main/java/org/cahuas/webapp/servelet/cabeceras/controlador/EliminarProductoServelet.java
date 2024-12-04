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

// Servlet para manejar la eliminación de productos
@WebServlet("/eliminar-producto") // URL donde este servlet estará disponible
public class EliminarProductoServelet extends HttpServlet {

    // Método para manejar la solicitud POST
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            // Obtener el ID del producto desde el formulario (parametro "idProducto")
            Integer idProducto;
            try {
                idProducto = Integer.valueOf(req.getParameter("idProducto"));
            } catch (NumberFormatException e){
                // Si el valor no es un número válido, asignar 0 (indicar que el ID no es válido)
                idProducto = 0;
            }

            // Establecer la conexión con la base de datos
            conn = ConexionBaseDatos.getConnection();

            // Crear un servicio para manejar operaciones de productos en la base de datos
            ProductoService service = new ProductoServiceJdbcImpl(conn);

            // Buscar el producto por ID en la base de datos
            Optional<Producto> o = service.porId(idProducto);

            // Si el producto existe, proceder a eliminarlo
            if (o.isPresent()) {
                service.eliminar(idProducto); // Eliminar el producto de la base de datos
                // Redirigir a la página de productos después de la eliminación exitosa
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
                // Si el producto no existe, enviar un error con código 400 (Bad Request)
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido en la tabla");
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL si ocurre un error de base de datos
            throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            // Si el ID del producto no es un número válido, enviar un error con código 400 (Bad Request)
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto no válido");
        }
    }
}
