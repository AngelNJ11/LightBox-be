package pe.lightbox.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.lightbox.model.Persona;
import pe.lightbox.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String registrarUsuario(String primerNombre, String segundoNombre, String primerApellido,
            String segundoApellido,
            String dni, String celular, String correo, LocalDate fechaNacimiento, String clave) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String claveEncriptada = encoder.encode(clave);

            jdbcTemplate.execute(
                    (Connection con) -> {
                        CallableStatement cs = con
                                .prepareCall("{CALL usp_register_user(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                        cs.setString(1, primerNombre);
                        cs.setObject(2, segundoNombre != null && !segundoNombre.isEmpty() ? segundoNombre : null,
                                Types.VARCHAR);
                        cs.setString(3, primerApellido);
                        cs.setObject(4, segundoApellido != null && !segundoApellido.isEmpty() ? segundoApellido : null,
                                Types.VARCHAR);
                        cs.setString(5, dni);
                        cs.setString(6, celular);
                        cs.setString(7, correo);
                        cs.setDate(8, java.sql.Date.valueOf(fechaNacimiento));
                        cs.setString(10, claveEncriptada);
                        return cs;
                    },
                    (CallableStatementCallback<Void>) cs -> {
                        cs.execute();
                        return null;
                    });
            return "Registro exitoso";
        } catch (Exception e) {
            return "Error al registrar: " + e.getMessage();
        }
    }

    @Override
    public Persona validarCredenciales(String correo, String clave) {
        try {
            return jdbcTemplate.execute((ConnectionCallback<Persona>) con -> {
                try (CallableStatement cs = con.prepareCall("{CALL usp_login_auth(?)}")) {
                    cs.setString(1, correo); 

                    boolean hasResult = cs.execute();

                    if (hasResult) {
                        try (ResultSet rs = cs.getResultSet()) {
                            if (rs.next()) {
                                String claveEncriptada = rs.getString("clave");

                                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                                if (encoder.matches(clave, claveEncriptada)) {
                                    Persona persona = new Persona();
                                    persona.setIdPersona(rs.getInt("id_persona"));
                                    persona.setPrimerNombre(rs.getString("primer_nombre"));
                                    persona.setSegundoNombre(rs.getString("segundo_nombre"));
                                    persona.setPrimerApellido(rs.getString("primer_apellido"));
                                    persona.setSegundoApellido(rs.getString("segundo_apellido"));
                                    persona.setDni(rs.getString("dni"));
                                    persona.setCelular(rs.getString("celular"));
                                    persona.setCorreo(rs.getString("correo"));
                                    persona.setFechaNacimiento(rs.getDate("fec_nacimiento").toLocalDate());
                                    persona.setFecRegistro(rs.getDate("fec_registro").toLocalDate());
                                    persona.setClave(claveEncriptada);
                                    return persona;
                                }
                            }
                        }
                    }

                    return null; 
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
