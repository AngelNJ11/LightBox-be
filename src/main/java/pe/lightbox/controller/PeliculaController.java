package pe.lightbox.controller;

import jakarta.websocket.server.PathParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.lightbox.dto.PeliculaDTO;
import pe.lightbox.dto.PeliculaPorCineDTO;
import pe.lightbox.model.Pelicula;
import pe.lightbox.repository.PeliculaPorCineaRepository;
import pe.lightbox.model.CinePelicula;
import pe.lightbox.service.PeliculaService;
import pe.lightbox.service.SedeService;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private PeliculaPorCineaRepository peliculaPorCineaRepository;

    @GetMapping
    public ResponseEntity<?> obtenerTodasPeliculas() {
        return peliculaService.obtenerTodasPeliculas().isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(peliculaService.obtenerTodasPeliculas());
    }

    @GetMapping("/detalle")
    public ResponseEntity<?> obtenerPeliculaId(@RequestParam("id") int id) {
        return peliculaService.obtenerPeliculaId(id).isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(peliculaService.obtenerPeliculaId(id));
    }

    @GetMapping("/genero")
    public ResponseEntity<?> obtenerPorIdGenero(@RequestParam("id") int id) {
        return peliculaService.obtenerPorIdGenero(id).isEmpty() ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(peliculaService.obtenerPorIdGenero(id));

    }

    @GetMapping("/search")
    public ResponseEntity<?> findByTitulo(@RequestParam("titulo") String titulo) {
        List<PeliculaDTO> peliculas = peliculaService.findByTitulo(titulo);
        if (peliculas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron películas");
        }
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/cartelera")
    public ResponseEntity<?> findByfechaInicioCarteleraAndFechaFinCartelera() {
        List<Pelicula> cartelera = peliculaService.findPeliculasEnCartelera();

        if (cartelera.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cartelera);
    }

    @GetMapping("/estrenos")
    public ResponseEntity<?> findByFechaInicioCarteleraAfter() {
        List<Pelicula> estrenos = peliculaService.findEstrenos();

        if (estrenos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estrenos);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<?> buscarPeliculasPorSedeYFecha(
            @RequestParam(required = false) Integer idSede,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<CinePelicula> relaciones = peliculaPorCineaRepository.findAll();
        List<PeliculaPorCineDTO> resultado = relaciones.stream()
                .filter(rel -> {
                    Pelicula p = rel.getPelicula();
                    boolean sedeOk = (idSede == null) || rel.getCine().getIdCine() == idSede;
                    boolean fechaOk = (fecha == null) ||
                            (!p.getFechaInicioCartelera().isAfter(fecha) &&
                                    !p.getFechaFinCartelera().isBefore(fecha));
                    return sedeOk && fechaOk;
                })
                .map(rel -> new PeliculaPorCineDTO(
                        rel.getPelicula().getIdPelicula(),
                        rel.getPelicula().getTitulo(),
                        rel.getPelicula().getFechaInicioCartelera(),
                        rel.getPelicula().getFechaFinCartelera(),
                        rel.getCine().getSede()))
                .collect(Collectors.toList());

        return resultado.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(resultado);
    }
}
