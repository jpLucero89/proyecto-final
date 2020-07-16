package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.entidades.Pedido;
import com.tiendavinos.proyectofinal.entidades.Vino;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import com.tiendavinos.proyectofinal.repositorios.PedidoRepositorio;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public Pedido generarPedido(String idCliente, List<Vino> vinos) throws ErrorServicio {
        Pedido pedido = null;
        Double precioTotal = null;
        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            pedido = new Pedido();
            pedido.setCliente(resultado.get());
            pedido.setFecha(new Date());
            pedido.setFechaModificacion(null);
            pedido.setVinos(vinos);

            precioTotal = calcularPrecioPedido(vinos);
            pedido.setPrecioTotal(precioTotal);

            pedidoRepositorio.save(pedido);
            //FALTA AGREGAR LOS PEDIDOS A CADA UNO DE LOS VINOS
        } else {
            throw new ErrorServicio("No hemos encontrado el cliente especificado");
        }
        return pedido;
    }

    @Transactional
    public Pedido modificarPedido(String idCliente, String idPedido, List<Vino> vinos) throws ErrorServicio {
        Pedido pedido = null;
        Double precioTotal = null;
        Optional<Pedido> resultado = pedidoRepositorio.findById(idPedido);
        Optional<Cliente> resultado2 = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent() && resultado2.isPresent()) {
            pedido = resultado.get();
            pedido.setCliente(resultado2.get());
            pedido.setFechaModificacion(new Date());
            pedido.setVinos(vinos);

            precioTotal = calcularPrecioPedido(vinos);
            pedido.setPrecioTotal(precioTotal);

            pedidoRepositorio.save(pedido);

        } else {
            throw new ErrorServicio("No hemos encontrado el cliente o el pedido especificado");
        }
        return pedido;
    }

    @Transactional
    public void eliminarPedido(String idPedido, String idCliente) throws ErrorServicio {
        //FALTARÁ VALIDAR ROL DE CLIENTE Y QUE LE CORRESPONDA EL PEDIDO

        Optional<Pedido> resultado = pedidoRepositorio.findById(idPedido);
        Optional<Cliente> resultado2 = clienteRepositorio.findById(idCliente);
        if (resultado.isPresent() && resultado2.isPresent()) {
            Cliente cliente = resultado2.get();
            Pedido pedido = resultado.get();

            pedido.setCliente(cliente);
            pedido.setProductos(productos);
            
        }else {
            throw new ErrorServicio("No se encontró el pedido especificado");
        }
        return null;

            cliente.getPedidos().remove(pedido);
            clienteRepositorio.save(cliente);
            pedidoRepositorio.delete(pedido);

        } else {
            throw new ErrorServicio("No se encontró el pedido especificado");
        }


    }

    private Double calcularPrecioPedido(List<Vino> vinos) {
        Double precio = null;
        for (Vino vino : vinos) {
            precio += vino.getPrecio();
        }
        return precio;
    }

}
