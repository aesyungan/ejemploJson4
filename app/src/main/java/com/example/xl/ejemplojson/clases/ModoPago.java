package com.example.xl.ejemplojson.clases;

/**
 * Created by Angelo on 18/2/2017.
 */

public class ModoPago {
    private int num_pago;
    private String nombre;
    private String otros_detalles;

    public ModoPago() {
    }

    public ModoPago(String otros_detalles, int num_pago, String nombre) {
        this.otros_detalles = otros_detalles;
        this.num_pago = num_pago;
        this.nombre = nombre;
    }

    public int getNum_pago() {
        return num_pago;
    }

    public void setNum_pago(int num_pago) {
        this.num_pago = num_pago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOtros_detalles() {
        return otros_detalles;
    }

    public void setOtros_detalles(String otros_detalles) {
        this.otros_detalles = otros_detalles;
    }
}
