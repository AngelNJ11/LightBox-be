package pe.lightbox.service;

import pe.lightbox.dto.PeliculaDTO;
import pe.lightbox.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    Optional<Pelicula> obtenerPeliculaId(int id);
    List<Pelicula> obtenerTodasPeliculas();
    List<Pelicula> obtenerPorIdGenero(int id);
    List<PeliculaDTO> findByTitulo(String titulo);
    List<Pelicula> findByCineAndFechaFinCartelera(int idCine, String fechaFinCartelera);
    List<Pelicula> findPeliculasEnCartelera();
    List<Pelicula> findEstrenos();
}
