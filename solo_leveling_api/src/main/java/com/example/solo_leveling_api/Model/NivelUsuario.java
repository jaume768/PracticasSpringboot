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
@Table(name = "Nivel_Usuario")
public class NivelUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "nivel_id", nullable = false)
    private Nivel nivel;

    @Column(name = "experiencia_actual", nullable = false)
    private int experienciaActual;

    // Agrega los constructores, métodos personalizados, etc. según sea necesario
}

