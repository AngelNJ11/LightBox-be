package pe.lightbox.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface PeliculaService {

    public ResponseEntity<Map<String, Object>> obtenerTodasPeliculas();
    public ResponseEntity<Map<String, Object>> obtenerPorIdGenero(int id);
}
