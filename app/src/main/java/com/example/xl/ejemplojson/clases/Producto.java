package com.example.xl.ejemplojson.clases;

/**
 * Created by Angelo on 18/2/2017.
 */

public class Producto {
    private int id_producto;
    private String nombre;
    private double precio;
    private int stock;
    private int id_categoria;
    private String format;
    private String content;

    public Producto() {
    }

    public Producto(int id_producto, String nombre, double precio, int stock, int id_categoria, String format, String content) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_categoria = id_categoria;
        this.format = format;
        this.content = content;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
