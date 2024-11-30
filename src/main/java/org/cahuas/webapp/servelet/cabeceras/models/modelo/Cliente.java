package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class Cliente {
    int id;
    String co;
    int tel;
    int id_usuario;
    String nombre;

    public Cliente(int id, String co, int tel, int id_usuario,String nombre) {
        this.id = id;
        this.co = co;
        this.tel = tel;
        this.nombre = nombre;
        this.id_usuario = id_usuario;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
