package com.tiendavinos.proyectofinal.enums;

public enum Varietal {

    MALBEC, CABERNET_SAUVIGNON, SAUVIGNON_BLANC, MERLOT, CHARDONNAY, TEMPRANILLO, CABERNET_FRANC;

    private final String varietal;
    
    private Varietal(String varietal) {
        this.varietal = varietal;
    }

    public String getVarietal() {
        return varietal;
    }

}
