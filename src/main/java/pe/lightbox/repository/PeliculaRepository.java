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
    Optional<Pelicula> findByTitulo(String titulo);

    @Query(
            value = "SELECT p.* FROM tb_pelicula p " +
                    "JOIN tb_sala s ON p.id_sala = s.id_sala " +
                    "JOIN tb_cine c ON s.id_cine = c.id_cine " +
                    "WHERE c.id_cine = :idCine AND p.fec_fin_cartelera = :fechaFin",
            nativeQuery = true
    )
    List<Pelicula> findByCineAndFechaFinCartelera(
            @Param("idCine") int idCine,
            @Param("fechaFin") String fechaFinCartelera
    );

    List<Pelicula> findByfechaInicioCarteleraAndFechaFinCartelera(
            LocalDate fechaInicioCartelera,
            LocalDate fechaFinCartelera
    );
}
