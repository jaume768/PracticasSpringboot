package com.example.login_spring.Controller;

import java.util.List;

import com.example.login_spring.Model.Permiso;
import com.example.login_spring.Model.UsuarioPermiso;
import com.example.login_spring.Service.PermisoService;
import com.example.login_spring.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PermisoController {

    private final PermisoService permisoService;
    private final UsuarioService usuarioService;

    @Autowired
    public PermisoController(PermisoService permisoService, UsuarioService usuarioService) {
        this.permisoService = permisoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/permisos")
    public List<Permiso> getPermisos() {
        return permisoService.getPermisos();
    }

    @PostMapping ("/insertpermiso")
    public Permiso insertPermiso(@RequestBody Permiso permiso) {
        return permisoService.insertPermiso(permiso);
    }

    @GetMapping("/permisosPorRolesDeUsuario")
    public ResponseEntity<List<String>> obtenerNombresPermisosPorRolesDeUsuario(@RequestParam String email) {
        List<String> permisos = usuarioService.obtenerNombresPermisosPorRolesDeUsuario(email);
        if(permisos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(permisos);
        }
    }

    @GetMapping("/permisosEspecialesPorEmail")
    public ResponseEntity<List<String>> obtenerNombresPermisosEspecialesPorEmail(@RequestParam String email) {
        List<String> permisos = usuarioService.obtenerNombresPermisosEspecialesPorEmail(email);
        if(permisos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(permisos);
        }
    }
}