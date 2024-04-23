package com.example.ataula_api.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    private String nombre;
    private String apellido;
    @Column(name = "contraseña") // Ensure this annotation if the database column has a different name
    private String contraseña;
    private String teléfono;
    private String dirección;
    private String numeroDomicilio;
    private String ciudad;
}

