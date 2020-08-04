package com.tiendavinos.proyectofinal.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Cliente extends AbstractEntity  {

    @NotNull
    @Size(min = 3, message = "Debe ingresar un nombre de al menos 3 caracteres")
    private String nombre;
    @NotNull
    @Size(min = 3, message = "Debe ingresar un apellido de al menos 3 caracteres")
    private String apellido;
    @Min(value = 18, message = "Debe ser mayor de 18 años para pode registrarse")
    private Integer edad;

    @NotNull(message = "Debe ingresar un e-mail válido")
    @Email(message = "E-mail no válido")
    private String email;
    private String telefono;
    @NotNull
    @Size(min = 6, message = "Debe ingresar mínimo 6 caracteres alfanuméricos")
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    @Temporal(TemporalType.TIMESTAMP)
    private Date baja;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, Integer edad, String email, String telefono, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password = password;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", email=" + email + ", telefono=" + telefono + ", password=" + password + ", alta=" + alta + ", baja=" + baja + ", pedidos=" + pedidos + '}';
    }
    
    

}
