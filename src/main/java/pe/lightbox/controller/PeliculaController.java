package pe.lightbox.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return peliculaService.obtenerPeliculaId(id).isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(peliculaService.obtenerPeliculaId(id));
    }

    @GetMapping("/genero")
    public ResponseEntity<?> obtenerPorIdGenero(@RequestParam("id") int id) {
        return peliculaService.obtenerPorIdGenero(id).isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(peliculaService.obtenerPorIdGenero(id));

    }

    @GetMapping("/search")
    public ResponseEntity<?> findByTitulo(@PathParam("titulo") String titulo) {
        return peliculaService.findByTitulo(titulo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filtrar")
    public ResponseEntity<?> findByCineAndFechaFinCartelera(
            @RequestParam("idCine") int idCine,
            @RequestParam("fechaFin") String fechaFinCartelera) {
        if (fechaFinCartelera == null || fechaFinCartelera.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return peliculaService.findByCineAndFechaFinCartelera(idCine, fechaFinCartelera).isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(peliculaService.findByCineAndFechaFinCartelera(idCine, fechaFinCartelera));
    }

    @GetMapping("/cartelera")
    public ResponseEntity<?> findByfechaInicioCarteleraAndFechaFinCartelera() {
        return peliculaService.findByfechaInicioCarteleraAndFechaFinCartelera().isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(peliculaService.findByfechaInicioCarteleraAndFechaFinCartelera());
    }
}
