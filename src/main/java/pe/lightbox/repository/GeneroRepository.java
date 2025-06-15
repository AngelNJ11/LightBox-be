package pe.lightbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.lightbox.model.GeneroPelicula;

import java.util.List;

@Repository
public interface GeneroRepository extends JpaRepository<GeneroPelicula, Integer>{
}
