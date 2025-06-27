package pe.lightbox.service;

import java.util.List;

import pe.lightbox.model.AsientoPurchase;
import pe.lightbox.model.Cliente;
import pe.lightbox.model.Funcion;
import pe.lightbox.model.Sala;

public interface TicketPurchaseService {

    public List<Funcion> getFuncionesPorPelicula(int idPelicula);
    public List<AsientoPurchase> getAsientosPorFuncion(int idFuncion);
    public Sala getSalaPorId(int idSala);
    public Cliente getClientePorIdPersona(int idPersona);
    public String registrarCompra(int idCliente, int idFuncion, int piso, int idCine, int[] asientosSeleccionados);
    public Funcion getFuncionPorId(int idFuncion);

}
