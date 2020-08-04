package com.tiendavinos.proyectofinal.entidades;

import com.tiendavinos.proyectofinal.enums.Varietal;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Vino extends AbstractEntity implements Serializable {

    @NotEmpty(message = "La marca no debe quedar vacia")
    private String marca;
    private String descripcion;
    @NotEmpty(message = "La cosecha no debe quedar vacia")
    private String cosecha;

    private Integer cantidad;
    @NotNull(message = "Debe seleccionar algun varietal")
    private Varietal varietal;
    @NotNull(message = "El precio no debe quedar vacio")
    private Double precio;

    @ManyToOne
    private Proveedor proveedor;
    @ManyToMany(mappedBy = "vinos", fetch = FetchType.EAGER)
    private List<Pedido> vendidos;

    @OneToOne
    private Foto foto;

    public Vino() {

    }

    public Vino(String marca, String descripcion, Integer cantidad, String cosecha, Varietal varietal, Double precio, Proveedor proveedor) {
        this.marca = marca;
        this.descripcion = descripcion;
        this.cosecha = cosecha;
        this.cantidad = cantidad;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
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

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    @Override
    public String getId() {
        return id;
    }

}
