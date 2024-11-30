package org.cahuas.webapp.servelet.cabeceras.models.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;
    public Carro() {
        this.items = new ArrayList<>();
    }

    public void addItemCarro(ItemCarro itemCarro) {
        Optional<ItemCarro> existingItem = items.stream()
                .filter(i -> i.getProducto().getId().equals(itemCarro.getProducto().getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            ItemCarro existing = existingItem.get();
            existing.setCantidad(existing.getCantidad() + itemCarro.getCantidad());
        } else {
            items.add(itemCarro);
        }
    }
    public List<ItemCarro> getItems() {
        return items;
    }

    public int cantidad(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .mapToInt(ItemCarro::getCantidad)
                .findFirst()
                .orElse(0);
    }

    public int getTotalProduc(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .mapToInt(ItemCarro::getImporte)
                .findFirst()
                .orElse(0);

    }

    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getImporte).sum();
    }

    public void removeProductos(List<String> productoIds) {
        if (productoIds != null) {
            productoIds.forEach(this::removeProducto);
            // que es lo mismo a:
            // productoIds.forEach(productoId -> removeProducto(productoId));
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return  items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProducto().getId())))
                .findAny();
    }

    public List<DatalleProducto> getDetalleProductos() {
        List<DatalleProducto> productos = new ArrayList<>();
        for (ItemCarro itemCarro : this.getItems()) {
            String nom= itemCarro.getProducto().getNom();
            Integer cantidad= itemCarro.getCantidad();
            Integer pUnitario = itemCarro.getProducto().getPrecio();
            Integer totalproduc = this.getTotalProduc(Long.toString(itemCarro.getProducto().getId()));
            productos.add(new DatalleProducto( cantidad,nom, pUnitario, totalproduc));
        }
        return productos;
    }
}
