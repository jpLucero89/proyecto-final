package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Bodega;
import com.tiendavinos.proyectofinal.entidades.Foto;
import com.tiendavinos.proyectofinal.entidades.Producto;
import com.tiendavinos.proyectofinal.enums.Tipo;
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
    public void agregarProducto(String idBodega, String nombre,Tipo tipo, Varietal varietal, String anioCosecha,Double precio, MultipartFile archivo) throws ErrorServicio {

        Optional<Bodega> resultado = bodegaRepositorio.findById(idBodega);
        if (resultado.isPresent()) {
            Bodega bodega = resultado.get();
        
        
            validar(nombre,anioCosecha,precio);

            Producto producto = new Producto();
            producto.setBodega(bodega);
            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setVarietal(varietal);
            producto.setAnioCosecha(anioCosecha);
            producto.setPrecio(precio);
            
            Foto foto = fotoServicio.guardar(archivo);
            producto.setFoto(foto);

            productoRepositorio.save(producto);
    
        } else{
            throw new ErrorServicio("No existe una bodega con el identificador solicitado");
        }
    }
    
    @Transactional
    public void modificar(MultipartFile archivo,String idBodega, String idProducto,String nombre,Varietal varietal,String anioCosecha,Double precio ) throws ErrorServicio {
        
        validar(nombre,anioCosecha,precio);
        
        Optional<Producto> resultado = productoRepositorio.findById(idProducto);
        if (resultado.isPresent()) {
            Producto producto = resultado.get();
            
               
            producto.setNombre(nombre);
            producto.setVarietal(varietal);
            producto.setAnioCosecha(anioCosecha);
            producto.setPrecio(precio);

            Foto foto = fotoServicio.guardar(archivo);
            producto.setFoto(foto);

            productoRepositorio.save(producto);
            
        }else{
            throw new ErrorServicio("No existe un producto con el identificador solicitado");
        }
    }
    
    @Transactional    
    public void eliminar(String idBodega,String idProducto) throws ErrorServicio  {
        Optional<Producto> resultado = productoRepositorio.findById(idProducto);
        if (resultado.isPresent()) {
            
            Producto producto = resultado.get();
            productoRepositorio.delete(producto);
            
        }
    }

    private void validar(String nombre, String anioCosecha, Double precio) throws ErrorServicio {
    
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del vino no puede ser nulo");
        }
        
        if (anioCosecha == null || anioCosecha.isEmpty()) {
            throw new ErrorServicio("El año de creacion no puede ser nulo");
        }
         
        if (precio == null ) {
            throw new ErrorServicio("El precio no puede ser nulo");
        }
    
    }

}
