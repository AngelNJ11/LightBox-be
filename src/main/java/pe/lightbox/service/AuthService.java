package pe.lightbox.service;

import java.time.LocalDate;

import pe.lightbox.model.Persona;

public interface AuthService {

    public String registrarUsuario(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido,
                           String dni, String celular, String correo, LocalDate fechaNacimiento,
                           String usuario, String clave);
    public Persona validarCredenciales(String usuario, String clave);

}
