/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author keyen
 */
public class ConexionBaseDatos {
      private static String url="jdbc:mysql://localhost:3306/libreria?serverTimezone=America/Lima";
    private static String username="root";
    private static String password="keyen102030";

    public  static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }
}
