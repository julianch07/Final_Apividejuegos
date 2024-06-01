package com.videojuegos.apivideojuegos.service;

import com.videojuegos.apivideojuegos.model.Client;
import com.videojuegos.apivideojuegos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Client> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Client getClienteById(Long clienteId) {
        return clienteRepository.findById(clienteId).orElse(null);
    }

    @Override
    public Client createCliente(Client cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Client updateCliente(Long clienteId, Client nuevoCliente) {
        Client clienteExistente = getClienteById(clienteId);
        if (clienteExistente != null) {
            nuevoCliente.setClienteId(clienteId);
            return clienteRepository.save(nuevoCliente);
        }
        return null;
    }

    @Override
    public void deleteCliente(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}