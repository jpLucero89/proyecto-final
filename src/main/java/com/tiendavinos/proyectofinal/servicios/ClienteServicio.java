/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.enums.Roles;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio implements UserDetailsService {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void registrar(String nombre, String apellido, String mail, String telefono, Integer edad, String clave, String clave2) throws ErrorServicio {

        validar(nombre, apellido, mail, telefono, edad, clave, clave2);

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMail(mail);
        cliente.setTelefono(telefono);
        cliente.setEdad(edad);
        cliente.setRol(Roles.USUARIO);

        String encriptada = new BCryptPasswordEncoder().encode(clave);
        cliente.setClave(encriptada);

        cliente.setAlta(new Date());

        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void modificar(String nombre, String apellido, String mail, String telefono, Integer edad, String clave, String clave2, String id) throws ErrorServicio {

        validar(nombre, apellido, mail, telefono, edad, clave, clave2);

        Optional<Cliente> resultado = clienteRepositorio.findById(id);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            cliente.setApellido(apellido);
            cliente.setNombre(nombre);
            cliente.setMail(mail);
            cliente.setTelefono(telefono);
            cliente.setEdad(edad);

            String encriptada = new BCryptPasswordEncoder().encode(clave);
            cliente.setClave(encriptada);

            clienteRepositorio.save(cliente);

        } else {
            throw new ErrorServicio("No se encontró el usuario solicitado");
        }
    }

    @Transactional
    public void deshabilitar(String id) throws ErrorServicio {

        Optional<Cliente> resultado = clienteRepositorio.findById(id);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            cliente.setBaja(new Date());

            clienteRepositorio.save(cliente);

        } else {
            throw new Error("No se encontró el usuario solicitado");
        }
    }

    @Transactional
    public void habilitar(String id) throws ErrorServicio {

        Optional<Cliente> resultado = clienteRepositorio.findById(id);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            cliente.setBaja(null);

            clienteRepositorio.save(cliente);

        } else {
            throw new ErrorServicio("No se encontro el usuario solicitado");
        }
    }

    private void validar(String nombre, String apellido, String mail, String telefono, Integer edad, String clave, String clave2) throws ErrorServicio {
        //(nombre, apellido, mail, telefono, edad, clave, clave2)
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }

        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede ser nulo");
        }

        if (clave == null || clave.isEmpty() || clave.length() < 6) {
            throw new ErrorServicio("La clave del usuario no puede ser nula ni tener menos de 6 caracteres");
        }

        if (telefono == null || telefono.isEmpty()) {
            throw new ErrorServicio("El teléfono del usuario no puede quedar vacío");
        }

        if (clave != clave2) {
            throw new ErrorServicio("Las claves deben coincidir");
        }

        if (edad < 18) {
            throw new ErrorServicio("El usuario debe ser mayor de edad para registrarse");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Cliente cliente = clienteRepositorio.buscarPorMail(mail);
        if (cliente != null) {
            //FALTAN PERMISOS CLIENTES Y ADMIN

            // return user;            
        } else {
            return null;
        }
        return null;
    }

}
