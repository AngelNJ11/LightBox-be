package pe.lightbox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.lightbox.model.Funcion;
import pe.lightbox.repository.FuncionRepository;
import pe.lightbox.service.FuncionService;

import java.util.List;

@Service
public class FuncionServiceImpl implements FuncionService {

    @Autowired
    private FuncionRepository funcionRepository;

    @Override
    public List<Funcion> obtenerFuncionPorPelicula(int idPelicula) {
        return funcionRepository.findByPelicula(idPelicula);
    }
}
