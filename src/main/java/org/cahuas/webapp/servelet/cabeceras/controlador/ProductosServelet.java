
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
import java.util.Optional;

/**
 * Servlet encargado de manejar las solicitudes relacionadas con los productos.
 * Este servlet permite listar los productos de la base de datos y redirigir a las páginas correspondientes 
 * según la URI de la solicitud, ya sea para la vista del usuario o para el administrador.
 * 
 * @author Team Shalom
 * @version 1.8
 */
@WebServlet({"/usuario/productos","/productos"})
public class ProductosServelet extends HttpServlet {

    /**
     * Maneja las solicitudes HTTP GET para listar los productos.
     * Este método obtiene la lista de productos desde la base de datos y redirige la solicitud
     * a diferentes vistas dependiendo de la URI solicitada.
     * Si la URI termina en "/usuario/productos", se muestra la tienda del usuario.
     * Si la URI termina en "/productos", se muestra la vista de inventario del administrador.
     * 
     * @param req la solicitud HTTP que contiene los parámetros necesarios para la ejecución del servlet.
     * @param resp la respuesta HTTP que se envía al cliente después de procesar la solicitud.
     * @throws ServletException si ocurre un error relacionado con el servlet durante el procesamiento de la solicitud.
     * @throws IOException si ocurre un error durante el proceso de redirección o escritura en la respuesta.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        try {
            // Obtener conexión a la base de datos
            // Obtener conexión a la base de datos
            conn = ConexionBaseDatos.getConnection();
            
            // Crear una instancia del servicio de productos
            ProductoService service = new ProductoServiceJdbcImpl(conn);
            
            // Obtener la lista de productos desde el servicio
            
            // Obtener la lista de productos desde el servicio
            List<Producto> productos = service.listar();
            
            // Establecer la lista de productos como atributo de la solicitud
            req.setAttribute("productos", productos);
            
            // Obtener la URI de la solicitud para determinar a qué página redirigir
            String requestURI = req.getRequestURI();
            
            // Si la solicitud es para el usuario, redirigir a la página de tienda
            if (requestURI.endsWith("/usuario/productos")) {
                // Si la solicitud es para el usuario, redirigir a shop.jsp
                getServletContext().getRequestDispatcher("/usuario/shop.jsp").forward(req, resp);
            } 
            // Si la solicitud es para el administrador, redirigir a la página de inventario
            else if (requestURI.endsWith("/productos")) {
                getServletContext().getRequestDispatcher("/admin/inventario.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            // Manejar excepciones de base de datos
            // Manejar excepciones de base de datos
            throw new RuntimeException(e);
        }
    }
}