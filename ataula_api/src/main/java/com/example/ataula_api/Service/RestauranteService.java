package com.example.ataula_api.Service;
import com.example.ataula_api.Repository.ComidaRepository;
import com.example.ataula_api.Repository.RestauranteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.ataula_api.Model.Restaurante;
import com.example.ataula_api.Model.Comida;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final ComidaRepository comidaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, ComidaRepository comidaRepository){
        this.restauranteRepository = restauranteRepository;
        this.comidaRepository = comidaRepository;
    }

    public List<Restaurante> findAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    public List<Comida> findComidasByRestauranteId(Integer restauranteId) {
        return comidaRepository.findByRestauranteId(restauranteId);
    }
}

