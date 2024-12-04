/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.cahuas.webapp.servelet.cabeceras.models.repositories;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author keyen
 */
public interface Repository <T>{
     List<T> listar()throws SQLException;
    T porId(int id) throws SQLException;
    void guardar(T t) throws SQLException;
    void eliminar(int id) throws SQLException;

}
