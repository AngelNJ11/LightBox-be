package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cine_pelicula")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CinePelicula {

    @EmbeddedId
    private CinePeliculaId id;

    @ManyToOne
    @MapsId("idCine")
    @JoinColumn(name = "id_cine")
    private Cine cine;

    @ManyToOne
    @MapsId("idPelicula")
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;
}
