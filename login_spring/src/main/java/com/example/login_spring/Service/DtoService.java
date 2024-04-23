package com.example.login_spring.Service;

import com.example.login_spring.Dto.PermisoDTO;
import com.example.login_spring.Dto.RolDTO;
import com.example.login_spring.Dto.UsuarioDTO;
import com.example.login_spring.Model.Permiso;
import com.example.login_spring.Model.Rol;
import com.example.login_spring.Model.Usuario;
import com.example.login_spring.Model.UsuarioPermiso;
import com.example.login_spring.Repository.UsuarioPermisoRepository;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DtoService {

    private final UsuarioPermisoRepository usuarioPermisoRepository;

    public DtoService(UsuarioPermisoRepository usuarioPermisoRepository){
        this.usuarioPermisoRepository = usuarioPermisoRepository;
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRoles(usuario.getRoles().stream().map(this::convertRolToDTO).collect(Collectors.toSet()));
        Set<PermisoDTO> permisosEspeciales = usuarioPermisoRepository.findByUsuario(usuario).stream()
                .map(UsuarioPermiso::getPermiso)
                .map(this::convertToPermiso)
                .collect(Collectors.toSet());

        dto.setPermisosEspeciales(permisosEspeciales);
        return dto;
    }

    public RolDTO convertRolToDTO(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombre(rol.getNombre());
        dto.setPermisos(rol.getPermisos().stream().map(this::convertToPermiso).collect(Collectors.toSet()));
        return dto;
    }

    public PermisoDTO convertToPermiso(Permiso permiso){
        PermisoDTO dto = new PermisoDTO();
        dto.setId(permiso.getId());
        dto.setDescripcion(permiso.getDescripcion());
        dto.setNombre(permiso.getNombre());

        return dto;
    }



}
