package pe.lightbox.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FiltroPeliculaDTO {
    private Integer idSede;
    private LocalDate fecha;
}