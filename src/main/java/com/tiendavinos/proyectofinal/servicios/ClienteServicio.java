/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ClienteServicio {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public void registrar(String nombre, String apellido, String mail, String clave,String clave2, Integer edad) throws ErrorServicio {
        
        validar(nombre,apellido,mail,clave,clave2,edad);
        
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setMail(mail);
        cliente.setEdad(edad);
        
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        cliente.setClave(encriptada);
        
        cliente.setAlta(new Date());
        
        clienteRepositorio.save(cliente);
        
    }
    
    private void validar(String nombre, String apellido, String mail, String clave, String clave2, Integer edad) throws ErrorServicio {
        
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre del usuario no puede ser nulo");
        }
        
        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido del usuario no puede ser nulo");
        }
        
        if (mail == null || mail.isEmpty()) {
            throw new ErrorServicio("El mail del usuario no puede ser nulo");
        }
        
        if (clave == null || clave.isEmpty() || clave.length() < 6  ) {
            throw new ErrorServicio("La clave del usuario no puede ser nula ni tener menos de 6 caracteres");
        }
        
        if (clave != clave2) {
            throw new ErrorServicio("Las claves deben coincidir");
        }
        
        if (edad<18) {
            throw new ErrorServicio("El usuario debe ser mayor de edad para registrarse");
        }
        
        
    }
    
    
}
