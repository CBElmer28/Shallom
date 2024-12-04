package org.cahuas.webapp.servelet.cabeceras.models.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url="jdbc:mysql://localhost:3306/libreria?serverTimezone=America/Lima";
    private static String username="root";
    private static String password="Gda1232546.12";

    public  static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
