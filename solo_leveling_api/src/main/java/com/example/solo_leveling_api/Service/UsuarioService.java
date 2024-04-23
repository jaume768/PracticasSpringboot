package com.example.solo_leveling_api.Service;
import com.example.solo_leveling_api.Config.PasswordUtil;
import com.example.solo_leveling_api.Model.*;
import com.example.solo_leveling_api.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final NivelUsuarioRepository nivelUsuarioRepository;
    private final UsuarioMisionRepository usuarioMisionRepository;
    private final EstadisicasUsuarioRepository estadisicasUsuarioRepository;
    private final MisionRepository misionRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, NivelUsuarioRepository nivelUsuarioRepository, UsuarioMisionRepository usuarioMisionRepository, EstadisicasUsuarioRepository estadisicasUsuarioRepository, MisionRepository misionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.nivelUsuarioRepository = nivelUsuarioRepository;
        this.usuarioMisionRepository = usuarioMisionRepository;
        this.estadisicasUsuarioRepository = estadisicasUsuarioRepository;
        this.misionRepository = misionRepository;
    }

    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean verificarCredenciales(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String hashedPassword = usuario.getPassword();

            return PasswordUtil.checkPassword(password, hashedPassword);
        }
        return false;
    }

    public UsuarioMision registrarUsuarioMision(String email, Long misionId) throws Exception {
        Usuario usuario = findByEmail(email);
        Optional<Mision> mision = misionRepository.findById(misionId);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        UsuarioMision usuarioMision = usuarioMisionRepository.findByUsuarioAndMisionId(usuario, misionId);
        if (usuarioMision != null) {
            throw new Exception("El usuario ya está registrado en esta misión");
        }
        usuarioMision = new UsuarioMision();
        usuarioMision.setUsuario(usuario);
        usuarioMision.setMision(mision.get());
        usuarioMision.setCompletada(false);
        usuarioMision.setProgresoActual(0);
        usuarioMision.setFecha(new Date());
        usuarioMisionRepository.save(usuarioMision);
        return usuarioMision;
    }


    public EstadisticasUsuario findEstadisticasByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return estadisicasUsuarioRepository.findByUsuarioId(usuario.getId());
        }
        return null;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        String cifrado = PasswordUtil.hashPassword(usuario.getPassword());
        usuario.setPassword(cifrado);
        return usuarioRepository.save(usuario);
    }

    public List<NivelUsuario> findNivelByUsuarioEmail(String email) {
        Usuario usuario = findByEmail(email);
        if (usuario != null) {
            return nivelUsuarioRepository.findByUsuarioId(usuario.getId());
        }
        return null;
    }

    public Nivel findNivelInfoByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getNiveles() != null) {
            return new Nivel(usuario.getNiveles().get(0).getNivel().getId(),usuario.getNiveles().get(0).getNivel().getExperienciaRequerida());
        } else {
            return null;
        }
    }

    public UsuarioMision findUsuarioMisionByEmailAndMisionId(String email, Long misionId) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return usuarioMisionRepository.findByUsuarioAndMisionId(usuario, misionId);
        }
        return null;
    }
}

