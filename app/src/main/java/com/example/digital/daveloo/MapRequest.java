package com.example.digital.daveloo;

public class MapRequest {
    private String mapa_id;

    public String getMapa_id() {
        return mapa_id;
    }

    public void setMapa_id(String mapa_id) {
        this.mapa_id = mapa_id;
    }

    public String getMapa_nombre() {
        return mapa_nombre;
    }

    public void setMapa_nombre(String mapa_nombre) {
        this.mapa_nombre = mapa_nombre;
    }

    public Double getMapa_longitud() {
        return mapa_longitud;
    }

    public void setMapa_longitud(Double mapa_longitud) {
        this.mapa_longitud = mapa_longitud;
    }

    public Double getMapa_latitud() {
        return mapa_latitud;
    }

    public void setMapa_latitud(Double mapa_latitud) {
        this.mapa_latitud = mapa_latitud;
    }

    public int getMapa_zoom() {
        return mapa_zoom;
    }

    public void setMapa_zoom(int mapa_zoom) {
        this.mapa_zoom = mapa_zoom;
    }

    private String mapa_nombre;
    private Double mapa_longitud;
    private Double mapa_latitud;
    private int mapa_zoom;

    public MapRequest() {
    }

    @Override
    public String toString() {
        return "MapRequest{" +
                "mapa_id='" + mapa_id + '\'' +
                ", mapa_nombre='" + mapa_nombre + '\'' +
                ", mapa_longitud=" + mapa_longitud +
                ", mapa_latitud=" + mapa_latitud +
                ", mapa_zoom=" + mapa_zoom +
                '}';
    }
}
