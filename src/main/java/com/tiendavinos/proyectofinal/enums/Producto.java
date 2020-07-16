package com.tiendavinos.proyectofinal.enums;

public enum Producto {
    VINO("Vino");

    private final String producto;

    private Producto(String producto) {
        this.producto = producto;
    }

    public String getProducto() {
        return producto;
    }

}
