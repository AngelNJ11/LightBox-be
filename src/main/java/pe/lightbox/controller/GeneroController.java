package pe.lightbox.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.lightbox.service.GeneroPeliculaService;

import java.util.Map;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {

    @Autowired
    private GeneroPeliculaService generoPeliculaService;

    @GetMapping("/lista")
    public ResponseEntity<Map<String, Object>> obtenerTodos() {
        return generoPeliculaService.obtenerTodos();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> obtenerPorId(@RequestParam("id") int id) {
        return generoPeliculaService.obtenerPorId(id);
    }
}
