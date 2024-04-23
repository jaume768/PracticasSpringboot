package com.example.ataula_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.ataula_api.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer clienteId);
}

