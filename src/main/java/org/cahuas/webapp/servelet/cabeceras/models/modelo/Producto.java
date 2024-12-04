package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa un producto en el sistema de inventario. Un producto tiene un
 * identificador, nombre, categoría, precio, cantidad en stock, proveedor asociado y
 * una ruta a su imagen.
 * 
 * @author Team Shalom
 * @version 1.2
 */
public class Producto {

    // Identificador único del producto
    private Integer id;
    
    // Nombre del producto
    private String nom;
    
    // Categoría del producto
    private String cat;
    
    // Precio del producto
    private int precio;
    
    // Cantidad en stock del producto
    private int stock;

    // Proveedor asociado a este producto
    private Proveedor id_proveedor;
    
    // Ruta de la imagen del producto
    private String ruta_imagen;

    /**
     * Constructor para crear un producto con todos sus atributos.
     *
     * @param id El identificador del producto.
     * @param nom El nombre del producto.
     * @param cat La categoría del producto.
     * @param precio El precio del producto.
     * @param proveedor El proveedor del producto.
     * @param stock La cantidad de este producto en stock.
     * @param ruta_imagen La ruta de la imagen del producto.
     */
    public Producto(Integer id, String nom, String cat, int precio, String proveedor, int stock, String ruta_imagen) {
        this.id = id;
        this.nom = nom;
        this.cat = cat;
        this.precio = precio;
        this.stock = stock;
        this.id_proveedor = new Proveedor();
        id_proveedor.setCo(proveedor);
        this.ruta_imagen = ruta_imagen;
    }

    /**
     * Constructor para crear un producto con un proveedor dado.
     *
     * @param nom El nombre del producto.
     * @param cat La categoría del producto.
     * @param precio El precio del producto.
     * @param stock La cantidad de este producto en stock.
     * @param id_proveedor El proveedor del producto.
     * @param ruta_imagen La ruta de la imagen del producto.
     */
    public Producto(String nom, String cat, int precio, int stock, Proveedor id_proveedor, String ruta_imagen) {
        this.nom = nom;
        this.cat = cat;
        this.precio = precio;
        this.stock = stock;
        this.id_proveedor = id_proveedor;
        this.ruta_imagen = ruta_imagen;
    }

    /**
     * Constructor utilizado para realizar pruebas con productos básicos (ID, nombre y precio).
     *
     * @param id El identificador del producto.
     * @param nom El nombre del producto.
     * @param precio El precio del producto.
     */
    public Producto(int id, String nom, int precio) {
        this.id = id;
        this.nom = nom;
        this.precio = precio;
    }

    /**
     * Constructor vacío para inicialización del producto.
     */
    public Producto() {
    }

    /**
     * Constructor para crear un producto con ID, nombre, precio y cantidad en stock.
     *
     * @param id El identificador del producto.
     * @param nom El nombre del producto.
     * @param precio El precio del producto.
     * @param stock La cantidad de este producto en stock.
     */
    public Producto(int id, String nom, int precio, int stock) {
        this.id = id;
        this.nom = nom;
        this.precio = precio;
        this.stock = stock;
    }

    // Métodos getter y setter para acceder y modificar los atributos del producto

    /**
     * Obtiene el identificador del producto.
     *
     * @return El identificador del producto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id El identificador del producto.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nom El nombre del producto.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return La categoría del producto.
     */
    public String getCat() {
        return cat;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param cat La categoría del producto.
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * Obtiene el precio del producto.
     *
     * @return El precio del producto.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio El precio del producto.
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la cantidad de este producto en stock.
     *
     * @return La cantidad en stock del producto.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de este producto en stock.
     *
     * @param stock La cantidad en stock del producto.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el proveedor asociado al producto.
     *
     * @return El proveedor del producto.
     */
    public Proveedor getId_proveedor() {
        return id_proveedor;
    }

    /**
     * Establece el proveedor asociado al producto.
     *
     * @param id_proveedor El proveedor del producto.
     */
    public void setId_proveedor(Proveedor id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * Obtiene la ruta de la imagen del producto.
     *
     * @return La ruta de la imagen del producto.
     */
    public String getRuta_imagen() {
        return ruta_imagen;
    }

    /**
     * Establece la ruta de la imagen del producto.
     *
     * @param ruta_imagen La ruta de la imagen del producto.
     */
    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
