package com.example.solo_leveling_api.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Misiones")
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "experiencia", nullable = false)
    private int experiencia;

    @Column(name = "acciones_requeridas", nullable = false)
    private int accionesRequeridas;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Dificultad dificultad;

    // Relación muchos a muchos con Usuario
    @ManyToMany(mappedBy = "misiones")
    @JsonIgnore
    private List<Usuario> usuarios;

    // Agrega los constructores, métodos personalizados, etc. según sea necesario
}

