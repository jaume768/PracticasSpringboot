package com.example.ataula_api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ataula_api.Model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
}

