package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_genero_pelicula")
public class GeneroPelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero_pelicula")
    private int idGeneroPelicula;
    private String Nombre;
}
