package com.example.digital.daveloo;

public class Telefono {
    private int telefono_id;
    private int numero;
    private String servicio;

    public int getTelefono_id(){
        return telefono_id;
    }

    public void setTelefono_id(int telefono_id){
        this.telefono_id = telefono_id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

}
