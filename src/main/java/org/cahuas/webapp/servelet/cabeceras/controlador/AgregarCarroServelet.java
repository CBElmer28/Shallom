package org.cahuas.webapp.servelet.cabeceras.controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Carro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.ItemCarro;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoService;
import org.cahuas.webapp.servelet.cabeceras.models.services.ProductoServiceJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Servlet que gestiona la adición de productos al carro de compras del usuario.
 * <p>
 * Este servlet se encarga de recibir los parámetros del producto y la cantidad a agregar,
 * consultar la base de datos para validar la existencia del producto y añadirlo al carro
 * de compras almacenado en la sesión del usuario.
 * </p>
 *
 * @author Team SHALOM
 * @version 1.0
 */
@WebServlet("/usuario/carro/agregar")
public class AgregarCarroServelet extends HttpServlet {

    /**
     * Método que procesa las solicitudes GET para agregar un producto al carro de compras.
     * <p>
     * Los parámetros esperados en la solicitud son:
     * <ul>
     *     <li><b>id</b>: ID del producto que se desea agregar.</li>
     *     <li><b>cantidad</b>: Cantidad del producto a agregar.</li>
     * </ul>
     * </p>
     * Si el producto existe en la base de datos, se añade al carro de compras almacenado
     * en la sesión del usuario. Si no existe, se devuelve un error HTTP 400.
     *
     * @param req  La solicitud HTTP recibida.
     * @param resp La respuesta HTTP que será enviada al cliente.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        int cantidad = Integer.parseInt(req.getParameter("cantidad"));
        int idd = Math.toIntExact(id);
        Connection conn = null;
        try {
            conn = ConexionBaseDatos.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ProductoService service = new ProductoServiceJdbcImpl(conn);
        Optional<Producto> producto = service.porId(idd);
        if (producto.isPresent()) {
            HttpSession session = req.getSession();
            Carro carro = (Carro) session.getAttribute("carro");
            /*if (carro == null) {
                carro = new Carro();
                session.setAttribute("carro", carro);
            }*/
            ItemCarro nuevoItem = new ItemCarro(cantidad, producto.get());
            carro.addItemCarro(nuevoItem);
            /*
            Optional<ItemCarro> optionalItem = carro.getItems().stream()
                    .filter(i -> i.getProducto().getId() == idd)
                    .findFirst();
            if (optionalItem.isPresent()) {
                ItemCarro itemExistente = optionalItem.get();
                itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);
            } else {
                ItemCarro nuevoItem = new ItemCarro(cantidad, producto.get());
                carro.addItemCarro(nuevoItem);
            }*/
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO EN LA BASE DE DATOS");
        }
        resp.sendRedirect(req.getContextPath() + "/usuario/cart.jsp");
    }
}