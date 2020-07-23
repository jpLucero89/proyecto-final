package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Proveedor;
import com.tiendavinos.proyectofinal.entidades.Vino;
import com.tiendavinos.proyectofinal.enums.Tipo;
import com.tiendavinos.proyectofinal.enums.Varietal;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.VinoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VinoServicio {

    @Autowired
    private VinoRepositorio vinoRepositorio;

    @Autowired
    private ProveedorServicio proveedorServicio;

    @Transactional
    public Vino crearVino(String idProveedor, String marca, String descripcion, String cosecha, Tipo tipo, Varietal varietal, Double precio) throws ErrorServicio {
        Vino vino = null;
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(idProveedor);
        if (proveedor != null) {

            vino = new Vino();
            vino.setMarca(marca);
            vino.setDescripcion(descripcion);
            vino.setCosecha(cosecha);
            vino.setProveedor(proveedor);
            vino.setTipo(tipo);
            vino.setVarietal(varietal);
            vino.setPrecio(precio);

            proveedorServicio.agregarVinoAProveedor(proveedor, vino);
            vinoRepositorio.save(vino);
        } else {
            throw new ErrorServicio("Para crear un vino primero debe cargar el proveedor.");
        }
        return vino;
    }

    @Transactional
    public Vino modificarVino(String idVino, String idProveedor, String marca, String descripcion, String cosecha, Tipo tipo, Varietal varietal, Double precio) throws ErrorServicio {
        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(idProveedor);
        Vino vino = null;
        if (proveedor == null) {
            throw new ErrorServicio("No se ha encontrado el proveedor indicado");
        } else {
            Optional<Vino> respuesta = vinoRepositorio.findById(idVino);
            if (respuesta.isPresent()) {
                vino = respuesta.get();
                vino.setMarca(marca);
                vino.setDescripcion(descripcion);
                vino.setCosecha(cosecha);
                vino.setProveedor(proveedor);
                vino.setTipo(tipo);
                vino.setVarietal(varietal);
                vino.setPrecio(precio);
                proveedorServicio.agregarVinoAProveedor(proveedor, vino);
                vinoRepositorio.save(vino);
            }
        }
        return vino;
    }

    @Transactional
    public void eliminarVino(String idVino) throws ErrorServicio {
        Vino vino = null;
        Optional<Vino> respuesta = vinoRepositorio.findById(idVino);
        if (respuesta.isPresent()) {
            vino = respuesta.get();
            Proveedor proveedor = proveedorServicio.buscarProveedorPorId(vino.getProveedor().getId());
            if (proveedor != null) {
                proveedorServicio.sacarVinoAProveedor(proveedor, vino);
                proveedorServicio.guardarCambios(proveedor);
                vinoRepositorio.delete(vino);
            } else {
                throw new ErrorServicio("Hubo un error al identificar al proveedor del vino. Intente nuevamente");
            }
        } else {
            throw new ErrorServicio("No se ha encontrado el vino especificado");
        }
    }

    private void validarDatos(String marca, String cosecha, Tipo tipo, Varietal varietal, Double precio) throws ErrorServicio {

        if (marca == null || marca.isEmpty()) {
            throw new ErrorServicio("La marca no puede quedar vacía");
        }

        if (cosecha == null || cosecha.isEmpty()) {
            throw new ErrorServicio("La cosecha no puede quedar vacía");
        }

        if (varietal == null) {
            throw new ErrorServicio("Debe seleccionar un varietal");
        }

        if (precio == null || precio.isNaN()) {
            throw new ErrorServicio("El precio no puede quedar vacío y debe ser un número");
        }
    }

}
