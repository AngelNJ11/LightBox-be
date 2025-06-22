package pe.lightbox.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.lightbox.model.Cine;
import pe.lightbox.repository.SedeRepository;
import pe.lightbox.service.SedeService;

import java.util.*;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Override
    public List<Cine> obtenerSedes() {
        return sedeRepository.findAll();
    }
}
