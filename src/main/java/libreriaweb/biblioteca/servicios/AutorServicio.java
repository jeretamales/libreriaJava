package libreriaweb.biblioteca.servicios;

import java.util.Optional;
import libreriaweb.biblioteca.entidades.Autor;
import libreriaweb.biblioteca.errores.ErrorServicio;
import libreriaweb.biblioteca.repositorios.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional(rollbackFor = {Exception.class})
    public void crearAutor(String nombre, boolean alta) throws ErrorServicio{
        validarAutor(nombre);
        
        //Creo el objeto
        Autor autor = new Autor();
        
        //Seteo sus atributos
        autor.setNombre(nombre);
        autor.setAlta(alta);
        
        //Lo cargo en la base de datos
        autorRepositorio.save(autor);
        
    }
    
    private void validarAutor(String nombre) throws ErrorServicio{
        if(nombre == null || nombre.isEmpty() ){
            throw new ErrorServicio("El nombre del autor no puede estar vacio");
        }
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void modificarAutor(String id, String nombre, boolean alta) throws ErrorServicio{
        validarAutor(nombre);
    
        Optional<Autor> respuesta = autorRepositorio.findById(id);
    
        if(respuesta.isPresent()){
            //creamos el objeto, en función del id
            Autor editorial = respuesta.get();
        
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
        
            autorRepositorio.save(editorial);
        }else{
            throw new ErrorServicio ("No se encontro el Autor solicitado");
        }
    }
    
    @Transactional(rollbackFor = {Exception.class})
    public void darDeBajaAutor(String id, String nombre, boolean alta) throws ErrorServicio{
        validarAutor(nombre);
    
        Optional<Autor> respuesta = autorRepositorio.findById(id);
    
        if(respuesta.isPresent()){
            //creamos el objeto, en función del id
            Autor editorial = respuesta.get();

            editorial.setAlta(alta);
        
            autorRepositorio.save(editorial);
        }else{
            throw new ErrorServicio ("No se encontro el Autor solicitado");
        }
    }
    
    
}
