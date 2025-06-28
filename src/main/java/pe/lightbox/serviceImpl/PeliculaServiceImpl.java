package pe.lightbox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.lightbox.dto.PeliculaDTO;
import pe.lightbox.model.Pelicula;
import pe.lightbox.repository.PeliculaRepository;
import pe.lightbox.service.PeliculaService;

import java.time.LocalDate;
import java.util.*;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Pelicula> obtenerPeliculaId(int id) {
        return peliculaRepository.findByIdPelicula(id);
    }

    @Override
    public List<Pelicula> obtenerTodasPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public List<Pelicula> obtenerPorIdGenero(int id) {
        try {
            List<Pelicula> peliculasPorGenero = peliculaRepository.findByIdGenero(id);
            return peliculasPorGenero;
        } catch (Exception e) {
            System.out.println("Error al buscar películas por ID de género: " + e.getMessage());
            return Collections.emptyList();
        }
    }


    @Override
    public List<PeliculaDTO> findByTitulo(String titulo) {
            try {
            return jdbcTemplate.query(
                "{CALL usp_get_PeliculasPorTitulo(?)}",
                new Object[]{titulo},
                (rs, rowNum) -> {
                    PeliculaDTO pelicula = new PeliculaDTO();
                    pelicula.setIdPelicula(rs.getInt("id_pelicula"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setRutaImagen(rs.getString("ruta_imagen"));
                    return pelicula;
                }
            );
        } catch (Exception e) {
            System.out.println("Error al buscar película por título: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Pelicula> findByCineAndFechaFinCartelera(int idCine, String fechaFinCartelera) {
        try{
            return peliculaRepository.findByCineAndFechaFinCartelera(idCine, fechaFinCartelera);
        } catch (Exception e) {
            System.out.println("Error al buscar películas por cine y fecha de fin de cartelera: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Pelicula> findPeliculasEnCartelera() {
        try {
            LocalDate fechaActual = LocalDate.now();
//            LocalDate fechaInicioCartelera = LocalDate.of(2024, 10, 1);
//            LocalDate fechaFinCartelera = LocalDate.of(2024, 11, 1);
            return peliculaRepository.findByFechaInicioCarteleraLessThanEqualAndFechaFinCarteleraGreaterThanEqual(fechaActual, fechaActual);
        } catch (Exception e) {
            System.out.println("Error al buscar películas por fechas de cartelera: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<Pelicula> findEstrenos() {
       List<Pelicula> estrenos = peliculaRepository.findByFechaInicioCarteleraAfter(LocalDate.now());
       return estrenos;
    }
}
