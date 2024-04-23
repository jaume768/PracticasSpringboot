package com.example.ataula_api.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comidas")
@Getter
@Setter
public class Comida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comidaId;

    @Column(name = "restauranteId")
    private Integer restauranteId;

    private String nombre;
    private String descripción;
    private Double precio;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "restauranteId", insertable = false, updatable = false)
    private Restaurante restaurante;
}