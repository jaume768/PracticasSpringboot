package com.example.login_spring.Service;

import com.example.login_spring.Dto.PermisoDTO;
import com.example.login_spring.Dto.RolDTO;
import com.example.login_spring.Model.Permiso;
import com.example.login_spring.Model.Rol;
import com.example.login_spring.Repository.PermisoRepository;
import com.example.login_spring.Repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolService {

    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;
    private final DtoService dtoService;

    public RolService(RolRepository rolRepository,DtoService dtoService,PermisoRepository permisoRepository){
        this.rolRepository = rolRepository;
        this.dtoService = dtoService;
        this.permisoRepository = permisoRepository;
    }

    public List<RolDTO> obtenerRolesConPermisos() {
        List<Rol> roles = rolRepository.findAll();
        return roles.stream().map(dtoService::convertRolToDTO).collect(Collectors.toList());
    }

    public Rol addPermisoToRol(String rolId, Long permisoId) {
        Optional<Rol> rolOpt = rolRepository.findById(Long.valueOf(rolId));
        Optional<Permiso> permisoOpt = permisoRepository.findById(permisoId);
        if (rolOpt.isPresent() && permisoOpt.isPresent()) {
            Rol rol = rolOpt.get();
            rol.getPermisos().add(permisoOpt.get());
            return rolRepository.save(rol);
        } else {
            return null;
        }
    }

    public Rol removePermisoFromRol(String rolId, Long permisoId) {
        Optional<Rol> rolOpt = rolRepository.findById(Long.valueOf(rolId));
        if (!rolOpt.isPresent()) {
            return null; // O manejar de otra manera
        }
        Rol rol = rolOpt.get();

        rol.setPermisos(
                rol.getPermisos().stream()
                        .filter(permiso -> !permiso.getId().equals(permisoId))
                        .collect(Collectors.toSet())
        );

        return rolRepository.save(rol);
    }

}
