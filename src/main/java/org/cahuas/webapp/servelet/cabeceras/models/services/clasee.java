package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.util.ConexionBaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase que proporciona métodos para encriptar y actualizar las contraseñas de los usuarios en la base de datos.
 * <p>
 * Utiliza las utilidades de conexión a la base de datos y un utilitario de generación de contraseñas para realizar
 * esta tarea de forma automática.
 * </p>
 * 
 * @author Team Shalom
 * @version 1.5
 */
public class clasee {

    /**
     * Método principal que ejecuta el proceso de encriptación de todas las contraseñas de la base de datos.
     * 
     * @param args Argumentos de la línea de comandos.
     * @throws SQLException Si ocurre algún error relacionado con la base de datos.
     */
    public static void main(String[] args) throws SQLException {
        clasee.actualizarContraseas();
        System.out.println("Todas las contraseñas han sido encriptadas exitosamente.");
    }

    /**
     * Método que encripta las contraseñas almacenadas en texto plano en la base de datos y las actualiza.
     * <p>
     * Este método realiza lo siguiente:
     * <ul>
     *   <li>Consulta todas las contraseñas actuales almacenadas en la tabla <b>usuarios</b>.</li>
     *   <li>Encripta cada contraseña utilizando el método {@code PasswordUtil.hashPassword()}.</li>
     *   <li>Actualiza la contraseña en la base de datos.</li>
     * </ul>
     * </p>
     * 
     * @throws SQLException Si ocurre algún error durante la conexión, consulta o actualización en la base de datos.
     */
    public static void actualizarContraseas() throws SQLException {
        // Consulta para obtener los datos actuales
        String selectSql = "SELECT id, pass FROM usuarios";

        // Consulta para actualizar la contraseña
        String updateSql = "UPDATE usuarios SET pass = ? WHERE id = ?";

        // Obtener conexión a la base de datos
        Connection conn2 = ConexionBaseDatos.getConnection();

        try (
            PreparedStatement selectStmt = conn2.prepareStatement(selectSql);
            PreparedStatement updateStmt = conn2.prepareStatement(updateSql);
            ResultSet rs = selectStmt.executeQuery()
        ) {
            // Iterar sobre los resultados de la consulta
            while (rs.next()) {
                int id = rs.getInt("id"); // Obtener ID del usuario
                String plainPassword = rs.getString("pass"); // Obtener contraseña en texto plano

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
