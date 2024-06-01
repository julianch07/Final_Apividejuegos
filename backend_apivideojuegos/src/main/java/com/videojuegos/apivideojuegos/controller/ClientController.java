package com.videojuegos.apivideojuegos.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.videojuegos.apivideojuegos.model.Client;
import com.videojuegos.apivideojuegos.service.ClienteService;



@RestController
@RequestMapping("/api/clientes")
public class ClientController {

    @Autowired
    private  ClienteService clienteService;

    public ClientController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Client> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Client> getClienteById(@PathVariable Long clienteId) {
        Client cliente = clienteService.getClienteById(clienteId);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Client> createCliente(@RequestBody Client cliente) {
        Client nuevoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Client> updateCliente(@PathVariable Long clienteId, @RequestBody Client cliente) {
        Client clienteActualizado = clienteService.updateCliente(clienteId, cliente);
        return clienteActualizado != null ? ResponseEntity.ok(clienteActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long clienteId) {
        clienteService.deleteCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}