package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.ClienteRepositoryJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryCliente;

import java.sql.Connection;
import java.sql.SQLException;

public class ClienteServiceJdbcImpl implements ClienteService {

    RepositoryCliente<Cliente> repositoycliente;

    public ClienteServiceJdbcImpl(Connection c) {
        this.repositoycliente = new ClienteRepositoryJdbcImpl(c);
    }

    @Override
    public Cliente ClienteporId(int id) throws SQLException {
        try {
            return repositoycliente.ClienteporId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Cliente existeCliente(String username) {
        return repositoycliente.existeCliente(username);
    }

    @Override
    public void crearCliente(String co, int tel, int id_usuario, String nombre) throws SQLException {
        repositoycliente.crearCliente(co, tel, id_usuario, nombre);
    }

    @Override
    public void editarCliente(int id,String co, int tel, int id_usuario, String nombre) throws SQLException {
        repositoycliente.editarCliente(id,co, tel, id_usuario, nombre);
    }

    @Override
    public Cliente buscarPorUsuarioId(int id) throws SQLException {
        return  repositoycliente.buscarPorUsuarioId(id);
    }

}
