package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa un detalle de venta en un sistema de gestión de ventas.
 * Cada instancia de esta clase contiene la información sobre un producto vendido,
 * su cantidad, el precio unitario y el subtotal de la venta.
 * 
 * @author Team Shalom
 * @version 1.2
 */
public class DetalleVenta {

    // ID único del detalle de venta
    private int id;
    
    // ID de la venta a la que pertenece este detalle
    private int idVenta; 
    
    // Producto comprado
    private Producto producto; 
    
    // Cantidad comprada de este producto
    private int cantidad; 
    
    // Precio por unidad del producto
    private double precioUnitario; 
    
    // Subtotal de la cantidad comprada (cantidad * precioUnitario)
    private double subtotal; 

    /**
     * Constructor vacío para crear una instancia de DetalleVenta sin inicializar atributos.
     */
    public DetalleVenta() {}

    /**
     * Constructor con parámetros para inicializar todos los atributos del detalle de venta.
     *
     * @param id El ID único del detalle de venta.
     * @param idVenta El ID de la venta a la que pertenece este detalle.
     * @param producto El producto comprado.
     * @param cantidad La cantidad comprada de este producto.
     * @param precioUnitario El precio por unidad del producto.
     * @param subtotal El subtotal de la cantidad comprada (cantidad * precioUnitario).
     */
    public DetalleVenta(int id, int idVenta, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    /**
     * Constructor para inicializar un detalle de venta sin un ID, común cuando se crea un detalle nuevo.
     *
     * @param idVenta El ID de la venta a la que pertenece este detalle.
     * @param producto El producto comprado.
     * @param cantidad La cantidad comprada de este producto.
     * @param precioUnitario El precio por unidad del producto.
     * @param subtotal El subtotal de la cantidad comprada (cantidad * precioUnitario).
     */
    public DetalleVenta(int idVenta, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    /**
     * Constructor para inicializar un detalle de venta con ID, pero sin especificar el producto.
     *
     * @param id El ID único del detalle de venta.
     * @param idVenta El ID de la venta a la que pertenece este detalle.
     * @param cantidad La cantidad comprada de este producto.
     * @param precioUnitario El precio por unidad del producto.
     * @param subtotal El subtotal de la cantidad comprada (cantidad * precioUnitario).
     */
    public DetalleVenta(int id, int idVenta, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    /**
     * Obtiene el ID único del detalle de venta.
     *
     * @return El ID del detalle de venta.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID único del detalle de venta.
     *
     * @param id El ID del detalle de venta.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID de la venta a la que pertenece este detalle.
     *
     * @return El ID de la venta.
     */
    public int getIdVenta() {
        return idVenta;
    }

    /**
     * Establece el ID de la venta a la que pertenece este detalle.
     *
     * @param idVenta El ID de la venta.
     */
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    /**
     * Obtiene el producto comprado en este detalle de venta.
     *
     * @return El producto comprado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto comprado en este detalle de venta.
     *
     * @param producto El producto comprado.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad comprada de este producto en este detalle de venta.
     *
     * @return La cantidad comprada.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad comprada de este producto en este detalle de venta.
     *
     * @param cantidad La cantidad comprada.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto comprado en este detalle de venta.
     *
     * @return El precio unitario del producto.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto comprado en este detalle de venta.
     *
     * @param precioUnitario El precio unitario del producto.
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el subtotal de la venta (cantidad * precioUnitario).
     *
     * @return El subtotal de la venta.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Establece el subtotal de la venta (cantidad * precioUnitario).
     *
     * @param subtotal El subtotal de la venta.
     */
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Método toString para representar el detalle de venta como una cadena de texto.
     *
     * @return Una cadena representando el detalle de venta.
     */
    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id=" + id +
                ", idVenta=" + idVenta +
                ", producto=" + producto.getNom() +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
