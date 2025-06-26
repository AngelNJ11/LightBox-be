package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_funciones")
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuncion;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula")
    private Pelicula pelicula;

    @Column(name = "precio")
    private double precio;

    @Column(name = "inicio_funcion")
    private LocalDateTime inicioFuncion;

    @Column(name = "fin_funcion")
    private LocalDateTime finFuncion;
}
