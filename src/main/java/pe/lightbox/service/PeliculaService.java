package pe.lightbox.service;

import pe.lightbox.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> obtenerTodasPeliculas();
    List<Pelicula> obtenerPorIdGenero(int id);
    Optional<Pelicula> findByTitulo(String titulo);
    List<Pelicula> findByCineAndFechaFinCartelera(int idCine, String fechaFinCartelera);
    List<Pelicula> findByfechaInicioCarteleraAndFechaFinCartelera();
}
