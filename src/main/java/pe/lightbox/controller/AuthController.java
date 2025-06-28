package pe.lightbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.lightbox.model.Persona;
import pe.lightbox.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarUsuario(@RequestBody Persona persona) {
        String resultado = authService.registrarUsuario(
                persona.getPrimerNombre(),
                persona.getSegundoNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getDni(),
                persona.getCelular(),
                persona.getCorreo(),
                persona.getFechaNacimiento(),
                persona.getUsuario(),
                persona.getClave());
        return ResponseEntity.ok(resultado);
    }
}
