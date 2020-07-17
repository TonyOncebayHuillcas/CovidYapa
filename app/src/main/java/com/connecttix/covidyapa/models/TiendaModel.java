package com.connecttix.covidyapa.models;

public class TiendaModel {
    private int id;
    private String nombre;
    private String latitud;
    private String longitud;
    private String dirección;
    private String ruc;
    private String correo;
    private String categoria_tienda;

    public TiendaModel(){}

    public TiendaModel(int id, String nombre, String latitud, String longitud, String dirección, String ruc, String correo, String categoria_tienda) {
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.dirección = dirección;
        this.ruc = ruc;
        this.correo = correo;
        this.categoria_tienda = categoria_tienda;
    }

    public TiendaModel(int id, String nombre, String ruc, String correo, String categoria_tienda) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.correo = correo;
        this.categoria_tienda = categoria_tienda;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCategoria_tienda() {
        return categoria_tienda;
    }

    public void setCategoria_tienda(String categoria_tienda) {
        this.categoria_tienda = categoria_tienda;
    }
}
