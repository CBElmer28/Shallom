package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<Producto>{
    private Connection conn;

    public ProductoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNom(rs.getString("nombre"));
        p.setPrecio(rs.getInt("precio"));
        p.setStock(rs.getInt("stock"));
        Proveedor c = new Proveedor();
        c.setId(rs.getInt("id_proveedor"));
        c.setCo(rs.getString("razon_social"));
        p.setId_proveedor(c);

        return p;
    }

    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.id, p.nom, p.precio, p.stock,p.ruta_imagen  p.id_proveedor, pr.co AS razon_social \n" +
                     " FROM productos p\n " +
                     " JOIN proveedor pr ON p.id_proveedor = pr.id")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }

    @Override
    public Producto porId(int id) throws SQLException {
        Producto producto = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.co as proveedor FROM productos as p " +
                " inner join proveedor as c ON (p.id_proveedor = c.id) WHERE p.id = ?")) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if ( producto.getId() > 0) {
            sql = "update productos set nom=?, cat=?, precio=?, stock=?, id_proveedor=?, ruta_imagen=? where id=?";
        } else {
            sql = "insert into productos (nom, cat, precio, stock, id_proveedor, ruta_imagen) values (?,?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, producto.getId());
            stmt.setString(2, producto.getNom());
            stmt.setInt(3, producto.getPrecio());
            stmt.setInt(4, producto.getStock());
            stmt.setInt(5, producto.getId_proveedor().getId());
            stmt.setString(6, producto.getRuta_imagen());

            if ( producto.getId() > 0) {
                stmt.setInt(5, producto.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "delete from productos where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
