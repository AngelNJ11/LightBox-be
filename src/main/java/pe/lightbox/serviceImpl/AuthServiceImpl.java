package pe.lightbox.serviceImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pe.lightbox.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String registrarUsuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
            String dni, String celular, String correo, LocalDate fechaNacimiento,
            String usuario, String clave) {
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String claveEncriptada = encoder.encode(clave);

            jdbcTemplate.execute(
                    (Connection con) -> {
                        CallableStatement cs = con
                                .prepareCall("{CALL usp_register_user(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
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
                        cs.setString(9, usuario);
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
}
