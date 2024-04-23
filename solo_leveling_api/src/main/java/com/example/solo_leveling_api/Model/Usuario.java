package com.example.solo_leveling_api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contraseña", nullable = false)
    private String password;

    @OneToMany(mappedBy = "usuario")

    private List<NivelUsuario> niveles;

    @ManyToMany
    @JoinTable(
            name = "Usuario_Entrenamiento",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "entrenamiento_id")
    )
    private List<Entrenamiento> entrenamientos;

    @ManyToMany
    @JoinTable(
            name = "Usuario_Mision",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "mision_id")
    )
    private List<Mision> misiones;

}
