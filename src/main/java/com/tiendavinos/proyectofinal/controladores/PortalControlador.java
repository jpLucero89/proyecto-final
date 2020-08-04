package com.tiendavinos.proyectofinal.controladores;

import com.tiendavinos.proyectofinal.entidades.Cliente;
import com.tiendavinos.proyectofinal.entidades.Proveedor;
import com.tiendavinos.proyectofinal.entidades.Vino;
import com.tiendavinos.proyectofinal.enums.Varietal;
import com.tiendavinos.proyectofinal.errores.ErrorServicio;
import com.tiendavinos.proyectofinal.repositorios.ClienteRepositorio;
import com.tiendavinos.proyectofinal.servicios.ClienteServicio;
import com.tiendavinos.proyectofinal.servicios.ProveedorServicio;
import com.tiendavinos.proyectofinal.servicios.VinoServicio;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class PortalControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private VinoServicio vinoServicio;

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/inicio")//pagina hecha para comprobar login correcto
    public String loginSuccess() {
        return "inicio";
    }
//    @PreAuthorize("hasAnyRole( 'ROLE_USUARIO' )")

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model modelo) {
        modelo.addAttribute(new Cliente());
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(@ModelAttribute @Valid Cliente cliente, Errors error, Model model) throws ErrorServicio {
        Cliente buscado = null;
        buscado = clienteRepositorio.findByEmail(cliente.getEmail());
        if (buscado != null) {
            System.err.println("YA EXISTE");
        }
        if (error.hasErrors()) {
            model.addAttribute("errores", error);
            return "registro";
        }
        clienteServicio.registrarCliente(cliente);
        return "exito";
    }

    /////////////// esto de abajo los agregre yo VIKTOR para poder ver esas templates ///////////////
//    @GetMapping("/inicio")
//    public String mostrarInicio(ModelMap modelo) {
//        return "inicio";
//    }
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
    public String bodega(ModelMap modelo) {
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

    @GetMapping("/cargarProducto")
    public String cargarProducto(Model model) {
        model.addAttribute("proveedores", proveedorServicio.listarProveedores());
        model.addAttribute("varietales", Varietal.values());
        model.addAttribute(new Vino());
        return "cargarProducto";
    }

    @PostMapping("/cargarProducto")
    public String cargarProducto(@ModelAttribute @Valid Vino vino, Errors error, Model model, @RequestParam MultipartFile archivo) throws ErrorServicio{
        if (error.hasErrors()) {
            model.addAttribute("errores", error);
            return "cargarProducto";
        }
        vinoServicio.cargarVino(vino, archivo);
        return "cargaProducto";
    }

    @GetMapping("/cargarProveedor")
    public String cargarProveedor(Model model) {
        
        model.addAttribute(new Proveedor());
        return "cargarProveedor";
    }

    @PostMapping("/cargarProveedor")
    public String cargarProveedor(@ModelAttribute @Valid Proveedor proveedor, Errors error, Model model) {
        if (error.hasErrors()) {
            model.addAttribute("errores", error);
            return "cargarProveedor";
        }

        proveedorServicio.cargarProveedor(proveedor);
        return "cargarProveedor";
    }
    
    
    //////////////////////////////////////////////////////////////////
}
