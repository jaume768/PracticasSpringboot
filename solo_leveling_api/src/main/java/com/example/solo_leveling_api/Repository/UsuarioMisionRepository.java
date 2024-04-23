package com.example.solo_leveling_api.Repository;

import com.example.solo_leveling_api.Model.Usuario;
import com.example.solo_leveling_api.Model.UsuarioMision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface UsuarioMisionRepository extends JpaRepository<UsuarioMision, Long> {
    UsuarioMision findByUsuarioAndMisionId(Usuario usuario, Long misionId);

    @Query("SELECT um FROM UsuarioMision um WHERE um.usuario.email = :email AND um.fecha >= :startOfDay AND um.fecha < :endOfDay")
    List<UsuarioMision> findMisionesByUsuarioEmailAndFecha(String email, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startOfDay, @DateTimeFormat(pattern = "yyyy-MM-dd") Date endOfDay);
}

