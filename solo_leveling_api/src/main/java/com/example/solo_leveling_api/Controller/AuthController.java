package com.example.solo_leveling_api.Controller;

import com.example.solo_leveling_api.Model.Usuario;
import com.example.solo_leveling_api.Service.TokenService;
import com.example.solo_leveling_api.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

        Usuario usuarioLogin = usuarioService.findByEmail(usuario.getEmail());

        String email = usuarioLogin.getEmail();
        String password = usuario.getPassword();
        int id = Math.toIntExact(usuarioLogin.getId());
        String nombre = usuarioLogin.getNombre();

        if (usuarioService.verificarCredenciales(email, password)) {
            String token = tokenService.generarToken(email,nombre,id);
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
