package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int idSala;
    @ManyToOne
    @JoinColumn(name = "id_cine", referencedColumnName = "id_cine")
    private Cine cine;
    @Column(name = "piso_sala")
    private int piso;
    @Column(name = "enumeracion")
    private String enumeracion;
    @Column(name = "capacidad_sala")
    private int capacidadSala;
}
