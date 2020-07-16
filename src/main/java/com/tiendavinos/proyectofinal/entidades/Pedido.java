package com.tiendavinos.proyectofinal.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido extends AbstractEntity implements Serializable {

    @ManyToOne
    private Cliente cliente;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;

    @ManyToMany
    private List<Vino> vinos;

    private Double precioTotal;

    public Pedido() {
    }

    public Pedido(Cliente cliente, Date fecha, Date fechaModificacion, List<Vino> vinos, Double precioTotal) {
        this.cliente = cliente;
        this.fecha = fecha;
        this.fechaModificacion = fechaModificacion;
        this.vinos = vinos;
        this.precioTotal = precioTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Vino> getVinos() {
        return vinos;
    }

    public void setVinos(List<Vino> vinos) {
        this.vinos = vinos;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    @Override
    public String getId() {
        return id;
    }

}
