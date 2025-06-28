package pe.lightbox.controller;

import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.lightbox.dto.PeliculaDTO;
import pe.lightbox.model.Pelicula;
import pe.lightbox.service.PeliculaService;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

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

    @GetMapping("/filtrar")
    public ResponseEntity<?> findByCineAndFechaFinCartelera(
            @RequestParam("idCine") int idCine,
            @RequestParam("fechaFin") String fechaFinCartelera) {
        if (fechaFinCartelera == null || fechaFinCartelera.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return peliculaService.findByCineAndFechaFinCartelera(idCine, fechaFinCartelera).isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(peliculaService.findByCineAndFechaFinCartelera(idCine, fechaFinCartelera));
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
}
