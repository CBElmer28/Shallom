
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

/**
 * Servlet para manejar la eliminación de productos.
 * <p>
 * Este servlet permite eliminar un producto de la base de datos a través de su ID, recibido como parámetro
 * en la solicitud POST. Si el producto no existe, se devuelve un error al cliente.
 * </p>
 * 
 * @author Team SHALOM
 * @version 1.9
 */
@WebServlet("/eliminar-producto")
public class EliminarProductoServelet extends HttpServlet {

    /**
     * Maneja las solicitudes POST para eliminar un producto por su ID.
     * <p>
     * El ID del producto se obtiene del parámetro `idProducto` del formulario. Si el ID es inválido
     * o no se encuentra en la base de datos, se devuelve un error HTTP. Si el ID es válido y el
     * producto existe, se elimina de la base de datos.
     * </p>
     * 
     * @param req  Solicitud HTTP que incluye el ID del producto a eliminar.
     * @param resp Respuesta HTTP que indica el resultado de la operación.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            // Obtener el ID del producto desde los parámetros de la solicitud
            Integer idProducto;
            try {
                idProducto = Integer.valueOf(req.getParameter("idProducto"));
            } catch (NumberFormatException e) {
                idProducto = 0;
            }

            // Establecer la conexión a la base de datos
            conn = ConexionBaseDatos.getConnection();

            // Crear un servicio para manejar operaciones de productos en la base de datos
            ProductoService service = new ProductoServiceJdbcImpl(conn);

            // Buscar el producto por su ID
            Optional<Producto> o = service.porId(idProducto);

            // Si el producto existe, eliminarlo; si no, devolver un error
            if (o.isPresent()) {
                service.eliminar(idProducto);
                resp.sendRedirect(req.getContextPath() + "/productos");
            } else {
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
