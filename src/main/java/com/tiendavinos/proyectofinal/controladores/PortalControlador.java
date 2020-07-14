package com.tiendavinos.proyectofinal.controladores;

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
        return "index";
    }

    @PreAuthorize("hasAnyRole( 'ROLE_USUARIO_REGISTRADO' )")
    @GetMapping("/inicio")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam String logout,
            ModelMap modelo) {
        if (error != null) {
            modelo.put("error", "Usuario o contraseña incorrecto");
        }
        if (logout != null) {
            modelo.put("logout", "Ha cerrado sesión correctamente");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(ModelMap modelo) {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String mail, @RequestParam String telefono, @RequestParam Integer edad, @RequestParam String clave1, @RequestParam String clave2) throws ErrorServicio {

        try {
            clienteServicio.registrar(nombre, apellido, mail, telefono, edad, clave1, clave2);
        } catch (ErrorServicio e) {
            modelo.put("error", e.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("mail", mail);
            modelo.put("telefono", telefono);
            modelo.put("edad", edad);
            modelo.put("clave1", clave1);
            modelo.put("clave2", clave2);
            return "registro";
        }
        modelo.put("titulo", "Bienvenido a Tienda de Vinos");
        modelo.put("descripcion", "Tu usuario fue registrado de manera satisfactoria");

        return "registro-exito";
    }

}
