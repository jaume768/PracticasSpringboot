package com.example.login_spring.Repository;

import com.example.login_spring.Model.Rol;
import com.example.login_spring.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // definir metodos adicionales si hacen falta.

    Usuario findByEmail(String email);
}
