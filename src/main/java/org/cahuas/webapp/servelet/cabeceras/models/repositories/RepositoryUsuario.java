/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import java.sql.SQLException;

public interface RepositoryUsuario<T> {
    T UsuarioporId(int id) throws SQLException;

    T UsuarioSesion(String username,String password) throws SQLException;

    T existeUsuario(String username) throws SQLException;

    void crearCuenta(int dni,String usuario,String pass,String tipo)throws SQLException;

    void editarCuenta(int id,int dni,String usuario,String pass,String tipo)throws SQLException;

    void eliminarUsuario(int dni)throws SQLException;

}
