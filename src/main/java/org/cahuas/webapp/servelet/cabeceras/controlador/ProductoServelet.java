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

/**
 * Servlet encargado de manejar las solicitudes relacionadas con los productos de la tienda.
 * Este servlet recupera un producto por su ID y muestra su información en una página de detalles.
 * Si el producto no existe, se muestra un error 400 (Bad Request).
 * 
 * @author Team Shalom
 * @version 1.0
 * 
 */
@WebServlet("/usuario/producto")
public class ProductoServelet extends HttpServlet {
    
    /**
     * Maneja las solicitudes HTTP GET para obtener los detalles de un producto.
     * Este método extrae el ID del producto desde los parámetros de la solicitud, busca el producto 
     * en la base de datos y lo muestra en la página de detalles. Si el producto no se encuentra, 
     * se envía un error 400.
     * 
     * @param req la solicitud HTTP que contiene el ID del producto.
     * @param resp la respuesta HTTP que se envía al cliente después de procesar la solicitud.
     * @throws ServletException si ocurre un error relacionado con el servlet durante el procesamiento de la solicitud.
     * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer idProducto;
        try {
            // Intentar obtener el ID del producto desde los parámetros de la solicitud
            idProducto = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e){
            // Si no se puede convertir a Integer, establecer el ID del producto como 0
            idProducto = 0;
        }
        Producto producto = null;
        try {
            // Obtener una conexión a la base de datos
            Connection connection = ConexionBaseDatos.getConnection();
            
            // Crear una instancia del servicio de productos
            ProductoService service = new ProductoServiceJdbcImpl(connection);
            
            // Buscar el producto por ID
            producto = service.buscarId(idProducto);
            
            // Si el producto existe, establecerlo como atributo en la solicitud y redirigir a la página de detalles
            if (producto != null) {
                req.setAttribute("producto", producto);
                req.getRequestDispatcher("shop-single.jsp").forward(req, resp);
            } else {
                // Si el producto no se encuentra, enviar un error 400
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO");
            }
        } catch (SQLException e) {
            // Manejar excepciones de base de datos
            throw new RuntimeException(e);
        }
    }
}
