package com.tiendavinos.proyectofinal.servicios;


import com.tiendavinos.proyectofinal.entidades.Proveedor;
import com.tiendavinos.proyectofinal.entidades.Vino;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ProveedorRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServicio {

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Transactional
    public Proveedor cargarProveedor(String nombre, Date alta, String descripcion) throws ErrorServicio {

        Proveedor proveedor = new Proveedor();
        proveedor.setAlta(alta);
        proveedor.setNombre(nombre);
        proveedor.setDescripcion(descripcion);
        proveedorRepositorio.save(proveedor);

        return proveedor;
    }
    
    
     @Transactional
     public void cargarProveedor(Proveedor proveedor){
         proveedor.setAlta(new Date());
         proveedorRepositorio.save(proveedor);
     }

    @Transactional
    public Proveedor modificarProveedor(String idProveedor, String nombre, String descripcion) throws ErrorServicio {
        Proveedor proveedor = null;

        Optional<Proveedor> resultado = proveedorRepositorio.findById(idProveedor);

        if (resultado.isPresent()) {
            proveedor = resultado.get();
            proveedor.setNombre(nombre);
            proveedor.setDescripcion(descripcion);
            proveedorRepositorio.save(proveedor);
        } else {
            throw new ErrorServicio("No se encontró el proveedor especificado");
        }

        return proveedor;
    }

    @Transactional
    public void bajaProveedor(String idProveedor) throws ErrorServicio {
        Optional<Proveedor> resultado = proveedorRepositorio.findById(idProveedor);

        if (resultado.isPresent()) {
            Proveedor proveedor = resultado.get();
            proveedor.setBaja(new Date());
            proveedorRepositorio.save(proveedor);
        } else {
            throw new ErrorServicio("No se encontró el proveedor especificado");
        }
    }

    @Transactional
    public void altaProveedor(String idProveedor) throws ErrorServicio {
        Optional<Proveedor> resultado = proveedorRepositorio.findById(idProveedor);

        if (resultado.isPresent()) {
            Proveedor proveedor = resultado.get();
            proveedor.setBaja(null);
            proveedorRepositorio.save(proveedor);
        } else {
            throw new ErrorServicio("No se encontró el proveedor especificado");
        }
    }

    @Transactional
    public void eliminarProveedor(String idProveedor) throws ErrorServicio {
        Optional<Proveedor> resultado = proveedorRepositorio.findById(idProveedor);

        if (resultado.isPresent()) {
            Proveedor proveedor = resultado.get();
            proveedorRepositorio.delete(proveedor);
        } else {
            throw new ErrorServicio("No se encontró el proveedor especificado");
        }
    }

    @Transactional
    public void agregarVinoAProveedor(Proveedor proveedor, Vino vino) {
        proveedor.getVinos().add(vino);
        proveedorRepositorio.save(proveedor);
    }

    @Transactional
    public void sacarVinoAProveedor(Proveedor proveedor, Vino vino) {
        proveedor.getVinos().remove(vino);
        proveedorRepositorio.save(proveedor);
    }

    @Transactional
    public Proveedor buscarProveedorPorId(String idProveedor) {
        Proveedor proveedor = null;
        Optional<Proveedor> resultado = proveedorRepositorio.findById(idProveedor);

        if (resultado.isPresent()) {
            proveedor = resultado.get();
        } else {
            proveedor = null;
        }
        return proveedor;
    }

    @Transactional
    public void guardarCambios(Proveedor proveedor) {
        proveedorRepositorio.save(proveedor);
    }
    
    public Iterable<Proveedor> listarProveedores(){
        return proveedorRepositorio.findAll();
    }

}
