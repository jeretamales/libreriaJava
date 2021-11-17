package libreriaweb.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    @GetMapping("/guardar")
    public String guardarEditorial(){
        return "editorialGuardar";
    }
    
    @GetMapping("/modificar")
    public String modificarEditorial(){
        return "editorialModificar";
    }
    
    @GetMapping("/lista")
    public String listarEditorial(){
        return "editorialListar";
    }
    
    @GetMapping("/darbaja")
    public String darBajaEditorial(){
        return "editorialDarBaja";
    }
}
