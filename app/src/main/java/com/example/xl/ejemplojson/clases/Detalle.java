package com.example.xl.ejemplojson.clases;

/**
 * Created by Angelo on 18/2/2017.
 */

public class Detalle {
    private  int num_detalle;
    private  int num_factura;
    private  int id_producto;
    private  int cantidad;
    private  double precio;

    public Detalle() {
    }

    public Detalle(int num_detalle, int num_factura, int id_producto, int cantidad, double precio) {
        this.num_detalle = num_detalle;
        this.num_factura = num_factura;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getNum_detalle() {
        return num_detalle;
    }

    public void setNum_detalle(int num_detalle) {
        this.num_detalle = num_detalle;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
