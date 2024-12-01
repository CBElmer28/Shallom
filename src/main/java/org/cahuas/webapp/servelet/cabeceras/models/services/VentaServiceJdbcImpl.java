package org.cahuas.webapp.servelet.cabeceras.models.services;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.DetalleVenta;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Venta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaServiceJdbcImpl {

    private Connection connection;

    public VentaServiceJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    // Método para obtener todas las ventas de un usuario
    public List<Venta> obtenerHistorialVentas(int idUsuario) throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Venta WHERE id_usuario = ? ORDER BY Fecha DESC";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Venta venta = new Venta();
                    venta.setId(rs.getInt("id"));
                    venta.setIdUsuario(rs.getInt("id_usuario"));
                    venta.setFecha(rs.getDate("Fecha"));
                    venta.setEstado(rs.getString("estado"));
                    venta.setTotal(rs.getDouble("Total"));

                    // Obtener detalles de la venta
                    venta.setDetalles(obtenerDetallesDeVenta(venta.getId()));

                    ventas.add(venta);
                }
            }
        }
        return ventas;
    }

    // Método para obtener los detalles de una venta
    public List<DetalleVenta> obtenerDetallesDeVenta(int idVenta) throws SQLException {
        List<DetalleVenta> detalles = new ArrayList<>();
        String sql = "SELECT * FROM Detalle_Venta dv JOIN productos p ON dv.id_producto = p.id WHERE dv.id_venta = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("id_producto"));
                    producto.setNom(rs.getString("nom"));
                    producto.setRuta_imagen(rs.getString("ruta_imagen"));
                    // Otros datos del producto si es necesario

                    DetalleVenta detalle = new DetalleVenta();
                    detalle.setId(rs.getInt("id"));
                    detalle.setIdVenta(rs.getInt("id_venta"));
                    detalle.setProducto(producto);
                    detalle.setCantidad(rs.getInt("cantidad"));
                    detalle.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    detalle.setSubtotal(rs.getDouble("subtotal"));

                    detalles.add(detalle);
                }
            }
        }
        return detalles;
    }
}
