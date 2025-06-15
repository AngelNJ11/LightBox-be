package pe.lightbox.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class CinePeliculaId implements Serializable {
    private int idCine;
    private int idPelicula;
}
