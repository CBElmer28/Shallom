package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa a un cliente en el sistema. 
 * Esta clase contiene información básica sobre el cliente, como su ID, código, número de teléfono, 
 * ID de usuario asociado y nombre.
 * 
 * @author Team Shalom
 * @version 1.5
 *
 */
public class Cliente {

    // ID único del cliente
    int id;

    // Código del cliente
    String co;

    // Número de teléfono del cliente
    int tel;

    // ID del usuario asociado al cliente
    int id_usuario;

    // Nombre del cliente
    String nombre;

    /**
     * Constructor que inicializa un cliente con los valores proporcionados.
     *
     * @param id El ID del cliente.
     * @param co El código del cliente.
     * @param tel El número de teléfono del cliente.
     * @param id_usuario El ID del usuario asociado al cliente.
     * @param nombre El nombre del cliente.
     */
    public Cliente(int id, String co, int tel, int id_usuario, String nombre) {
        this.id = id;
        this.co = co;
        this.tel = tel;
        this.nombre = nombre;
        this.id_usuario = id_usuario;
    }

    /**
     * Constructor vacío para crear una instancia de cliente sin inicializar valores.
     */
    public Cliente() {
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id El nuevo ID del cliente.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el código del cliente.
     *
     * @return El código del cliente.
     */
    public String getCo() {
        return co;
    }

    /**
     * Establece el código del cliente.
     *
     * @param co El nuevo código del cliente.
     */
    public void setCo(String co) {
        this.co = co;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public int getTel() {
        return tel;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param tel El nuevo número de teléfono del cliente.
     */
    public void setTel(int tel) {
        this.tel = tel;
    }

    /**
     * Obtiene el ID de usuario asociado al cliente.
     *
     * @return El ID de usuario asociado al cliente.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el ID de usuario asociado al cliente.
     *
     * @param id_usuario El nuevo ID de usuario.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
