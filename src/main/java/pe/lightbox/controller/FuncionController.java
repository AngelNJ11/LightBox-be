package pe.lightbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.lightbox.model.Funcion;
import pe.lightbox.service.FuncionService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @GetMapping("/funcion")
    public ResponseEntity<?> obtenerFuncionPorPelicula(@RequestParam("pelicula") int pelicula) {
        List<Funcion> funciones = funcionService.obtenerFuncionPorPelicula(pelicula);
        return ResponseEntity.ok(funciones);
    }

}
