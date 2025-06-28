package pe.lightbox.service;

import java.time.LocalDate;

public interface AuthService {

    public String registrarUsuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                           String dni, String celular, String correo, LocalDate fechaNacimiento,
                           String usuario, String clave);

}
