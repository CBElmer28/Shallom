package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();

    Optional<Producto> porId(int id);

    void guardar(Producto producto);

    void eliminar(int id);

    List<Proveedor> listarCategoria();

    Proveedor porIdCategoria(int id);
}
