package pe.lightbox.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.lightbox.service.PeliculaService;

import java.util.Map;

@RestController
@RequestMapping("/api/pelicula")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public ResponseEntity<Map<String,Object>> obtenerTodasPeliculas() {
        return peliculaService.obtenerTodasPeliculas();
    }

    @GetMapping("/genero")
    public ResponseEntity<Map<String,Object>> obtenerPorIdGenero(@RequestParam("id") int id) {
        return peliculaService.obtenerPorIdGenero(id);
    }
}
