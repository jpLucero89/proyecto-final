package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.entidades.Pedido;
import com.tiendavinos.proyectofinal.entidades.Producto;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import com.tiendavinos.proyectofinal.repositorios.PedidoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public void crearPedido(String idCliente, List<Producto> productos, String direccionEnvio, String medioDePago) throws ErrorServicio {

        Optional<Cliente> respuesta = clienteRepositorio.findById(idCliente);

        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            Pedido pedido = new Pedido();
            pedido.setFecha(new Date());
            pedido.setCliente(cliente);
            pedido.setProductos(productos);
            pedido.setDireccionEnvio(direccionEnvio);

            Double precioTotal = calcularPrecioTotalPedido(productos);
            pedido.setPrecioTotal(precioTotal);

            pedido.setMedioDePago(medioDePago);

            pedidoRepositorio.save(pedido);
        } else {
            throw new ErrorServicio("No se encontró el cliente especificado");
        }
    }

    public Pedido modificarPedido(String idPedido, String idCliente, List<Producto> productos, String direccionEnvio, String medioDePago) {

        Optional<Pedido> resultado = pedidoRepositorio.findById(idPedido);
        
        Cliente cliente = clienteRepositorio.findById(idCliente).get();

        if (resultado.isPresent()) {
            Pedido pedido = resultado.get();
            pedido.setCliente(cliente);
            pedido.setProductos(productos);
            
        }else {
            throw new ErrorServicio("No se encontró el pedido especificado");
        }
    }

    private Double calcularPrecioTotalPedido(List<Producto> productos) {
        Double precio = null;
        for (Producto producto : productos) {
            precio += producto.getPrecio();
        }
        return precio;
    }
}
