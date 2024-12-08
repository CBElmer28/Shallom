package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Producto;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Proveedor;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.ProductoRepositoryImpl;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.ProveedorRepositoryImpl;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de productos y proveedores utilizando JDBC.
 * 
 * Esta clase gestiona las operaciones CRUD para productos y la gestión de categorías de proveedores.
 * 
 * @see Producto
 * @see Proveedor
 * @see ProductoService
 * @see ProductoRepositoryImpl
 * @see ProveedorRepositoryImpl
 * 
 * @author Team Shalom
 * @version 1.5
 */
public class ProductoServiceJdbcImpl implements ProductoService {

    private final Repository<Producto> repositoryJdbc;
    private final Repository<Proveedor> repositoryProveedorJdbc;

    /**
     * Constructor que inicializa los repositorios de productos y proveedores.
     * 
     * @param connection La conexión JDBC a utilizar para las operaciones.
     */
    public ProductoServiceJdbcImpl(Connection connection) {
        this.repositoryJdbc = new ProductoRepositoryImpl(connection);
        this.repositoryProveedorJdbc = new ProveedorRepositoryImpl(connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Producto> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Producto> porId(int id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Producto buscarId(int id) {
        try {
            return repositoryJdbc.porId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void guardar(Producto producto) {
        try {
            repositoryJdbc.guardar(producto);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminar(int id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proveedor> listarCategoria() {
        try {
            return repositoryProveedorJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proveedor porIdCategoria(int id) {
        try {
            return repositoryProveedorJdbc.porId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
