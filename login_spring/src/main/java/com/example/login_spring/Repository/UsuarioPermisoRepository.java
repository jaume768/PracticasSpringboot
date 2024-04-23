package com.example.login_spring.Repository;

import com.example.login_spring.Model.Usuario;
import com.example.login_spring.Model.UsuarioPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioPermisoRepository extends JpaRepository<UsuarioPermiso, Long> {
    List<UsuarioPermiso> findByUsuario(Usuario usuario);
}
