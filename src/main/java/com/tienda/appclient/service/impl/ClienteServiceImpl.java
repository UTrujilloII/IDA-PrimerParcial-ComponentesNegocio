package com.tienda.appclient.service.impl;

import com.tienda.appclient.exception.DuplicateResourceException;
import com.tienda.appclient.exception.ResourceNotFoundException;
import com.tienda.appclient.model.Cliente;
import com.tienda.appclient.repository.ClienteRepository;
import com.tienda.appclient.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente crear(Cliente cliente) {
        // Evitar duplicados por DNI
        if (cliente.getDni() != null && clienteRepository.findByDni(cliente.getDni()).isPresent()) {
            throw new DuplicateResourceException("Ya existe un cliente con el DNI: " + cliente.getDni());
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerPorId(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public Optional<Cliente> obtenerPorDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente actualizar(Integer id, Cliente cliente) {
        return clienteRepository.findById(id).map(existing -> {
            existing.setDni(cliente.getDni());
            existing.setApellidoPaterno(cliente.getApellidoPaterno());
            existing.setApellidoMaterno(cliente.getApellidoMaterno());
            existing.setNombres(cliente.getNombres());
            existing.setFechaNacimiento(cliente.getFechaNacimiento());
            return clienteRepository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Integer id) {
        // Comprobar existencia y lanzar excepción clara si no existe
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
