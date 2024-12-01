package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRepositoryJdbcImpl implements RepositoryCliente<Cliente> {
    private Connection conn;

    public ClienteRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Cliente ClienteporId(int id) throws SQLException {
        String query = "SELECT * FROM clientes WHERE id = ?";
        try (

                PreparedStatement statement = conn.prepareStatement(query)) {


            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("co"),
                        resultSet.getInt("tel"),
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre")
                );
            }
        }
        return null; // Si no se encuentra el cliente
    }

    @Override
    public Cliente existeCliente(String username) {
        String query = "SELECT * FROM clientes WHERE nombre = ?";
        try (

                PreparedStatement statement = conn.prepareStatement(query)) {


            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getString("co"),
                        resultSet.getInt("tel"),
                        resultSet.getInt("id_usuario"),
                        resultSet.getString("nombre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el cliente
    }

    @Override
    public void crearCliente(String co, int tel, int id_usuario, String nombre) throws SQLException {
        String query = "INSERT INTO clientes (co, tel, id_usuario, nombre) VALUES (?, ?, ?, ?)";
        try (
                PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, co);
            statement.setInt(2, tel);
            statement.setInt(3, id_usuario);
            statement.setString(4, nombre);
            statement.executeUpdate();
        }
    }

    @Override
    public void editarCliente(int id,String co, int tel, int id_usuario, String nombre) throws SQLException {
        String query = "UPDATE clientes SET co = ?, tel = ?, id_usuario = ?, nombre = ? WHERE id = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, co);
            statement.setInt(2, tel);
            statement.setInt(3, id_usuario);
            statement.setString(4, nombre);
            statement.setInt(5, id);
            statement.executeUpdate();
        }

    }

    @Override
    public Cliente buscarPorUsuarioId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id_usuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),         // Suponiendo que tienes un campo 'id'
                            rs.getString("co"),      // Campo 'co'
                            rs.getInt("tel"),        // Campo 'tel'
                            rs.getInt("id_usuario"), // Campo 'id_usuario'
                            rs.getString("nombre")   // Campo 'nombre'
                    );
                }
            }
        }
        return null; // Retorna null si no encuentra ningún cliente
    }
}
