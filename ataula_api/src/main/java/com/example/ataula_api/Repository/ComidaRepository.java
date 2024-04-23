package com.example.ataula_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.ataula_api.Model.Comida;

public interface ComidaRepository extends JpaRepository<Comida, Integer> {
    List<Comida> findByRestauranteId(Integer restauranteId);
}
