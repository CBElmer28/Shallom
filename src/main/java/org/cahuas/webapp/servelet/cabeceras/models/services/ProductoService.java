package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para definir los servicios relacionados con la gestión de productos y proveedores.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD en productos y para gestionar categorías de proveedores.
 * </p>
 * 
 * @see Producto
 * @see Proveedor
 * 
 * @author Team Shalom
 * @version 1.3
 */
public interface ProductoService {

    /**
     * Lista todos los productos disponibles.
     * 
     * @return Una lista de todos los productos.
     */
    List<Producto> listar();

    /**
     * Busca un producto por su ID.
     * 
     * @param id El ID del producto a buscar.
     * @return Un {@link Optional} que contiene el producto si se encuentra, o vacío en caso contrario.
     */
    Optional<Producto> porId(int id);

    /**
     * Busca un producto por su ID y lo retorna directamente.
     * 
     * @param id El ID del producto a buscar.
     * @return La instancia de {@link Producto} correspondiente al ID.
     */
    Producto buscarId(int id);

    /**
     * Guarda un producto nuevo o actualiza uno existente.
     * 
     * @param producto El producto a guardar o actualizar.
     */
    void guardar(Producto producto);

    /**
     * Elimina un producto por su ID.
     * 
     * @param id El ID del producto a eliminar.
     */
    void eliminar(int id);

    /**
     * Lista todas las categorías de proveedores disponibles.
     * 
     * @return Una lista de proveedores que representan las categorías.
     */
    List<Proveedor> listarCategoria();

    /**
     * Busca un proveedor por su ID de categoría.
     * 
     * @param id El ID de la categoría del proveedor.
     * @return La instancia de {@link Proveedor} correspondiente al ID de la categoría.
     */
    Proveedor porIdCategoria(int id);
}
