package com.tiendavinos.proyectofinal.servicios;


import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {

    //Crear, modificar, dardealta, dardebaja
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public Cliente registrarCliente(String nombre, String apellido, String email, String telefono, String password, String password2) {
        //FALTA VALIDACION
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, password);
        cliente.setAlta(new Date());
        cliente.setPedidos(new ArrayList<>());
        clienteRepositorio.save(cliente);
        return cliente;
    }

    @Transactional
    public Cliente modificarCliente(String idCliente, String nombre, String apellido, String email, String telefono, String password, String password2) throws ErrorServicio {
        Cliente cliente = null;
        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            cliente = resultado.get();

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            clienteRepositorio.save(cliente);

        } else {
            throw new ErrorServicio("No hemos podido encontrar el cliente solicitado");
        }
        return cliente;
    }

    @Transactional
    public void deshabilitarCliente(String idCliente) throws ErrorServicio {
        Cliente cliente = null;
        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            cliente = resultado.get();
            cliente.setBaja(new Date());
            clienteRepositorio.save(cliente);
        } else {
            throw new ErrorServicio("No hemos podido encontrar el cliente solicitado");
        }

    }

    @Transactional
    public void habilitarCliente(String idCliente) throws ErrorServicio {
        Cliente cliente = null;
        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            cliente = resultado.get();
            cliente.setBaja(null);
            clienteRepositorio.save(cliente);
        } else {
            throw new ErrorServicio("No hemos podido encontrar el cliente solicitado");
        }
    }

    @Transactional
    public void eliminarCliente(String idCliente) throws ErrorServicio {
        Cliente cliente = null;
        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            cliente = resultado.get();
            clienteRepositorio.delete(cliente);
        } else {
            throw new ErrorServicio("No hemos podido encontrar el cliente solicitado");
        }
    }
}
