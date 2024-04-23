package com.example.login_spring.Controller;

import com.example.login_spring.Dto.UsuarioDTO;
import com.example.login_spring.Model.Usuario;
import com.example.login_spring.Service.TokenService;
import com.example.login_spring.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TokenService tokenService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService,TokenService tokenService) {
        this.usuarioService = usuarioService;
        this.tokenService = tokenService;
    }

    @GetMapping("/usuarios")
    public List<UsuarioDTO> findall(){
        return usuarioService.findall();
    }

    @GetMapping("/usuariosconroles")
    public ResponseEntity<?> findall(@RequestHeader("Authorization") String authHeader) {
        // Verificar si hay un token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No autorizado");
        }
        String token = authHeader.substring(7); // Quitar "Bearer "
        try {
            String rolUsuario = tokenService.extractRolFromToken(token);
            List<UsuarioDTO> usuarios = usuarioService.findUsuariosPorRol(rolUsuario);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acceso Prohibido");
        }
    }

    @GetMapping("/permisosUsuario")
    public ResponseEntity<?> obtenerPermisosPorUsuarios(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("No autorizado", HttpStatus.UNAUTHORIZED);
        }
        String token = authHeader.substring(7); // Quitar "Bearer "

        try {
            String emailUsuario = tokenService.extractEmailFromToken(token);

            List<String> permisos = usuarioService.obtenerTodosLosNombresPermisosDeUsuario(emailUsuario);

            if(permisos.isEmpty()) {
                return new ResponseEntity<>("El usuario no tiene permisos asignados o no existe.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(permisos);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la extracción del email o en la obtención de permisos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/permisoUsuarioEmail")
    public ResponseEntity<?> obtenerPermisosPorEmail(@RequestParam("email") String email) {
        try {
            List<String> permisos = usuarioService.obtenerTodosLosNombresPermisosDeUsuario(email);

            if (permisos.isEmpty()) {
                return new ResponseEntity<>("El usuario no tiene permisos asignados o no existe.", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(permisos);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la obtención de permisos para el email proporcionado", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
