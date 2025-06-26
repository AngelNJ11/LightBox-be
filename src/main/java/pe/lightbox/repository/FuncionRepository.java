package pe.lightbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.lightbox.model.Funcion;

import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Integer> {

    @Query("SELECT f FROM Funcion f WHERE f.pelicula.idPelicula = :idPelicula")
    List<Funcion> findByPelicula(@Param("idPelicula") int idPelicula);


}
