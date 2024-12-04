package org.cahuas.webapp.servelet.cabeceras.models.modelo;

/**
 * Clase que representa los detalles de un producto dentro de un carrito de compras. 
 * Incluye información sobre el nombre del producto, cantidad, precio unitario y el total calculado por producto.
 * 
 * @author Team Shalom
 * @version 1.2
 */
public class DatalleProducto {
    
    // Nombre del producto
    private String nom;
    
    // Cantidad del producto
    private Integer cantidad;
    
    // Precio unitario del producto
    private Integer pUnitario;
    
    // Total calculado para el producto (cantidad * precio unitario)
    private Integer totalproduc;

    /**
     * Constructor que inicializa un detalle de producto con los valores proporcionados.
     *
     * @param cantidad La cantidad del producto.
     * @param nom El nombre del producto.
     * @param pUnitario El precio unitario del producto.
     * @param totalproductoo El total calculado para el producto.
     */
    public DatalleProducto(Integer cantidad, String nom, Integer pUnitario, Integer totalproductoo) {
        this.nom = nom;
        this.cantidad = cantidad;
        this.pUnitario = pUnitario;
        this.totalproduc = totalproductoo;
    }

    /**
     * Constructor vacío para crear una instancia de DatalleProducto sin inicializar los valores.
     */
    public DatalleProducto() {
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
     * Obtiene la cantidad del producto.
     *
     * @return La cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Obtiene el precio unitario del producto.
     *
     * @return El precio unitario del producto.
     */
    public Integer getpUnitario() {
        return pUnitario;
    }

    /**
     * Obtiene el total calculado para el producto (cantidad * precio unitario).
     *
     * @return El total calculado para el producto.
     */
    public Integer getTotalproduc() {
        return totalproduc;
    }

    /**
     * Constructor que inicializa un detalle de producto con el nombre, cantidad y total calculado.
     *
     * @param nom El nombre del producto.
     * @param cantidad La cantidad del producto.
     * @param totalproductoo El total calculado para el producto.
     */
    public DatalleProducto(String nom, Integer cantidad, Integer totalproductoo) {
        this.nom = nom;
        this.cantidad = cantidad;
        this.totalproduc = totalproductoo;
    }
}
