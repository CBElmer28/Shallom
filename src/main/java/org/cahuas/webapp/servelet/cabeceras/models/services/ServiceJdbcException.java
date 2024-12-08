package org.cahuas.webapp.servelet.cabeceras.models.services;

/**
 * Excepción personalizada para manejar errores relacionados con servicios JDBC.
 * 
 * Proporciona constructores para manejar mensajes de error y causas específicas de la excepción.
 * 
 * @see RuntimeException
 * 
 * @author Team Shalom
 * @version 1.4
 */
public class ServiceJdbcException extends RuntimeException {

    /**
     * Constructor que crea una excepción con un mensaje detallado.
     * 
     * @param message El mensaje de error detallado.
     */
    public ServiceJdbcException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una excepción con un mensaje detallado y una causa subyacente.
     * 
     * @param message El mensaje de error detallado.
     * @param cause   La causa original de la excepción.
     */
    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
