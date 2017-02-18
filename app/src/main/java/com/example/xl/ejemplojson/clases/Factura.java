package com.example.xl.ejemplojson.clases;

/**
 * Created by Angelo on 18/2/2017.
 */

public class Factura {
    private  int num_factura;
    private  int id_cliente;
    private  String fecha;
    private  int num_pago;

    public Factura() {
    }

    public Factura(int num_factura, int id_cliente, String fecha, int num_pago) {
        this.num_factura = num_factura;
        this.id_cliente = id_cliente;
        this.fecha = fecha;
        this.num_pago = num_pago;
    }

    public int getNum_factura() {
        return num_factura;
    }

    public void setNum_factura(int num_factura) {
        this.num_factura = num_factura;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getNum_pago() {
        return num_pago;
    }

    public void setNum_pago(int num_pago) {
        this.num_pago = num_pago;
    }
}
