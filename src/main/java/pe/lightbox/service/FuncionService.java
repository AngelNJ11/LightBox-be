package pe.lightbox.service;

import pe.lightbox.model.Funcion;
import java.util.List;

public interface FuncionService {
    List<Funcion> obtenerFuncionPorPelicula(int idPelicula);

}
