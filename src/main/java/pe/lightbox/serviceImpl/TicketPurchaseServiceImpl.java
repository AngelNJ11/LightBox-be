package pe.lightbox.serviceImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.lightbox.model.AsientoPurchase;
import pe.lightbox.model.Cine;
import pe.lightbox.model.Cliente;
import pe.lightbox.model.Funcion;
import pe.lightbox.model.Pelicula;
import pe.lightbox.model.Persona;
import pe.lightbox.model.Sala;
import pe.lightbox.service.TicketPurchaseService;

@Service
public class TicketPurchaseServiceImpl implements TicketPurchaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Funcion> getFuncionesPorPelicula(int idPelicula) {
        return jdbcTemplate.execute(connection -> {
            CallableStatement cs = connection.prepareCall("{CALL usp_FuncionesPorPelicula(?)}");
            cs.setInt(1, idPelicula);
            return cs;
        }, (CallableStatementCallback<List<Funcion>>) cs -> {
            List<Funcion> funciones = new ArrayList<>();
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Funcion f = new Funcion();
                f.setIdFuncion(rs.getInt("id_funcion"));
                f.setInicioFuncion(rs.getTimestamp("inicio_funcion").toLocalDateTime());
                f.setFinFuncion(rs.getTimestamp("fin_funcion").toLocalDateTime());
                f.setPrecio(rs.getDouble("precio"));

                Sala s = new Sala();
                s.setIdSala(rs.getInt("id_sala"));
                s.setEnumeracion(rs.getString("enumeracion"));
                s.setPiso(rs.getInt("piso_sala"));
                s.setCapacidadSala(rs.getInt("capacidad_sala"));

                Cine c = new Cine();
                c.setIdCine(rs.getInt("id_cine"));
                s.setCine(c);

                f.setSala(s);

                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getInt("id_pelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setFechaInicioCartelera(rs.getDate("fec_inicio_cartelera").toLocalDate());
                p.setFechaFinCartelera(rs.getDate("fec_fin_cartelera").toLocalDate());
                p.setRutaImagen(rs.getString("ruta_imagen"));

                f.setPelicula(p);

                funciones.add(f);
            }
            return funciones;
        });
    }

    @Override
    public List<AsientoPurchase> getAsientosPorFuncion(int idFuncion) {
        return jdbcTemplate.query("{CALL usp_ObtenerAsientosPorFuncion(?)}",
                new Object[] { idFuncion }, (rs, rowNum) -> {
                    AsientoPurchase a = new AsientoPurchase();
                    a.setIdAsiento(rs.getInt("id_asiento"));
                    a.setIdSala(rs.getInt("id_sala"));
                    a.setDisponibilidad(rs.getBoolean("disponibilidad"));
                    return a;
                });
    }

    @Override
    public Sala getSalaPorId(int idSala) {
        return jdbcTemplate.queryForObject("{CALL usp_GetSalaById(?)}",
                new Object[] { idSala }, (rs, rowNum) -> {
                    Cine c = new Cine();
                    c.setIdCine(rs.getInt("id_cine"));

                    Sala s = new Sala();
                    s.setIdSala(rs.getInt("id_sala"));
                    s.setCine(c);
                    s.setPiso(rs.getInt("piso_sala"));
                    s.setEnumeracion(rs.getString("enumeracion"));
                    s.setCapacidadSala(rs.getInt("capacidad_sala"));
                    return s;
                });
    }

    @Override
    public Cliente getClientePorIdPersona(int idPersona) {
        return jdbcTemplate.queryForObject("{CALL usp_GetClienteByIdPersona(?)}",
                new Object[] { idPersona }, (rs, rowNum) -> {
                    Persona p = new Persona();
                    p.setIdPersona(rs.getInt("id_persona"));

                    Cliente c = new Cliente();
                    c.setIdCliente(rs.getInt("id_cliente"));
                    c.setPersona(p);
                    c.setSaldo(rs.getDouble("saldo"));
                    return c;
                });
    }

    @Override
    public Funcion getFuncionPorId(int idFuncion) {
        return jdbcTemplate.queryForObject("{CALL usp_GetFuncionById(?)}",
                new Object[] { idFuncion }, (rs, rowNum) -> {
                    Sala s = new Sala();
                    s.setIdSala(rs.getInt("id_sala"));

                    Pelicula p = new Pelicula();
                    p.setIdPelicula(rs.getInt("id_pelicula"));

                    Funcion f = new Funcion();
                    f.setIdFuncion(rs.getInt("id_funcion"));
                    f.setSala(s);
                    f.setPelicula(p);
                    f.setInicioFuncion(rs.getTimestamp("inicio_funcion").toLocalDateTime());
                    f.setFinFuncion(rs.getTimestamp("fin_funcion").toLocalDateTime());
                    f.setPrecio(rs.getDouble("precio"));
                    return f;
                });
    }

    @Override
    public String registrarCompra(int idCliente, int idFuncion, int piso, int idCine, int[] asientosSeleccionados) {
        String asientoString = Arrays.stream(asientosSeleccionados)
                .mapToObj(String::valueOf)
                .reduce((a, b) -> a + "," + b)
                .orElse("");

        try {
            jdbcTemplate.update(connection -> {
                CallableStatement cs = connection.prepareCall("{CALL usp_RegistrarCompra(?, ?, ?, ?, ?)}");
                cs.setInt(1, idCliente);
                cs.setInt(2, idFuncion);
                cs.setInt(3, piso);
                cs.setInt(4, idCine);
                cs.setString(5, asientoString);
                return cs;
            });
            return "Compra realizada con éxito.";
        } catch (Exception ex) {
            return "Error al realizar la compra: " + ex.getMessage();
        }
    }
}
