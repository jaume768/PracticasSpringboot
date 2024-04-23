package com.example.ataula_api.Controller;
import com.example.ataula_api.Service.GooglePlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacesController {

    private final GooglePlacesService googlePlacesService;

    public PlacesController(GooglePlacesService googlePlacesService) {
        this.googlePlacesService = googlePlacesService;
    }

    @GetMapping("/restauranteszona")
    public ResponseEntity<?> obtenerRestaurantesCercanos(@RequestParam double latitud, @RequestParam double longitud) {
        Object response = googlePlacesService.obtenerRestaurantes(latitud, longitud);
        return ResponseEntity.ok(response);
    }
}
