package com.tiendavinos.proyectofinal.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Producto {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombreVino;
    @ManyToOne
    private Bodega bodega;
    private Integer capacidadBotella;
    @ManyToOne
    private Foto foto;
    private Double precio;
    private String anioCosecha;

    public Producto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreVino() {
        return nombreVino;
    }

    public void setNombreVino(String nombreVino) {
        this.nombreVino = nombreVino;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Integer getCapacidadBotella() {
        return capacidadBotella;
    }

    public void setCapacidadBotella(Integer capacidadBotella) {
        this.capacidadBotella = capacidadBotella;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getAnioCosecha() {
        return anioCosecha;
    }

    public void setAnioCosecha(String anioCosecha) {
        this.anioCosecha = anioCosecha;
    }

}
