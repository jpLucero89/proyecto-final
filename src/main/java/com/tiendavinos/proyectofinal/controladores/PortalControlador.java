package com.tiendavinos.proyectofinal.controladores;

import com.tiendavinos.proyectofinal.enums.Roles;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

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
    public String mostrarFormularioRegistro(ModelMap modelo) {
        return "registro";
    }
    
    @PostMapping("/registro")
    public String registro() throws ErrorServicio {

       
        return "registro-exito";        
    }
    @GetMapping("/prueba")
    public String prueba(){
        return "prueba";
    }
}
