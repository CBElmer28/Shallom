package org.cahuas.webapp.servelet.cabeceras.models.repositories;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorRepositoryImpl implements Repository<Proveedor>{
    private Connection conn;

    public ProveedorRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    private Proveedor getCategoria(ResultSet rs) throws SQLException {
        Proveedor provee = new Proveedor();
        provee.setId(rs.getInt("id"));
        provee.setRuc(rs.getInt("ruc"));
        provee.setDirec(rs.getString("direc"));
        provee.setTel(rs.getInt("tel"));
        provee.setCo(rs.getString("co"));
        return provee;
    }


    @Override
    public List<Proveedor> listar() throws SQLException {
        List<Proveedor> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from proveedor")){
            while (rs.next()) {
                Proveedor categoria = getCategoria(rs);
                categorias.add(categoria);
            }

        }
        return categorias;
    }

    @Override
    public Proveedor porId(int id) throws SQLException {
        Proveedor provee = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from Proveedor as c where c.id=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    provee = getCategoria(rs);
                }
            }
        }
        return provee;
    }

    @Override
    public void guardar(Proveedor proveedor) throws SQLException {

    }

    @Override
    public void eliminar(int id) throws SQLException {

    }
}
