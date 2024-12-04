/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.services.PasswordUtil;

public class UsuarioRepositoryJdbcImpl implements RepositoryUsuario<Usuario> {
    private Connection conn;

    //METODOS GENERALES PARA HACER CONSULTAS SQL
    public UsuarioRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario p = new Usuario();
        p.setId(rs.getInt("id"));
        p.setDni(rs.getInt("dni"));
        p.setUser(rs.getString("user"));
        p.setPass(rs.getString("pass"));
        p.setTipo(rs.getString("tipo"));
        return p;
    }
    private  boolean usuarioExisten(int dni) throws SQLException {
        String sql = "SELECT dni FROM usuarios WHERE dni = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dni);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    //METODO BUSCAR USUARIO POR ID
    @Override
    public Usuario UsuarioporId(int id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }


    //METODO PARA INICIAR SESION
    @Override
    public Usuario UsuarioSesion(String username, String password) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs); // Obtienes el usuario de la BD
                    // Validas la contraseña usando BCrypt
                    if (!PasswordUtil.checkPassword(password, usuario.getPass())) {
                        usuario = null; // Si la contraseña no coincide, usuario es null
                    }
                }
            }
        }
        return usuario;
    }




    //METODO SI EXISTE USUARIO POR NOMBRE
    @Override
    public Usuario existeUsuario(String username) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE user = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }


    //METODOS QUE NECESIT SABER SI EXISTE EL DNI YA QUE ES UNA ISSERCION DE LA CUENTA.

    //METODO PARA CREAR CUENTA NUEVA
    @Override
    public void crearCuenta(int dni, String usuario, String pass, String tipo) throws SQLException {
        if (usuarioExisten(dni)) {
            throw new SQLException("El usuario con DNI " + dni + " ya existe.");
        }
        String sql = "INSERT INTO usuarios (dni, user, pass, tipo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dni);
            stmt.setString(2, usuario);
            String hashedPassword = PasswordUtil.hashPassword(pass);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, tipo);
            stmt.executeUpdate();
        }
    }



    @Override
    public void editarCuenta(int id, int dni, String usuario, String pass, String tipo) throws SQLException {
        if (!usuarioExisten(dni)) {
            throw new SQLException("El usuario con DNI " + dni + " no existe.");
        }
        String sql = "UPDATE usuarios SET user = ?, pass = ?, tipo = ? WHERE id = ? AND dni = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, pass);
            stmt.setString(3, tipo);
            stmt.setInt(4, id);
            stmt.setInt(5, dni);
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminarUsuario(int dni) throws SQLException {
        // Verifica si el usuario existe antes de intentar eliminarlo
        if (!usuarioExisten(dni)) {
            throw new SQLException("El usuario con DNI " + dni + " no existe.");
        }

        // Consulta SQL para eliminar el usuario
        String sql = "DELETE FROM usuarios WHERE dni = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, dni);  // Especifica el DNI del usuario que se eliminará

            stmt.executeUpdate(); // Ejecuta la consulta de eliminación
        }
    }

}