package pe.lightbox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import pe.lightbox.dto.CompraDTO;
import pe.lightbox.model.AsientoPurchase;
import pe.lightbox.model.Funcion;
import pe.lightbox.service.TicketPurchaseService;

@RestController
@RequestMapping("/api")
public class TicketPurchaseController {

    @Autowired
    private TicketPurchaseService ticketService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/compra")
    public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO request) {
        try {
            String resultado = ticketService.registrarCompra(
                    request.getIdCliente(),
                    request.getIdFuncion(),
                    request.getPiso(),
                    request.getIdCine(),
                    request.getAsientosSeleccionados().stream().mapToInt(i -> i).toArray()
            );
            if (resultado != null && resultado.toLowerCase().contains("error")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                        "error", resultado
                ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "mensaje", "Compra realizada con éxito"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/funcion")
    public ResponseEntity<?> obtenerFuncionPorPelicula(@RequestParam("pelicula") int pelicula) {
        try {
            List<Funcion> funciones = ticketService.getFuncionesPorPelicula(pelicula);
            if (funciones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron funciones para la película con ID: " + pelicula);
            }
            return ResponseEntity.ok(funciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener funciones: " + e.getMessage());
        }
    }

    @GetMapping("/asientos")
    public ResponseEntity<?> obtenerAsientosPorFuncion(@RequestParam("funcion") int funcion) {
        try {
            List<AsientoPurchase> asientos = ticketService.getAsientosPorFuncion(funcion);
            if (asientos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontraron asientos para la función con ID: " + funcion);
            }
            return ResponseEntity.ok(asientos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener asientos: " + e.getMessage());
        }
    }

}