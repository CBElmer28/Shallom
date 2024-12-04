package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class Proveedor {
    int id;
    int ruc;
    String direc;
    int tel;
    String co;

    public Proveedor(int id, int ruc, String direc, int tel, String co) {
        this.id = id;
        this.ruc = ruc;
        this.direc = direc;
        this.tel = tel;
        this.co = co;
    }

    public Proveedor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getDirec() {
        return direc;
    }

    public void setDirec(String direc) {
        this.direc = direc;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }
}
