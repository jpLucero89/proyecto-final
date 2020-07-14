package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Bodega;
import com.tiendavinos.proyectofinal.entidades.Foto;
import com.tiendavinos.proyectofinal.entidades.Producto;
import com.tiendavinos.proyectofinal.enums.Varietal;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.BodegaRepositorio;
import com.tiendavinos.proyectofinal.repositorios.ProductoRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductoServicio {

    @Autowired
    private BodegaRepositorio bodegaRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void agregarProducto(String idBodega, String nombre, Varietal varietal, String anioCosecha, MultipartFile archivo) throws ErrorServicio {

        Optional<Bodega> resultado = bodegaRepositorio.findById(idBodega);
        if (resultado.isPresent()) {
            Bodega bodega = resultado.get();
        }
        
        validar(nombre);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setAnioCosecha(anioCosecha);

        Foto foto = fotoServicio.guardar(archivo);
        producto.setFoto(foto);

        productoRepositorio.save(producto);

    }

    public void validar(String nombre) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del vino no puede ser nulo");
        }

    }

}
