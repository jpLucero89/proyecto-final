package com.tiendavinos.proyectofinal.controladores;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.enums.Roles;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import com.tiendavinos.proyectofinal.servicios.ClienteServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    
    @GetMapping
    public String index() {
        return "inicio";
    }

//    @PreAuthorize("hasAnyRole( 'ROLE_USUARIO' )")
//    @GetMapping("/inicio")
//    public String inicio() {
//        return "logeado";
//    }

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
        return "redirect:";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute(new Cliente());
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute @Valid Cliente cliente, Errors error, Model model) throws ErrorServicio {

        if (error.hasErrors()) {
            model.addAttribute("errores", error);
            return "registro";
        }
        clienteServicio.registrarCliente(cliente);
        return "exito";
    }
    
    /////////////// esto de abajo los agregre yo VIKTOR para poder ver esas templates ///////////////
                    
    @GetMapping("/inicio")
    public String mostrarInicio(ModelMap modelo) {
        return "inicio";
    }
    @GetMapping("/blancos")
    public String mostrarVinosBlancos(ModelMap modelo) {
        return "blancos";
    }
    
    @GetMapping("/tintos")
    public String mostrarVinosTintos(ModelMap modelo) {
        return "tintos";
    }
    
    @GetMapping("/rosados")
    public String mostrarVinosRosados(ModelMap modelo) {
        return "rosados";
    }
    
    @GetMapping("/espumantes")
    public String mostrarVinosEspumantes(ModelMap modelo) {
        return "espumantes";
    }
    
    @GetMapping("/listaBodegas")
    public String mostrarListaBodegas(ModelMap modelo) {
        return "listaBodegas";
    }
    
    @GetMapping("/bodega")
    public String bodega(ModelMap modelo){
        return "bodega";
    }
    
    @GetMapping("/nostros")
    public String mostrarNosotros(ModelMap modelo) {
        return "nosotros";
    }
    
    @GetMapping("/carrito")
    public String mostrarCarrito(ModelMap modelo) {
        return "carrito";
    }
    
    @GetMapping("/falla")
    public String informarFalla(ModelMap modelo) {
        return "falla";
    }
    
    @GetMapping("/cargaProducto")
    public String cargarProducto(ModelMap modelo) {
        return "cargaProducto";
    }

    //////////////////////////////////////////////////////////////////
    
      
}
