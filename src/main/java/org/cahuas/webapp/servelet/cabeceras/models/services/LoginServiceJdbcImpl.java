package org.cahuas.webapp.servelet.cabeceras.models.services;

import java.sql.Connection;
import java.sql.SQLException;
import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryUsuario;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.UsuarioRepositoryJdbcImpl;

/**
 * Implementación de {@link LoginService} que utiliza JDBC para gestionar operaciones de login y usuarios.
 * <p>
 * Esta clase delega las operaciones a una instancia de {@link RepositoryUsuario},
 * concretamente {@link UsuarioRepositoryJdbcImpl}, para interactuar con la base de datos.
 * </p>
 * 
 * @author Team Shalom
 * @version 1.5
 */
public class LoginServiceJdbcImpl implements LoginService {

    /**
     * Repositorio utilizado para realizar las operaciones sobre la entidad Usuario.
     */
    private final RepositoryUsuario<Usuario> UsuarioRepository;

    /**
     * Constructor que inicializa el repositorio con una conexión a la base de datos.
     * 
     * @param connection La conexión JDBC utilizada por el repositorio.
     */
    public LoginServiceJdbcImpl(Connection connection) {
        this.UsuarioRepository = new UsuarioRepositoryJdbcImpl(connection);
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param id El ID del usuario a buscar.
     * @return La instancia de {@link Usuario} correspondiente al ID.
     * @throws SQLException Si ocurre un error en la operación JDBC.
     */
    @Override
    public Usuario UsuarioporId(int id) throws SQLException {
        try {
            return UsuarioRepository.UsuarioporId(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Verifica las credenciales de un usuario para iniciar sesión.
     * 
     * @param username El nombre de usuario.
     * @param password La contraseña del usuario.
     * @return La instancia de {@link Usuario} si las credenciales son válidas.
     * @throws SQLException Si ocurre un error en la operación JDBC.
     */
    @Override
    public Usuario UsuarioSesion(String username, String password) throws SQLException {
        try {
            return UsuarioRepository.UsuarioSesion(username, password);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Verifica si existe un usuario con el nombre de usuario proporcionado.
     * 
     * @param username El nombre de usuario a buscar.
     * @return La instancia de {@link Usuario} si existe, de lo contrario, {@code null}.
     * @throws SQLException Si ocurre un error en la operación JDBC.
     */
    @Override
    public Usuario existeUsuario(String username) throws SQLException {
        try {
            return UsuarioRepository.existeUsuario(username);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Crea una nueva cuenta de usuario con los datos proporcionados.
     * 
     * @param dni El DNI del usuario.
     * @param usuario El nombre de usuario.
     * @param pass La contraseña del usuario.
     * @param tipo El tipo de usuario (e.g., administrador, cliente).
     * @throws SQLException Si ocurre un error al insertar los datos.
     */
    @Override
    public void crearCuenta(int dni, String usuario, String pass, String tipo) throws SQLException {
        try {
            UsuarioRepository.crearCuenta(dni, usuario, pass, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Edita una cuenta de usuario existente con los nuevos datos proporcionados.
     * 
     * @param id El ID del usuario a editar.
     * @param dni El nuevo DNI del usuario.
     * @param usuario El nuevo nombre de usuario.
     * @param pass La nueva contraseña del usuario.
     * @param tipo El nuevo tipo de usuario.
     * @throws SQLException Si ocurre un error al actualizar los datos.
     */
    @Override
    public void editarCuenta(int id, int dni, String usuario, String pass, String tipo) throws SQLException {
        try {
            UsuarioRepository.editarCuenta(id, dni, usuario, pass, tipo);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    /**
     * Elimina un usuario de la base de datos por su DNI.
     * 
     * @param dni El DNI del usuario a eliminar.
     * @throws SQLException Si ocurre un error durante la operación JDBC.
     */
    @Override
    public void eliminarUsuario(int dni) throws SQLException {
        try {
            UsuarioRepository.eliminarUsuario(dni);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

}
