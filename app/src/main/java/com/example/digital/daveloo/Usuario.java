package com.example.digital.daveloo;

public class Usuario {
    private int id_usuario;
    private String usuario_nombre;
    private String usuario_password;
    private String usuario_email;

    public int getId() {
        return id_usuario;
    }

    public void setId(int id) {
        this.id_usuario = id;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String nombre) {
        this.usuario_nombre = nombre;
    }

    public String getUsuario_password() {
        return usuario_password;
    }

    public void setUsuario_password(String password) {
        this.usuario_password = password;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

    public void setUsuario_email(String email) {
        this.usuario_email = email;
    }
}