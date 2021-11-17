package libreriaweb.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroController {
    @GetMapping("/guardar")
    public String guardarLibro(){
        return "libroGuardar";
    }
    
    @GetMapping("/modificar")
    public String modificarLibro(){
        return "libroModificar";
    }
    
    @GetMapping("/lista")
    public String listarLibro(){
        return "libroListar";
    }
    
    @GetMapping("/darbaja")
    public String darBajaLibro(){
        return "libroDarBaja";
    }
}
