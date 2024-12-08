package org.cahuas.webapp.servelet.cabeceras.models.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Representa un carro de compras en una tienda en línea. El carro mantiene una lista de items (productos) 
 * y permite operaciones como agregar, eliminar, actualizar cantidades, obtener totales y detalles de los productos.
 *
 * @autor Team Shalom
 * @version 1.2
 */
public class Carro {

    // Lista que contiene los items del carro de compras
    private List<ItemCarro> items;

    /**
     * Constructor que inicializa el carro con una lista vacía de items.
     */
    public Carro() {
        this.items = new ArrayList<>();
    }

    /**
     * Agrega un item al carro. Si el producto ya existe en el carro, se actualiza la cantidad.
     *
     * @param itemCarro El item que se desea agregar al carro.
     */
    public void addItemCarro(ItemCarro itemCarro) {
        Optional<ItemCarro> existingItem = items.stream()
                .filter(i -> i.getProducto().getId().equals(itemCarro.getProducto().getId()))
                .findFirst();

        // Si el producto ya está en el carro, se actualiza la cantidad.
        if (existingItem.isPresent()) {
            ItemCarro existing = existingItem.get();
            existing.setCantidad(existing.getCantidad() + itemCarro.getCantidad());
        } else {
            // Si no está en el carro, se agrega como un nuevo item.
            items.add(itemCarro);
        }
    }

    /**
     * Obtiene la lista de todos los items en el carro.
     *
     * @return Lista de items en el carro.
     */
    public List<ItemCarro> getItems() {
        return items;
    }

    /**
     * Obtiene la cantidad de un producto específico en el carro.
     *
     * @param productoId El ID del producto.
     * @return La cantidad del producto en el carro, o 0 si no está presente.
     */
    public int cantidad(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .mapToInt(ItemCarro::getCantidad)
                .findFirst()
                .orElse(0);
    }

    /**
     * Obtiene el total de un producto específico en el carro (importe).
     *
     * @param productoId El ID del producto.
     * @return El total de importe del producto en el carro, o 0 si no está presente.
     */
    public int getTotalProduc(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .mapToInt(ItemCarro::getImporte)
                .findFirst()
                .orElse(0);
    }

    /**
     * Obtiene el total general de todos los productos en el carro.
     *
     * @return El total general de todos los productos en el carro.
     */
    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    /**
     * Elimina productos de la lista de items del carro.
     * 
     * @param productoIds Una lista de IDs de los productos a eliminar.
     */
    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
        }
    }

    /**
     * Elimina un producto específico del carro utilizando su ID.
     *
     * @param productoId El ID del producto a eliminar.
     */
    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    /**
     * Actualiza la cantidad de un producto en el carro.
     *
     * @param productoId El ID del producto a actualizar.
     * @param cantidad La nueva cantidad del producto.
     */
    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    /**
     * Busca un producto en el carro utilizando su ID.
     *
     * @param productoId El ID del producto a buscar.
     * @return Un Optional que contiene el item del carro si se encuentra, o vacío si no.
     */
    private Optional<ItemCarro> findProducto(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }

    /**
     * Obtiene un listado de detalles de los productos en el carro.
     *
     * @return Una lista de objetos {@link DatalleProducto} que contienen la información de cada producto en el carro.
     */
    public List<DatalleProducto> getDetalleProductos() {
        List<DatalleProducto> productos = new ArrayList<>();
        for (ItemCarro itemCarro : this.getItems()) {
            String nom = itemCarro.getProducto().getNom();
            Integer cantidad = itemCarro.getCantidad();
            Integer pUnitario = itemCarro.getProducto().getPrecio();
            Integer totalproduc = this.getTotalProduc(Long.toString(itemCarro.getProducto().getId()));
            productos.add(new DatalleProducto(cantidad, nom, pUnitario, totalproduc));
        }
        return productos;
    }
}
