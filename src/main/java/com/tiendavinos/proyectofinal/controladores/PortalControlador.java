package com.tiendavinos.proyectofinal.controladores;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.enums.Roles;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import com.tiendavinos.proyectofinal.servicios.ClienteServicio;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class PortalControlador {
    
    @Autowired
    private ClienteServicio clienteServicio;
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @GetMapping
    public String index() {
        return "inicio";
    }
    
    @PreAuthorize("hasAnyRole( 'ROLE_USUARIO' )")
    @GetMapping("/inicio")
    public String inicio() {
        return "logeado";
    }
    
    @GetMapping("/login")
    public String login(/*@RequestParam(required = false) String error, @RequestParam String logout,
            ModelMap modelo*/) {
//        if (error != null) {
//            modelo.put("error", "Usuario o contraseña incorrecto");
//        }
//        if (logout != null) {
//            modelo.put("logout", "Ha cerrado sesión correctamente");
//        }
        return "login";
    }
    
    @PostMapping("/login")
    public String procesarFormularioLogin(@RequestParam String usuario, @RequestParam String clave) {
        //FALTA VALIDACION USUARIO Y CONTRASEÑA Y REDIRECCION A UN LUGAR ADECUADO
        System.out.println(usuario);
        System.out.println(clave);
        return "redirect:";
    }
    
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute(new Cliente());
        return "registro";
    }
    
    @PostMapping("/registro")
    public String procesarFormularioRegistro(@ModelAttribute @Valid Cliente cliente, Errors error, Model model) throws ErrorServicio {
        
        if (error.hasErrors()) {
            model.addAttribute("error", error);
            return "registro";
        }
        clienteServicio.registrarCliente(cliente);
        return "exito";
    }
    
    @GetMapping("/prueba")
    public String prueba() {
        return "prueba";
    }
}
