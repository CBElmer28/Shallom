package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Usuario;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryUsuario;

/**
 * Interfaz que extiende la funcionalidad de {@link RepositoryUsuario} para manejar operaciones relacionadas con el login.
 * <p>
 * Proporciona un contrato para gestionar usuarios durante el proceso de autenticación.
 * </p>
 * 
 * @param <Usuario> La entidad que representa un usuario en la aplicación.
 * 
 * @author Team Shalom
 * @version 1.5
 */
public interface LoginService extends RepositoryUsuario<Usuario> {

}
