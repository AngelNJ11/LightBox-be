package pe.lightbox.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.lightbox.model.Persona;
import pe.lightbox.security.JwtUtil;
import pe.lightbox.service.AuthService;

@RestController
@RequestMapping("/auth")
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
                persona.getUsuario(),
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

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.validarCredenciales(request.getUsuario(), request.getClave());
        if (usuario != null) {
            String token = jwtUtil.generateToken(usuario.getUsuario());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }*/
}