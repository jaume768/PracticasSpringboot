package com.example.ataula_api.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detalle_pedido")
@Getter
@Setter
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleId;

    @Column(name = "pedidoId")
    private Integer pedidoId;

    @Column(name = "comidaId")
    private Integer comidaId;

    private Integer cantidad;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedidoId", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "comidaId", insertable = false, updatable = false)
    private Comida comida;
}

