package com.example.ataula_api.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@Getter
@Setter
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restauranteId;

    private String nombre;
    private String teléfono;
    private String dirección;
    private String ciudad;
    private String imagen;

    @ManyToMany
    @JoinTable(
            name = "restaurante_tipo",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_id")
    )
    private List<Tipo> tipos;

    public Restaurante() {
    }

    // Constructor con parámetros si es necesario
    public Restaurante(String nombre, String teléfono, String dirección, String ciudad, String imagen) {
        this.nombre = nombre;
        this.teléfono = teléfono;
        this.dirección = dirección;
        this.ciudad = ciudad;
        this.imagen = imagen;
    }
}
