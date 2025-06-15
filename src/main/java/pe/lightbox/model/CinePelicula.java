package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_cine_pelicula")
public class CinePelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cine_pelicula")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cine")
    private Cine cine;

    @ManyToOne
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;
}
