package com.example.solo_leveling_api.Model;
import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Entrenamientos")
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Relación muchos a muchos con Usuario
    @ManyToMany(mappedBy = "entrenamientos")
    private List<Usuario> usuarios;

    // Agrega los constructores, métodos personalizados, etc. según sea necesario
}

