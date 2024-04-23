package com.example.login_spring.Dto;

import lombok.Data;

import java.util.Set;

@Data
public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String email;
    private Set<RolDTO> roles;
    private Set<PermisoDTO> permisosEspeciales;
}


