package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class DatalleProducto {
    private String nom;
    private Integer cantidad;
    private Integer pUnitario;
    private  Integer totalproduc;

    public DatalleProducto( Integer cantidad,String nom, Integer pUnitario, Integer totalproductoo) {
        this.nom = nom;
        this.cantidad = cantidad;
        this.pUnitario = pUnitario;
        this.totalproduc = totalproductoo;
    }
    public DatalleProducto() {
    }
    public String getNom() {
        return nom;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Integer getpUnitario() {
        return pUnitario;
    }

    public Integer getTotalproduc() {
        return totalproduc;
    }

    public DatalleProducto(String nom, Integer cantidad, Integer totalproductoo) {
        this.nom = nom;
        this.cantidad = cantidad;
        this.totalproduc = totalproductoo;
    }
}
