package pe.lightbox.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompraDTO {
    private int idCliente;
    private int idFuncion;
    private int piso;
    private int idCine;
    private List<Integer> asientosSeleccionados;
}
