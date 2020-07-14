package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Bodega;
import com.tiendavinos.proyectofinal.entidades.Foto;
import com.tiendavinos.proyectofinal.entidades.Vino;
import com.tiendavinos.proyectofinal.enums.Varietal;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.BodegaRepositorio;
import com.tiendavinos.proyectofinal.repositorios.VinoRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VinoServicio {

    @Autowired
    private BodegaRepositorio bodegaRepositorio;

    @Autowired
    private VinoRepositorio vinoRepositorio;

    @Autowired
    private FotoServicio fotoServicio;

    @Transactional
    public void agregarVino(String idBodega, String nombreVino, Varietal varietal, String anioCosecha, MultipartFile archivo) throws ErrorServicio {

        Bodega bodega = bodegaRepositorio.findById(idBodega).get();

        validar(nombreVino);

        Vino vino = new Vino();
        vino.setNombreVino(nombreVino);
        vino.setAnioCosecha(anioCosecha);

        Foto foto = fotoServicio.guardar(archivo);
        vino.setFoto(foto);

        vinoRepositorio.save(vino);

    }

    public void validar(String nombreVino) throws ErrorServicio {

        if (nombreVino == null || nombreVino.isEmpty()) {
            throw new ErrorServicio("El nombre del vino no puede ser nulo");
        }

    }

}
