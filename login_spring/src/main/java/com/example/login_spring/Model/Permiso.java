package com.example.login_spring.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Table(name = "permiso")
@Entity
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    @JsonIgnore // Ignora la lista de usuarios para evitar recursión
    @ManyToMany(mappedBy = "permisos", fetch = FetchType.LAZY)
    private Set<Rol> roles;

    @OneToMany(mappedBy = "permiso")
    @JsonIgnore
    private Set<UsuarioPermiso> usuarioPermisos;

}
