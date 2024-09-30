package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class Producto {
    private int id;
    private String nom;
    private String cat;
    private int precio;
    private int  stock;

    private Proveedor id_proveedor;
    private String ruta_imagen;

    public Producto(int id, String nom, String cat, int precio,String proveedor, int stock, String ruta_imagen) {
        this.id = id;
        this.nom = nom;
        this.cat = cat;
        this.precio = precio;
        this.stock = stock;
        id_proveedor.setCo(proveedor);
        this.ruta_imagen = ruta_imagen;
    }

    public Producto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Proveedor getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Proveedor id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }
}
