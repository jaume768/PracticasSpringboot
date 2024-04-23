package com.example.login_spring.Service;

import com.example.login_spring.Dto.PermisoDTO;
import com.example.login_spring.Dto.RolDTO;
import com.example.login_spring.Dto.UsuarioDTO;
import com.example.login_spring.Model.Permiso;
import com.example.login_spring.Model.Usuario;
import com.example.login_spring.Model.UsuarioPermiso;
import com.example.login_spring.Repository.RolRepository;
import com.example.login_spring.Repository.UsuarioRepository;
import com.example.login_spring.Repository.UsuarioPermisoRepository;
import com.example.login_spring.config.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.login_spring.Model.Rol;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DtoService dtoService;

    private final UsuarioPermisoRepository usuarioPermisoRepository; // Asegúrate de inyectar este repositorio

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, DtoService dtoService, UsuarioPermisoRepository usuarioPermisoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.dtoService = dtoService;
        this.usuarioPermisoRepository = usuarioPermisoRepository;
    }
    public List<String> obtenerNombresRolesPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getRoles() != null) {
            return usuario.getRoles().stream().map(Rol::getNombre).collect(Collectors.toList());
        }
        return List.of();
    }

    public List<String> obtenerNombresPermisosPorRolesDeUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && usuario.getRoles() != null) {
            return usuario.getRoles().stream()
                    .flatMap(rol -> rol.getPermisos().stream())
                    .map(Permiso::getNombre)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    public List<String> obtenerNombresPermisosEspecialesPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            List<UsuarioPermiso> usuarioPermisos = usuarioPermisoRepository.findByUsuario(usuario);

            return usuarioPermisos.stream()
                    .map(usuarioPermiso -> usuarioPermiso.getPermiso().getNombre())
                    .collect(Collectors.toList());
        }
        return Collections.emptyList(); // Devuelve una lista vacía si no se encuentran permisos o el usuario no existe
    }


    public List<String> obtenerTodosLosNombresPermisosDeUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return Collections.emptyList();
        }

        // Obtiene todos los permisos especiales del usuario, separados en activos e inactivos.
        List<UsuarioPermiso> usuarioPermisosActivos = usuarioPermisoRepository.findByUsuario(usuario).stream()
                .filter(UsuarioPermiso::isActivo)
                .collect(Collectors.toList());

        List<UsuarioPermiso> usuarioPermisosInactivos = usuarioPermisoRepository.findByUsuario(usuario).stream()
                .filter(usuarioPermiso -> !usuarioPermiso.isActivo())
                .collect(Collectors.toList());

        // Lista de nombres de permisos especiales activos.
        List<String> nombresPermisosEspecialesActivos = usuarioPermisosActivos.stream()
                .map(usuarioPermiso -> usuarioPermiso.getPermiso().getNombre())
                .collect(Collectors.toList());

        // Lista de nombres de permisos especiales inactivos.
        List<String> nombresPermisosEspecialesInactivos = usuarioPermisosInactivos.stream()
                .map(usuarioPermiso -> usuarioPermiso.getPermiso().getNombre())
                .collect(Collectors.toList());

        // Obtiene los nombres de los permisos por roles, excluyendo los permisos especiales inactivos.
        List<String> permisosPorRoles = usuario.getRoles().stream()
                .flatMap(rol -> rol.getPermisos().stream())
                .map(Permiso::getNombre)
                // Excluye los permisos que están marcados como inactivos en los permisos especiales.
                .filter(permisoNombre -> !nombresPermisosEspecialesInactivos.contains(permisoNombre))
                .collect(Collectors.toList());

        // Combina los permisos especiales activos con los permisos por roles y elimina duplicados.
        return Stream.concat(nombresPermisosEspecialesActivos.stream(), permisosPorRoles.stream())
                .distinct()
                .collect(Collectors.toList());
    }



    public boolean verificarCredenciales(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            String hashedPassword = usuario.getPassword();

            return PasswordUtil.checkPassword(password, hashedPassword);
        }
        return false;
    }

    public List<UsuarioDTO> findall() {
        return usuarioRepository.findAll().stream().map(dtoService::convertToDTO).collect(Collectors.toList());
    }

    public List<UsuarioDTO> findUsuariosPorRol(String rolBuscado) {
        return usuarioRepository.findAll().stream()
                .filter(usuario -> usuario.getRoles().stream()
                        .anyMatch(rol -> rol.getNombre().equalsIgnoreCase(rolBuscado)))
                .map(dtoService::convertToDTO)
                .collect(Collectors.toList());
    }

    public Usuario registrarUsuario(Usuario usuario) {
        String cifrado = PasswordUtil.hashPassword(usuario.getPassword());
        usuario.setPassword(cifrado);
        return usuarioRepository.save(usuario);
    }
}
