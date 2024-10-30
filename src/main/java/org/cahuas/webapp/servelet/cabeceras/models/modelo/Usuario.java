package org.cahuas.webapp.servelet.cabeceras.models.modelo;

public class Usuario {
    int id;
    int dni;
    String user;
    String pass;
    String tipo;

    public Usuario(int id, int dni, String user, String pass, String tipo) {
        this.id = id;
        this.dni = dni;
        this.user = user;
        this.pass = pass;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
