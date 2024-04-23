package com.example.solo_leveling_api.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estadisticas_usuario")
public class EstadisticasUsuario {

    @Id
    @Column(name = "usuario_id")
    private Long usuarioId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "fuerza")
    private Integer fuerza;

    @Column(name = "resistencia")
    private Integer resistencia;

    @Column(name = "velocidad")
    private Integer velocidad;

    @Column(name = "agilidad")
    private Integer agilidad;

    @Column(name = "inteligencia")
    private Integer inteligencia;

    public EstadisticasUsuario() {
    }

    public EstadisticasUsuario(Long usuarioId, Integer fuerza, Integer resistencia, Integer velocidad, Integer agilidad) {
        this.usuarioId = usuarioId;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.velocidad = velocidad;
        this.agilidad = agilidad;
    }

}
