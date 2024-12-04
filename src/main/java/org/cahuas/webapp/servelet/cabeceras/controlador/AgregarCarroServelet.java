
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

@WebServlet("/usuario/carro/agregar")
public class AgregarCarroServelet extends HttpServlet {
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
        }else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "NO EXISTEN PRODUCTO EN LA BASE DE DATOS");
        }
        resp.sendRedirect(req.getContextPath() + "/usuario/cart.jsp");
    }
}
