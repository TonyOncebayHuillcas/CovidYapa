package com.connecttix.covidyapa.models;

public class ProductModel {
    private int id;
    private int id_tienda;
    private String nombre;
    private String precio;
    private String descripcion;
    private int stock;
    private String categoria_producto; //FK

    public ProductModel(){}

    public ProductModel(int id, int id_tienda, String nombre, String descripcion, int stock, String categoria_producto) {
        this.id = id;
        this.id_tienda = id_tienda;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.categoria_producto = categoria_producto;
    }

    public ProductModel(int id, int id_tienda, String nombre, String precio, String descripcion, int stock, String categoria_producto) {
        this.id = id;
        this.id_tienda = id_tienda;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.categoria_producto = categoria_producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria_producto() {
        return categoria_producto;
    }

    public void setCategoria_producto(String categoria_producto) {
        this.categoria_producto = categoria_producto;
    }
}
