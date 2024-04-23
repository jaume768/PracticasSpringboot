package com.example.ataula_api.Repository;
import com.example.ataula_api.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByTeléfonoAndContraseña(String teléfono, String contraseña);

    Optional<Cliente> findByTeléfono(String teléfono);
}
