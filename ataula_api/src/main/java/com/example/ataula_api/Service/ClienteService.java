package com.example.ataula_api.Service;
import com.example.ataula_api.Config.PasswordUtil;
import com.example.ataula_api.Model.Cliente;
import com.example.ataula_api.Model.Pedido;
import com.example.ataula_api.Repository.ClienteRepository;
import com.example.ataula_api.Repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final TokenService tokenService;

    public ClienteService(ClienteRepository clienteRepository, TokenService tokenService, PedidoRepository pedidoRepository){
        this.clienteRepository = clienteRepository;
        this.tokenService = tokenService;
        this.pedidoRepository = pedidoRepository;
    }

    public Cliente registerCliente(Cliente cliente) {
        String hashedPassword = PasswordUtil.hashPassword(cliente.getContraseña());
        cliente.setContraseña(hashedPassword);
        return clienteRepository.save(cliente);
    }

    public Optional<String> login(String teléfono, String contraseña) {
        Optional<Cliente> cliente = clienteRepository.findByTeléfono(teléfono);
        System.out.println(cliente.get());
        if (cliente.isPresent() && PasswordUtil.checkPassword(contraseña, cliente.get().getContraseña())) {
            String token = tokenService.generarToken(cliente.get().getTeléfono(), cliente.get().getNombre(), cliente.get().getClienteId());
            return Optional.of(token);
        }
        return Optional.empty();
    }

    public List<Pedido> findPedidosByClientetelefono(String teléfono) {
        Optional<Cliente> cliente = clienteRepository.findByTeléfono(teléfono);

        List<Pedido> pedidos = pedidoRepository.findByClienteId(cliente.get().getClienteId());

        return pedidos;
    }

}

