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
@Table(name = "Niveles")
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "experiencia_requerida", nullable = false)
    private int experienciaRequerida;

    // Relación uno a muchos con Nivel_Usuario
    @OneToMany(mappedBy = "nivel")
    private List<NivelUsuario> usuarios;

    public Nivel(Long id, int experienciaRequerida) {
        this.id = id;
        this.experienciaRequerida = experienciaRequerida;
    }

    public Nivel() {

    }

    // Agrega los constructores, métodos personalizados, etc. según sea necesario
}

