package org.cahuas.webapp.servelet.cabeceras.models.modelo;

import java.util.Objects;

/**
 * Clase que representa un item en el carrito de compras. Cada item tiene una cantidad
 * y un producto asociado, y se utiliza para gestionar los artículos que el usuario desea
 * comprar en el carrito de compras.
 * 
 * @author Team Shalom
 * @version 1.2
 */
public class ItemCarro {

    // Cantidad de productos de este item en el carrito
    private int cantidad;
    
    // Producto asociado a este item en el carrito
    private Producto producto;

    /**
     * Constructor para inicializar un item de carrito con una cantidad y un producto.
     *
     * @param cantidad La cantidad de este producto en el carrito.
     * @param producto El producto asociado a este item.
     */
    public ItemCarro(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad de productos de este item en el carrito.
     *
     * @return La cantidad de productos.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos de este item en el carrito.
     *
     * @param cantidad La cantidad de productos.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto asociado a este item en el carrito.
     *
     * @return El producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado a este item en el carrito.
     *
     * @param producto El producto.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Compara dos objetos ItemCarro para verificar si son iguales. Dos items se consideran
     * iguales si contienen el mismo producto (basado en el ID del producto).
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return producto.getId().equals(itemCarro.getProducto().getId());
    }

    /**
     * Genera el código hash para el objeto ItemCarro basado en el ID del producto.
     *
     * @return El código hash del item.
     */
    @Override
    public int hashCode() {
        return Objects.hash(producto.getId());
    }

    /**
     * Calcula el importe total de este item en el carrito, que es el precio del producto
     * multiplicado por la cantidad de productos en el carrito.
     *
     * @return El importe total de este item en el carrito.
     */
    public int getImporte() {
        return cantidad * producto.getPrecio();
    }
}
