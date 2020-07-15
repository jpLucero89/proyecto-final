package com.tiendavinos.proyectofinal.enums;

public enum Varietal {

    MALBEC("Malbec"), CABERNET_SAUVIGNON("Cabernet_Sauvignon"), SAUVIGNON_BLANC("Sauvignon_Blanc"), MERLOT("Merlot"), CHARDONNAY("Chardonnay"), TEMPRANILLO("Tempranillo"), CABERNET_FRANC("Cabernet_Franc"),TORRONTÉS("Torrontés"),BLENT("Blent"),ALBARIÑO("Albariño"),PINOT_GRIGIO("Pinot_Grigio"),BONARDA("Bonarda"),SYRAH("Syrah"),BRUT("Brut"),BRUT_NATURE("Brut_Nature"),EXTRA_BRUT("Extra_Brut");

    private final String varietal;
    
    private Varietal(String varietal) {
        this.varietal = varietal;
    }

    public String getVarietal() {
        return varietal;
    }

}
