package pe.lightbox.serviceImpl;

import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.lightbox.model.GeneroPelicula;
import pe.lightbox.repository.GeneroRepository;
import pe.lightbox.service.GeneroPeliculaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GeneroServiceImpl implements GeneroPeliculaService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public ResponseEntity<Map<String, Object>> obtenerTodos() {
        Map<String, Object> response = new HashMap<>();
        List<GeneroPelicula> genero = generoRepository.findAll();

        if( genero.isEmpty() ) {
            response.put("mensaje", "No existen genero para esta consulta");
            response.put("status", HttpStatus.NOT_FOUND);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } else {
            response.put("mensaje", "Listado de Genero exitoso");
            response.put("status", HttpStatus.OK);
            response.put("genero", genero);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }


    @Override
    public ResponseEntity<Map<String, Object>> obtenerPorId(int id) {
        Map<String, Object> response = new HashMap<>();
        return generoRepository.findById(id)
                .map(genero -> {
                    response.put("mensaje", "Géneros encontrado");
                    response.put("genero", genero);
                    response.put("status", 200);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    response.put("mensaje", "No se encontró los géneros con ID: " + id);
                    response.put("status", 404);
                    return ResponseEntity.status(404).body(response);
                });
    }
}
