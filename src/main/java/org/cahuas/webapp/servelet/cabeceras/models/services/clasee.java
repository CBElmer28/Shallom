package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.repositories.UsuarioRepositoryJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clasee {
    public static void main(String[] args) throws SQLException {

            clasee.actualizarContraseas();
            System.out.println("Todas las contraseñas han sido encriptadas exitosamente.");

    }

    public static void actualizarContraseas() throws SQLException {
        String selectSql = "SELECT id, pass FROM usuarios"; // Consulta para obtener los datos actuales
        String updateSql = "UPDATE usuarios SET pass = ? WHERE id = ?"; // Consulta para actualizar la contraseña
        Connection conn2 = ConexionBaseDatos.getConnection();
        try (
                PreparedStatement selectStmt = conn2.prepareStatement(selectSql);
                PreparedStatement updateStmt = conn2.prepareStatement(updateSql);
                ResultSet rs = selectStmt.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String plainPassword = rs.getString("pass");

                // Generar el hash de la contraseña
                String hashedPassword = PasswordUtil.hashPassword(plainPassword);

                // Actualizar la contraseña en la base de datos
                updateStmt.setString(1, hashedPassword);
                updateStmt.setInt(2, id);
                updateStmt.executeUpdate();
            }
        }
    }
}
