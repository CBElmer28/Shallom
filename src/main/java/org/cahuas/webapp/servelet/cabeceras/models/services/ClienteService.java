package org.cahuas.webapp.servelet.cabeceras.models.services;

import org.cahuas.webapp.servelet.cabeceras.models.modelo.Cliente;
import org.cahuas.webapp.servelet.cabeceras.models.repositories.RepositoryCliente;

/**
 * Interfaz que extiende la funcionalidad de {@link RepositoryCliente} para trabajar con entidades de tipo {@link Cliente}.
 * <p>
 * Esta interfaz permite definir métodos adicionales específicos para el manejo de clientes,
 * además de los métodos genéricos heredados de {@link RepositoryCliente}.
 * </p>
 * 
 * @param <Cliente> La entidad que representa un cliente en la aplicación.
 * 
 * @author Team Shalom
 * @version 1.5
 */
public interface ClienteService extends RepositoryCliente<Cliente> {
}
