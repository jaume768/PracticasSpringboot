package com.example.ataula_api.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToMany(mappedBy = "tipos")
    @JsonIgnore
    private List<Restaurante> restaurantes;

    public Tipo() {
    }

    public Tipo(String nombre) {
        this.nombre = nombre;
    }
}
