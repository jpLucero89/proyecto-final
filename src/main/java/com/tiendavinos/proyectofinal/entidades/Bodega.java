package com.tiendavinos.proyectofinal.entidades;

import com.tiendavinos.proyectofinal.enums.Zona;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Bodega {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombreBodega;
    private String direccion;
    private String correoElectronico;
    @OneToMany
    private List<Vino> listadoDeVino;

    private Zona zona;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombreBodega
     */
    public String getNombreBodega() {
        return nombreBodega;
    }

    /**
     * @param nombreBodega the nombreBodega to set
     */
    public void setNombreBodega(String nombreBodega) {
        this.nombreBodega = nombreBodega;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the listadoDeVino
     */
    public List<Vino> getListadoDeVino() {
        return listadoDeVino;
    }

    /**
     * @param listadoDeVino the listadoDeVino to set
     */
    public void setListadoDeVino(List<Vino> listadoDeVino) {
        this.listadoDeVino = listadoDeVino;
    }

    /**
     * @return the zona
     */
    public Zona getZona() {
        return zona;
    }

    /**
     * @param zona the zona to set
     */
    public void setZona(Zona zona) {
        this.zona = zona;
    }

}
