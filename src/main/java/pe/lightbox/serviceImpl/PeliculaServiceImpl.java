package pe.lightbox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.lightbox.model.Pelicula;
import pe.lightbox.repository.PeliculaRepository;
import pe.lightbox.service.PeliculaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public ResponseEntity<Map<String, Object>> obtenerTodasPeliculas() {
        Map<String, Object> response = new HashMap<>();
        List<Pelicula> peliculas = peliculaRepository.findAll();

        if( peliculas.isEmpty() ) {
            response.put("mensaje", "No existen registros para esta consulta");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("mensaje", "Peliculas obtenidas correctamente\"");
            response.put("status", HttpStatus.OK);
            response.put("peliculas", peliculas);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

    }

    @Override
    public ResponseEntity<Map<String, Object>> obtenerPorIdGenero(int id) {
        Map<String, Object> response = new HashMap<>();
        List<Pelicula> peliculas = peliculaRepository.findByGenero(id);

        if (peliculas.isEmpty()) {
            response.put("mensaje", "No se encontraron películas con el género ID: " + id);
            response.put("status", 404);
            return ResponseEntity.status(404).body(response);
        } else {
            response.put("mensaje", "Películas por género encontradas");
            response.put("genero", peliculas);
            response.put("status", 200);
            return ResponseEntity.ok(response);
        }
    }

}
