package com.example.ataula_api.Controller;
import com.example.ataula_api.Model.Cliente;
import com.example.ataula_api.Model.Pedido;
import com.example.ataula_api.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/register")
    public ResponseEntity<Cliente> registerCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.registerCliente(cliente);
        return new ResponseEntity<>(savedCliente, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCliente(@RequestParam String teléfono, @RequestParam String contraseña) {
        Optional<String> token = clienteService.login(teléfono, contraseña);
        return token.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed"));
    }

    @GetMapping("/cliente/{telefono}/pedidos")
    public ResponseEntity<List<Pedido>> getPedidosByClienteEmail(@PathVariable String telefono) {
        List<Pedido> pedidos = clienteService.findPedidosByClientetelefono(telefono);
        return ResponseEntity.ok(pedidos);
    }

}
