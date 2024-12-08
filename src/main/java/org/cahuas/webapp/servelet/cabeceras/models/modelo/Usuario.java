package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa un usuario en el sistema. Un usuario tiene un identificador,
 * un DNI, un nombre de usuario, una contraseña y un tipo de usuario (por ejemplo, administrador, cliente, etc.).
 * 
 * @author Team Shalom
 * @version 1.3
 */
public class Usuario {
    
    // Identificador único del usuario
    int id;
    
    // Número de DNI del usuario
    int dni;
    
    // Nombre de usuario para autenticación
    String user;
    
    // Contraseña asociada al usuario
    String pass;
    
    // Tipo de usuario (por ejemplo, administrador, cliente)
    String tipo;

    /**
     * Constructor para crear un usuario con todos los atributos.
     *
     * @param id El identificador del usuario.
     * @param dni El número de DNI del usuario.
     * @param user El nombre de usuario para la autenticación.
     * @param pass La contraseña del usuario.
     * @param tipo El tipo de usuario (por ejemplo, administrador, cliente).
     */
    public Usuario(int id, int dni, String user, String pass, String tipo) {
        this.id = id;
        this.dni = dni;
        this.user = user;
        this.pass = pass;
        this.tipo = tipo;
    }

    /**
     * Constructor para crear un usuario sin el DNI.
     *
     * @param id El identificador del usuario.
     * @param user El nombre de usuario para la autenticación.
     * @param pass La contraseña del usuario.
     * @param tipo El tipo de usuario (por ejemplo, administrador, cliente).
     */
    public Usuario(int id, String user, String pass, String tipo) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.tipo = tipo;
    }

    /**
     * Constructor vacío para inicialización del usuario.
     */
    public Usuario() {
    }

    /**
     * Obtiene el identificador del usuario.
     *
     * @return El identificador del usuario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del usuario.
     *
     * @param id El identificador del usuario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el número de DNI del usuario.
     *
     * @return El número de DNI del usuario.
     */
    public int getDni() {
        return dni;
    }

    /**
     * Establece el número de DNI del usuario.
     *
     * @param dni El número de DNI del usuario.
     */
    public void setDni(int dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param user El nombre de usuario.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPass() {
        return pass;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param pass La contraseña del usuario.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Obtiene el tipo de usuario (por ejemplo, administrador, cliente).
     *
     * @return El tipo de usuario.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipo El tipo de usuario (por ejemplo, administrador, cliente).
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
