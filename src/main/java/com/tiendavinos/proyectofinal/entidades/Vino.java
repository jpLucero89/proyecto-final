package com.tiendavinos.proyectofinal.entidades;

import com.tiendavinos.proyectofinal.enums.Producto;
import com.tiendavinos.proyectofinal.enums.Tipo;
import com.tiendavinos.proyectofinal.enums.Varietal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vino extends AbstractEntity implements Serializable {

    private String marca;
    private String descripcion;
    private String cosecha;
    private Tipo tipo;
    private Varietal varietal;
    private Double precio;

    @ManyToOne
    private Proveedor proveedor;
    @ManyToMany(mappedBy = "vinos")
    private List<Pedido> vendidos;

    public Vino() {

    }

    public Vino(String marca, String descripcion, String cosecha, Tipo tipo, Varietal varietal, Double precio, Proveedor proveedor) {
        this.marca = marca;
        this.descripcion = descripcion;
        this.cosecha = cosecha;
        
        this.tipo = tipo;
        this.varietal = varietal;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCosecha() {
        return cosecha;
    }

    public void setCosecha(String cosecha) {
        this.cosecha = cosecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Varietal getVarietal() {
        return varietal;
    }

    public void setVarietal(Varietal varietal) {
        this.varietal = varietal;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Pedido> getVendidos() {
        return vendidos;
    }

    public void setVendidos(List<Pedido> vendidos) {
        this.vendidos = vendidos;
    }

    @Override
    public String getId() {
        return id;
    }

}
