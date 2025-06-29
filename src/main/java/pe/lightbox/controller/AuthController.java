package pe.lightbox.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.lightbox.dto.AuthRequest;
import pe.lightbox.model.Persona;
import pe.lightbox.security.JwtUtil;
import pe.lightbox.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

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
                persona.getClave());
        
        UserDetails user = new User(
                persona.getCorreo(),
                persona.getClave(),
                new ArrayList<>()  
            );
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok()
                            .header("Authorization", "Bearer " + token)
                            .body(resultado);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Persona persona = authService.validarCredenciales(request.getCorreo(), request.getClave());
        if (persona != null) {
             UserDetails user = new User(
                persona.getCorreo(),
                persona.getClave(),
                new ArrayList<>()  
            );
            String token = jwtUtil.generateToken(user);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                            .body(persona);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    } 
}