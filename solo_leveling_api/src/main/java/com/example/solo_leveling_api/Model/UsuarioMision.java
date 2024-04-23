package com.example.solo_leveling_api.Model;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Usuario_Mision")
public class UsuarioMision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "mision_id", nullable = false)
    private Mision mision;

    @Column(name = "completada", nullable = false)
    private boolean completada;

    @Column(name = "progreso_actual", nullable = false)
    private int progresoActual;

    @Column(name = "fecha_asignacion", columnDefinition = "date")
    private Date fecha;

}

