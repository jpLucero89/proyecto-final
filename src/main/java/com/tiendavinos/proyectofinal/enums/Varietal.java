package com.tiendavinos.proyectofinal.enums;

public enum Varietal {

    MALBEC("Malbec"), CABERNET_SAUVIGNON("Cabernet Sauvignon"), CABERNET_FRANC("Cabernet Franc"),
    CHARDONNAY("Chardonnay"), TORRONTES("Torrontés"), ALBARINO("Albariño"), SAUVIGNON_BLANC("Sauvignon Blanc"),
    BRUT("Brut"), EXTRA_BRUT("Extra Brut"), BRUT_NATURE("Brut Nature");

    private final String nombre;

    private Varietal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;

    }

}
