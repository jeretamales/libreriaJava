package libreriaweb.biblioteca.servicios;

import java.util.Optional;
import libreriaweb.biblioteca.entidades.Editorial;
import libreriaweb.biblioteca.errores.ErrorServicio;
import libreriaweb.biblioteca.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Transactional(rollbackFor = {Exception.class})
    public void crearEditorial(String nombre, boolean alta) throws ErrorServicio{
        validarEditorial(nombre);
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        editorial.setAlta(alta);
        
        editorialRepositorio.save(editorial);
    }
    
    private void validarEditorial(String nombre) throws ErrorServicio{
        if(nombre == null || nombre.isEmpty() ){
            throw new ErrorServicio("El nombre de la editorial no puede estar vacio");
        }
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void modificarEditorial(String id, String nombre, boolean alta) throws ErrorServicio{
        validarEditorial(nombre);
    
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
    
        if(respuesta.isPresent()){
            //creamos el objeto, en función del id
            Editorial editorial = respuesta.get();
        
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
        
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio ("No se encontro el usuario solicitado");
        }
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void darDeBajaEditorial(String id, String nombre, boolean alta) throws ErrorServicio{
        validarEditorial(nombre);
    
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
    
        if(respuesta.isPresent()){
            //creamos el objeto, en función del id
            Editorial editorial = respuesta.get();
        
            editorial.setAlta(alta);
        
            editorialRepositorio.save(editorial);
        }else{
            throw new ErrorServicio ("No se encontro la Editorial solicitada");
        }
    }
}
