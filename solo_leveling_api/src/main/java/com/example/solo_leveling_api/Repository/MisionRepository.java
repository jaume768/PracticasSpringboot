package com.example.solo_leveling_api.Repository;

import com.example.solo_leveling_api.Model.Dificultad;
import com.example.solo_leveling_api.Model.Mision;
import com.example.solo_leveling_api.Model.NivelUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MisionRepository extends JpaRepository<Mision, Long> {
    List<Mision> findByDificultad(Dificultad dificultad);
}
