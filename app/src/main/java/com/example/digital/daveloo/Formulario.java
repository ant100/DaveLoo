package com.example.digital.daveloo;

import java.io.File;

public class Formulario {
    //private int id;
    private int id_usuario;
    private String tipo;
    private String texto;
    private String imagen;

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/

    public enum Status {
        CREATED, ASSIGNED, CANCELED, COMPLETED
    }

    public Formulario(int id_usuario, String tipo, String texto,String imagen){
        this.id_usuario = id_usuario;
        this.tipo = tipo;
        this.texto = texto;
        this.imagen = imagen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id) {
        this.id_usuario = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Formulario [id_usuario=" + id_usuario + ", tipo=" + tipo + ", texto=" + texto + ", imagen=" + imagen +"]";
    }
}
