package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.ClienteRepositoryJdbcImpl;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryCliente;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementación de {@link ClienteService} que utiliza JDBC para realizar operaciones sobre clientes.
 * <p>
 * Esta clase delega las operaciones a una instancia de {@link RepositoryCliente},
 * concretamente {@link ClienteRepositoryJdbcImpl}, para interactuar con la base de datos.
 * </p>
 * 
 * @author Team Shalom
 * @version 1.5
 */
public class ClienteServiceJdbcImpl implements ClienteService {

    /**
     * Repositorio utilizado para realizar las operaciones sobre la entidad Cliente.
     */
    private final RepositoryCliente<Cliente> repositoycliente;

    /**
     * Constructor que inicializa el repositorio con una conexión a la base de datos.
     * 
     * @param c La conexión JDBC utilizada por el repositorio.
     */
    public ClienteServiceJdbcImpl(Connection c) {
        this.repositoycliente = new ClienteRepositoryJdbcImpl(c);
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id El ID del cliente a buscar.
     * @return La instancia de {@link Cliente} correspondiente al ID.
     * @throws SQLException Si ocurre un error en la operación JDBC.
     */
    @Override
    public Cliente ClienteporId(int id) throws SQLException {
        try {
            return repositoycliente.ClienteporId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Verifica si existe un cliente con el nombre de usuario proporcionado.
     * 
     * @param username El nombre de usuario a buscar.
     * @return La instancia de {@link Cliente} si existe, de lo contrario, {@code null}.
     */
    @Override
    public Cliente existeCliente(String username) {
        return repositoycliente.existeCliente(username);
    }

    /**
     * Crea un nuevo cliente con los datos proporcionados.
     * 
     * @param co El correo electrónico del cliente.
     * @param tel El teléfono del cliente.
     * @param id_usuario El ID del usuario asociado al cliente.
     * @param nombre El nombre del cliente.
     * @throws SQLException Si ocurre un error al insertar los datos.
     */
    @Override
    public void crearCliente(String co, int tel, int id_usuario, String nombre) throws SQLException {
        repositoycliente.crearCliente(co, tel, id_usuario, nombre);
    }

    /**
     * Edita un cliente existente con los nuevos datos proporcionados.
     * 
     * @param id El ID del cliente a editar.
     * @param co El nuevo correo electrónico del cliente.
     * @param tel El nuevo teléfono del cliente.
     * @param id_usuario El nuevo ID del usuario asociado al cliente.
     * @param nombre El nuevo nombre del cliente.
     * @throws SQLException Si ocurre un error al actualizar los datos.
     */
    @Override
    public void editarCliente(int id, String co, int tel, int id_usuario, String nombre) throws SQLException {
        repositoycliente.editarCliente(id, co, tel, id_usuario, nombre);
    }

    /**
     * Busca un cliente por el ID de usuario asociado.
     * 
     * @param id El ID del usuario asociado al cliente.
     * @return La instancia de {@link Cliente} correspondiente al ID del usuario.
     * @throws SQLException Si ocurre un error en la operación JDBC.
     */
    @Override
    public Cliente buscarPorUsuarioId(int id) throws SQLException {
        return repositoycliente.buscarPorUsuarioId(id);
    }

}
