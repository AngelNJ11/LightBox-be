package pe.lightbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.lightbox.service.SedeService;

@RestController
@RequestMapping("/api/sede")
public class SedeController {

    @Autowired
    private SedeService sedeService;

    @GetMapping("/lista")
    public ResponseEntity<?> obtenerSedes() {
        return sedeService.obtenerSedes().isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(sedeService.obtenerSedes());
    }   
}
