package pe.lightbox.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinePeliculaId implements Serializable {
    @Column(name = "id_cine")
    private Integer idCine;

    @Column(name = "id_pelicula")
    private Integer idPelicula;
}
