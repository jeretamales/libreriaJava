package libreriaweb.biblioteca.servicios;

import libreriaweb.biblioteca.entidades.Autor;
import libreriaweb.biblioteca.entidades.Editorial;
import libreriaweb.biblioteca.entidades.Libro;
import libreriaweb.biblioteca.errores.ErrorServicio;
import libreriaweb.biblioteca.repositorios.AutorRepositorio;
import libreriaweb.biblioteca.repositorios.EditorialRepositorio;
import libreriaweb.biblioteca.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    @Autowired
    private AutorRepositorio autorRepositorio;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer anio,Integer ejemplares, String IdAutor, String IdEditorial) throws ErrorServicio{
        validarLibro(titulo,anio,ejemplares,IdAutor, IdEditorial);
        
        Autor autor = autorRepositorio.findById(IdAutor).get();
        
        Editorial editorial = editorialRepositorio.findById(IdEditorial).get();
        
        Libro libro = new Libro();
        
        libro.setAlta(true);
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        
        libroRepositorio.save(libro);
    }
    
    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer anio,Integer ejemplares, String IdAutor, String IdEditorial, Integer ejemplaresPrestados, Integer ejemplaresRestantes) throws ErrorServicio{
        validarLibro(titulo,anio,ejemplares,IdAutor, IdEditorial);
        
        Autor autor = autorRepositorio.findById(IdAutor).get();
        
        Editorial editorial = editorialRepositorio.findById(IdEditorial).get();
        
        Libro libro = new Libro();
        
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        
        libroRepositorio.save(libro);
    }
    
    //@Transactional
    //public void eliminarLibro(String idLibro) throws ErrorServicio{
        
    //}
    
    private void validarLibro(String titulo, Integer anio, Integer ejemplares, String IdAutor, String IdEditorial) throws ErrorServicio{
        
        if(anio == null){
            throw new ErrorServicio("El año no puede estar vacio");
        }
        
        if(ejemplares == null || ejemplares<1){
            throw new ErrorServicio("El número de ejemplares no puede estar vacio ni ser menor que uno");
        }
        
        if(IdAutor == null || autorRepositorio.existsById(IdAutor)){
            throw new ErrorServicio("El nombre del autor no puede estar vacio y tiene que estar creado primero");
        }
        
        if(IdEditorial == null || editorialRepositorio.existsById(IdEditorial)){
            throw new ErrorServicio("El nombre de la editorial no puede estar vacio y tiene que estar creado primero");
        }
    } 
    
}
