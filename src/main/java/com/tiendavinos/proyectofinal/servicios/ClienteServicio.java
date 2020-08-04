package com.tiendavinos.proyectofinal.servicios;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ClienteServicio implements UserDetailsService {

    @Autowired
    private NotificacionServicio notificacionServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;


//    @Transactional
//    public Cliente registrarCliente(String nombre, String apellido,Integer edad, String email, String telefono, String password, String password2) throws ErrorServicio {
//
//        validar(nombre, apellido, email, password, password2);
//
//        Cliente cliente = new Cliente(nombre, apellido, edad, email, telefono, password);
//        cliente.setAlta(new Date());
//        cliente.setPedidos(new ArrayList<>());
//        
//        clienteRepositorio.save(cliente);
//        
//        return cliente;
//        
//    }
    
    @Transactional
    public void registrarCliente(Cliente cliente){
        cliente.setAlta((new Date()));
        String encriptada = new BCryptPasswordEncoder().encode(cliente.getPassword());
        
        cliente.setPassword(encriptada);

             
       // notificacionServicio.enviar("Bienvenido a la vinoteca!", "Vinoteca", cliente.getEmail());
        
        clienteRepositorio.save(cliente);
        

    }

    @Transactional
    public Cliente modificarCliente(String idCliente, String nombre, String apellido, String email, String telefono, String password) throws ErrorServicio {

        Optional<Cliente> resultado = clienteRepositorio.findById(idCliente);

        if (resultado.isPresent()) {
            Cliente cliente = resultado.get();
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            clienteRepositorio.save(cliente);
            return cliente;
        } else {
            throw new ErrorServicio("No hemos podido encontrar el cliente solicitado");
        }

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

    private void validar(String nombre, String apellido, String email, String password, String password2) throws ErrorServicio {

        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("El nombre no puede ser nulo");
        }

        if (apellido == null || apellido.isEmpty()) {
            throw new ErrorServicio("El apellido no puede ser nulo");
        }

        if (email == null || email.isEmpty()) {
            throw new ErrorServicio("El mail no puede ser nulo");
        }

        if (password == null || password.isEmpty() || password.length() <= 6) {
            throw new ErrorServicio("La clave no puede ser nula ni tener menos de 6 caracteres");
        }

        if (!password.equals(password2)) {
            throw new ErrorServicio("Las claves no coinciden");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepositorio.findByEmail(email);
        if (cliente != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO");
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("clientesession", cliente);

            User user = new User(cliente.getEmail(), cliente.getPassword(), permisos);
            return user;

        } else {
            System.err.println("ACA ESTA EL ERROR");
            throw new UsernameNotFoundException("No se encontró un usuario con ese email");

        }
    }
}

