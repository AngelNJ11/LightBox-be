package pe.lightbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.lightbox.model.CinePelicula;
import pe.lightbox.model.CinePeliculaId;;

@Repository
public interface PeliculaPorCineaRepository extends JpaRepository<CinePelicula, CinePeliculaId>{

}
