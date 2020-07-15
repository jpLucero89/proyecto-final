
package com.tiendavinos.proyectofinal.enums;

public enum Tipo {
   
    TINTO("Tinto"),BLANCO("Blanco"),ROSADO("Rosado"),ESPUMANTE("Espumante");
    
    private final String nombreTipo;

    private Tipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }
    
    
}
