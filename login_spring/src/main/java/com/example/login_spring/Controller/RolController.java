package com.example.login_spring.Controller;

import com.example.login_spring.Dto.RolDTO;
import com.example.login_spring.Model.Rol;
import com.example.login_spring.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RolController {

    private final RolService rolService;

    @Autowired
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RolDTO>> obtenerRolesConPermisos() {
        List<RolDTO> roles = rolService.obtenerRolesConPermisos();
        if (roles != null && !roles.isEmpty()) {
            return ResponseEntity.ok(roles);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("insertpermiso/{rolId}/{permisoId}")
    public ResponseEntity<Rol> addPermisoToRol(@PathVariable String rolId, @PathVariable Long permisoId) {
        Rol rolUpdated = rolService.addPermisoToRol(rolId, permisoId);
        if (rolUpdated != null) {
            return ResponseEntity.ok(rolUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/rol/{rolId}/permiso/{permisoId}")
    public ResponseEntity<Rol> removePermisoFromRol(@PathVariable String rolId, @PathVariable Long permisoId) {
        Rol rolUpdated = rolService.removePermisoFromRol(rolId, permisoId);
        if (rolUpdated != null) {
            return ResponseEntity.ok(rolUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
