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

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public Cliente registrarCliente(String nombre, String apellido, String email, String telefono, String password, String password2) throws ErrorServicio {

        validarDatos(nombre, apellido, email, telefono, password, password2);
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
            cliente.setPassword(password);
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

    private void validarDatos(String nombre, String apellido, String email, String telefono, String password, String password2) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo");
        }

        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El email no puede ser nulo");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El telefono no puede ser nulo");
        }

        if (password == null || password.isEmpty() || password.length() <= 6) {
            throw new ErrorServicio("La contraseña no puede ser nula y dene tener al menos 6 caracteres");
        }

        if (!password.equals(password2)) {
            throw new ErrorServicio("Las claves no coinciden");
        }
    }
}
