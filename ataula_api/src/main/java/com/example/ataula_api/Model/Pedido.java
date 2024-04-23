package com.example.ataula_api.Model;
import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidoId;

    @Column(name = "clienteId")
    private Integer clienteId;

    @Column(name = "restauranteId")
    private Integer restauranteId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    private Double total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "clienteId", insertable = false, updatable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restauranteId", insertable = false, updatable = false)
    private Restaurante restaurante;
}

