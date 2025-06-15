package pe.lightbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private Persona persona;

    private LocalDate FechaContratacion;
    private double Salario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_empleado", referencedColumnName = "id_tipo_empleado")
    private TipoEmpleado tipoEmpleado;

    private boolean EstadoLaboral;
}
