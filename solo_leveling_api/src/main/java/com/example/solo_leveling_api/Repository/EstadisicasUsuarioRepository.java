package com.example.solo_leveling_api.Repository;

import com.example.solo_leveling_api.Model.EstadisticasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadisicasUsuarioRepository extends JpaRepository<EstadisticasUsuario, Long> {
    EstadisticasUsuario findByUsuarioId(Long id);
}
