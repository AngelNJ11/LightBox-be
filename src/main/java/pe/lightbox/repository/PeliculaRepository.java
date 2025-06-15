package pe.lightbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.lightbox.model.Pelicula;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    @Query("SELECT p FROM Pelicula p WHERE p.genero.id = :idGenero")
    List<Pelicula> findByGenero( @Param("idGenero") int genero);
}
