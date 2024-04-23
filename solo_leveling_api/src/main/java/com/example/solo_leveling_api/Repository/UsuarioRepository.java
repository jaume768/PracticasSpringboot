package com.example.solo_leveling_api.Repository;

import com.example.solo_leveling_api.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombre(String nombre);

    Usuario findByEmail(String email);
}
