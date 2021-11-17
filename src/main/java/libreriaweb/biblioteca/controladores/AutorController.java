package libreriaweb.biblioteca.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autor")
public class AutorController {
    @GetMapping("/guardar")
    public String guardarAutor(){
        return "autorGuardar";
    }
    
    @GetMapping("/modificar")
    public String modificarAutor(){
        return "autorModificar";
    }
    
    @GetMapping("/lista")
    public String listarAutor(){
        return "autorListar";
    }
    
    @GetMapping("/darbaja")
    public String darBajaAutor(){
        return "autorDarBaja";
    }
}
