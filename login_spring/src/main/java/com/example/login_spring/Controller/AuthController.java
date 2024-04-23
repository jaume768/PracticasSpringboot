package com.example.login_spring.Controller;

import com.example.login_spring.Model.Usuario;
import com.example.login_spring.Service.TokenService;
import com.example.login_spring.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthController {

    private final UsuarioService usuarioService;
    public final TokenService tokenService;

    @Autowired
    public AuthController(UsuarioService usuarioService,TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "¡Hola!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        String email = usuario.getEmail();
        String password = usuario.getPassword();

        if (usuarioService.verificarCredenciales(email, password)) {
            List<String> nombresRoles = usuarioService.obtenerNombresRolesPorEmail(email);
            String roles = String.join(",", nombresRoles);
            String token = tokenService.generarToken(email,roles);
            return ResponseEntity.ok("Token: " + token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarNuevoUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario usuarioRegistrado = usuarioService.registrarUsuario(usuario);
            usuarioRegistrado.setPassword(null); // Es una buena práctica no retornar la contraseña, incluso cifrada
            return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

