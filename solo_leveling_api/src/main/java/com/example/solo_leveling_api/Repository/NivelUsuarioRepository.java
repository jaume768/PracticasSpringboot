package com.example.solo_leveling_api.Repository;

import com.example.solo_leveling_api.Model.NivelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NivelUsuarioRepository extends JpaRepository<NivelUsuario, Long> {
    List<NivelUsuario> findByUsuarioId(Long usuarioId);
}
