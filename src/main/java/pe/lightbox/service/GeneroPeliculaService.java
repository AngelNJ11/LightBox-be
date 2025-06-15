package pe.lightbox.service;

import org.springframework.http.ResponseEntity;
import pe.lightbox.model.GeneroPelicula;
import java.util.Map;

public interface GeneroPeliculaService {

    public ResponseEntity<Map<String, Object>> obtenerTodos();
    public ResponseEntity<Map<String, Object>> obtenerPorId(int id);

}
