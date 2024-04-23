package com.example.ataula_api.Controller;

import com.example.ataula_api.Model.Comida;
import com.example.ataula_api.Model.Restaurante;
import com.example.ataula_api.Service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    @GetMapping("/{restauranteId}/comidas")
    public ResponseEntity<List<Comida>> getComidasByRestauranteId(@PathVariable Integer restauranteId) {
        List<Comida> comidas = restauranteService.findComidasByRestauranteId(restauranteId);
        return ResponseEntity.ok(comidas);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurante>> getAllRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.findAllRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }
}
