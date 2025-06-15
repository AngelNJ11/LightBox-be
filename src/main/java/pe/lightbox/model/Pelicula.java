package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tb_pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private int idPelicula;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    private Sala sala;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_genero_pelicula", referencedColumnName = "id_genero_pelicula")
    private GeneroPelicula genero;

    @Column(name = "duracion")
    private int duracion;

    @Column(name = "fec_inicio_cartelera")
    private Date fechaInicioCartelera;

    @Column(name = "fec_fin_cartelera")
    private Date fechaFinCartelera;

    @Column(name = "ruta_imagen")
    private String rutaImagen;
}
