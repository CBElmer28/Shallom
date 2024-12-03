package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class DetalleVenta {
    private int id;
    private int idVenta; // ID de la venta a la que pertenece este detalle
    private Producto producto; // Producto comprado
    private int cantidad; // Cantidad comprada de este producto
    private double precioUnitario; // Precio por unidad del producto
    private double subtotal; // Subtotal de la cantidad comprada (cantidad * precioUnitario)

    // Constructor vacío
    public DetalleVenta() {}

    // Constructor con parámetros
    public DetalleVenta(int id, int idVenta, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public DetalleVenta(int idVenta, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public DetalleVenta(int id, int idVenta, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    // Método toString() para depuración
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
