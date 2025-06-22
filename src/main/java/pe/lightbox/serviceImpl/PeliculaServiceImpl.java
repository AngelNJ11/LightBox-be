package pe.lightbox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.lightbox.model.Pelicula;
import pe.lightbox.repository.PeliculaRepository;
import pe.lightbox.service.PeliculaService;

import java.time.LocalDate;
import java.util.*;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

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
    public Optional<Pelicula> findByTitulo(String titulo) {
        try {
            return peliculaRepository.findByTitulo(titulo);
        } catch (Exception e) {
            System.out.println("Error al buscar película por título: " + e.getMessage());
            return Optional.empty();
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
    public List<Pelicula> findByfechaInicioCarteleraAndFechaFinCartelera() {
        try {
            LocalDate fechaInicioCartelera = LocalDate.now().minusDays(30); // 30 días atras
            LocalDate fechaFinCartelera = LocalDate.now();
//            LocalDate fechaInicioCartelera = LocalDate.of(2024, 10, 1);
//            LocalDate fechaFinCartelera = LocalDate.of(2024, 11, 1);
            return peliculaRepository.findByfechaInicioCarteleraAndFechaFinCartelera(fechaInicioCartelera, fechaFinCartelera);
        } catch (Exception e) {
            System.out.println("Error al buscar películas por fechas de cartelera: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
