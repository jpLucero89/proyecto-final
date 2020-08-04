package com.tiendavinos.proyectofinal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
public class Proveedor extends AbstractEntity implements Serializable {
    
    @NotEmpty(message="No se puede guardar un proveedor sin nombre")
    private String nombre;
    private String descripcion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;

    @OneToMany(mappedBy = "proveedor")
    private List<Vino> vinos;

    public Proveedor() {
    }

    public Proveedor(String nombre, String descripcion, Date alta, Date baja, List<Vino> vinos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.alta = alta;
        this.baja = baja;
        this.vinos = vinos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getAlta() {
        return alta;
    }

    public void setAlta(Date alta) {
        this.alta = alta;
    }

    public Date getBaja() {
        return baja;
    }

    public void setBaja(Date baja) {
        this.baja = baja;
    }

    public List<Vino> getVinos() {
        return vinos;
    }

    public void setVinos(List<Vino> vinos) {
        this.vinos = vinos;
    }

    @Override
    public String getId() {
        return id;
    }

}
