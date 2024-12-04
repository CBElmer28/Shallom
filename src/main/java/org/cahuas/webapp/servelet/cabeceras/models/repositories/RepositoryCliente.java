package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import java.sql.SQLException;

public interface RepositoryCliente <T>{

    T ClienteporId(int id) throws SQLException;

    T existeCliente(String username);

    void crearCliente(String co,int tel,int id_usuario,String nombre)throws SQLException;

    void editarCliente(int id,String co,int tel,int id_usuario,String nombre)throws SQLException;

    T buscarPorUsuarioId(int id) throws SQLException;
}
