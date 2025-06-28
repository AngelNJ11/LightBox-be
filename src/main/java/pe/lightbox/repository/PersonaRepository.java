package pe.lightbox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.lightbox.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>{

    Optional<Persona> findByCorreo(String username);
}
