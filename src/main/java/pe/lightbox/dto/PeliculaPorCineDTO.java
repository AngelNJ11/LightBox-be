package pe.lightbox.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PeliculaPorCineDTO {
    private int idPelicula;
    private String titulo;
    private LocalDate fechaInicioCartelera;
    private LocalDate fechaFinCartelera;
    private String cine;

} 