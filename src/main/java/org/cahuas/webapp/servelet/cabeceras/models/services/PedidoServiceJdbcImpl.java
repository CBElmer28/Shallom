package org.cahuas.webapp.servelet.cabeceras.models.services;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoServiceJdbcImpl {
    private Connection conn;

    public PedidoServiceJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    public List<Producto> obtenerHistorialCompras(int idUsuario) throws SQLException {
        List<Producto> historial = new ArrayList<>();
        String sql = "SELECT p.id, p.nom, p.cat, p.precio, p.stock, p.ruta_imagen " +
                "FROM Pedido pe " +
                "JOIN Pedido_Productos pp ON pe.id = pp.id_pedido " +
                "JOIN Productos p ON pp.id_producto = p.id " +
                "WHERE pe.id_cliente = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id"));
                    producto.setNom(rs.getString("nom"));
                    producto.setCat(rs.getString("cat"));
                    producto.setPrecio(rs.getInt("precio"));
                    producto.setStock(rs.getInt("stock"));
                    producto.setRuta_imagen(rs.getString("ruta_imagen"));
                    historial.add(producto);
                }
            }
        }
        return historial;
    }
}
