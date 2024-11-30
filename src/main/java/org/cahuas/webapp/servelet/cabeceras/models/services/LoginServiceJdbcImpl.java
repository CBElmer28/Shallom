/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.services;

import java.sql.Connection;
import java.sql.SQLException;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryUsuario;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.UsuarioRepositoryJdbcImpl;

/**
 *
 * @author keyen
 */
public class LoginServiceJdbcImpl implements LoginService{
    
    private final RepositoryUsuario<Usuario> UsuarioRepository;

    public LoginServiceJdbcImpl(Connection connection) {
        this.UsuarioRepository =new UsuarioRepositoryJdbcImpl(connection);
    }

    @Override
    public Usuario UsuarioporId(int id) throws SQLException {
      try {
           return UsuarioRepository.UsuarioporId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Usuario UsuarioSesion(String username, String password) throws SQLException {
        try {
           return UsuarioRepository.UsuarioSesion(username, password);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Usuario existeUsuario(String username) throws SQLException {
        try {
            return UsuarioRepository.existeUsuario(username);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void crearCuenta(int dni, String usuario, String pass, String tipo) throws SQLException {
        try {
            UsuarioRepository.crearCuenta(dni, usuario, pass, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void editarCuenta(int id,int dni, String usuario, String pass, String tipo) throws SQLException {
      try {
            UsuarioRepository.editarCuenta(id,dni, usuario, pass, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void eliminarUsuario(int dni) throws SQLException {
       try {
            UsuarioRepository.eliminarUsuario(dni);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
    
}
