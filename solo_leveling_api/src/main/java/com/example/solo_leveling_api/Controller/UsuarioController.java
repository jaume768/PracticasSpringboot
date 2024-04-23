package com.example.solo_leveling_api.Controller;

import com.example.solo_leveling_api.Model.*;
import com.example.solo_leveling_api.Service.MisionService;
import com.example.solo_leveling_api.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final MisionService misionService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, MisionService misionService) {
        this.usuarioService = usuarioService;
        this.misionService = misionService;
    }

    @GetMapping("/all")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/{email}/nivel")
    public ResponseEntity<NivelUsuario> getNivelUsuarioByEmail(@PathVariable String email) {
        List<NivelUsuario> niveles = usuarioService.findNivelByUsuarioEmail(email);
        NivelUsuario nivelUsuario = niveles.get(0);
        if (nivelUsuario != null) {
            return new ResponseEntity<>(nivelUsuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{email}/nivelactual")
    public ResponseEntity<Nivel> getNivelInfoByEmail(@PathVariable String email) {
        Nivel nivel = usuarioService.findNivelInfoByEmail(email);
        if (nivel != null) {
            return new ResponseEntity<>(nivel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/misiones-diarias/{email}")
    public ResponseEntity<List<Mision>> getMisionesDiarias(@PathVariable String email) {
        try {
            List<Mision> misiones = misionService.getMisionesDiariasParaUsuario(email);
            if (!misiones.isEmpty()) {
                return new ResponseEntity<>(misiones, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{email}/misiones/{misionId}")
    public ResponseEntity<UsuarioMision> getUsuarioMisionByEmailAndMisionId(@PathVariable String email, @PathVariable Long misionId) {
        UsuarioMision usuarioMision = usuarioService.findUsuarioMisionByEmailAndMisionId(email, misionId);
        if (usuarioMision != null) {
            return ResponseEntity.ok(usuarioMision);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{email}/estadisticas")
    public ResponseEntity<EstadisticasUsuario> getEstadisticasUsuarioByEmail(@PathVariable String email) {
        EstadisticasUsuario estadisticas = usuarioService.findEstadisticasByEmail(email);
        if (estadisticas != null) {
            return new ResponseEntity<>(estadisticas, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{email}/misiones/{misionId}/registrar")
    public ResponseEntity<UsuarioMision> registrarUsuarioMision(@PathVariable String email, @PathVariable Long misionId) {
        try {
            UsuarioMision usuarioMision = usuarioService.registrarUsuarioMision(email, misionId);
            return ResponseEntity.ok(usuarioMision);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

