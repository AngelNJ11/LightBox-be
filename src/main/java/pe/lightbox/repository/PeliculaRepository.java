package pe.lightbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.lightbox.model.Pelicula;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    @Query("SELECT p FROM Pelicula p WHERE p.genero.id = :idGenero")
    List<Pelicula> findByIdGenero( @Param("idGenero") int genero);

    @Query("SELECT p FROM Pelicula p WHERE p.idPelicula = :idPelicula")
    Optional<Pelicula> findByIdPelicula(int idPelicula);


    Optional<Pelicula> findByTitulo(String titulo);

    List<Pelicula> findByFechaInicioCarteleraLessThanEqualAndFechaFinCarteleraGreaterThanEqual(LocalDate inicio, LocalDate fin);

    List<Pelicula> findByFechaInicioCarteleraAfter(LocalDate hoy);

}
