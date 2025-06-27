package pe.lightbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import pe.lightbox.model.Funcion;
import pe.lightbox.service.TicketPurchaseService;

@RestController
@RequestMapping("/api")
public class TicketPurchaseController {

    @Autowired
    private TicketPurchaseService ticketService;

    @PostMapping("/compra")
    public String realizarCompra(@RequestParam int idCliente,
                                @RequestParam int idFuncion,
                                  @RequestParam int piso,
                                  @RequestParam int idCine,
                                  @RequestParam int[] asientosSeleccionados,
                                  HttpSession session,
                                  Model model) {

        /*Persona usuario = (Persona) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/login";
        }

        int idCliente = usuario.getIdPersona();*/
        return ticketService.registrarCompra(idCliente, idFuncion, piso, idCine, asientosSeleccionados);
    }

    @GetMapping("/funcion")
    public ResponseEntity<?> obtenerFuncionPorPelicula(@RequestParam("pelicula") int pelicula) {
        List<Funcion> funciones = ticketService.getFuncionesPorPelicula(pelicula);
        return ResponseEntity.ok(funciones);
    }
}