package com.example.login_spring.Dto;


import lombok.Data;

import java.util.Set;

@Data
public class RolDTO {
    private Long id;
    private String nombre;
    private Set<PermisoDTO> permisos;
}
