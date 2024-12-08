package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa un proveedor en el sistema. Un proveedor tiene un identificador,
 * número de RUC, dirección, teléfono y un código asociado.
 * 
 * @author Team Shalom
 * @version 1.0
 */
public class Proveedor {
    
    // Identificador único del proveedor
    int id;
    
    // Número de RUC del proveedor
    int ruc;
    
    // Dirección del proveedor
    String direc;
    
    // Teléfono del proveedor
    int tel;
    
    // Código asociado al proveedor
    String co;

    /**
     * Constructor para crear un proveedor con todos sus atributos.
     *
     * @param id El identificador del proveedor.
     * @param ruc El número de RUC del proveedor.
     * @param direc La dirección del proveedor.
     * @param tel El número de teléfono del proveedor.
     * @param co El código asociado al proveedor.
     */
    public Proveedor(int id, int ruc, String direc, int tel, String co) {
        this.id = id;
        this.ruc = ruc;
        this.direc = direc;
        this.tel = tel;
        this.co = co;
    }

    /**
     * Constructor vacío para inicialización del proveedor.
     */
    public Proveedor() {
    }

    /**
     * Obtiene el identificador del proveedor.
     *
     * @return El identificador del proveedor.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del proveedor.
     *
     * @param id El identificador del proveedor.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el número de RUC del proveedor.
     *
     * @return El número de RUC del proveedor.
     */
    public int getRuc() {
        return ruc;
    }

    /**
     * Establece el número de RUC del proveedor.
     *
     * @param ruc El número de RUC del proveedor.
     */
    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    /**
     * Obtiene la dirección del proveedor.
     *
     * @return La dirección del proveedor.
     */
    public String getDirec() {
        return direc;
    }

    /**
     * Establece la dirección del proveedor.
     *
     * @param direc La dirección del proveedor.
     */
    public void setDirec(String direc) {
        this.direc = direc;
    }

    /**
     * Obtiene el número de teléfono del proveedor.
     *
     * @return El número de teléfono del proveedor.
     */
    public int getTel() {
        return tel;
    }

    /**
     * Establece el número de teléfono del proveedor.
     *
     * @param tel El número de teléfono del proveedor.
     */
    public void setTel(int tel) {
        this.tel = tel;
    }

    /**
     * Obtiene el código asociado al proveedor.
     *
     * @return El código asociado al proveedor.
     */
    public String getCo() {
        return co;
    }

    /**
     * Establece el código asociado al proveedor.
     *
     * @param co El código asociado al proveedor.
     */
    public void setCo(String co) {
        this.co = co;
    }
}
