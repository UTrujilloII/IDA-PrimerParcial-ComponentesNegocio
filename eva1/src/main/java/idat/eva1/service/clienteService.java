package idat.eva1.service;


import idat.eva1.dto.clienteDTO;
import idat.eva1.exception.NotFoundException;
import idat.eva1.model.Cliente;
import idat.eva1.repository.clienteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class clienteService {

    //comunica con repository
    private final clienteRepository clienteRepository;
    public clienteService(clienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    //crear
    public clienteDTO registrarCliente(clienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setDniCliente(dto.getDniCliente());
        cliente.setApellidoPaternoCliente(dto.getApellidoPaternoCliente());
        cliente.setApellidoMaternoCliente(dto.getApellidoMaternoCliente());
        cliente.setNombreCliente(dto.getNombreCliente());
        cliente.setFechaRegistro(new Date());

        Cliente nuevo = clienteRepository.registrarCliente(cliente);

        clienteDTO respuesta = new clienteDTO();
        respuesta.setDniCliente(nuevo.getDniCliente());
        respuesta.setApellidoPaternoCliente(nuevo.getApellidoPaternoCliente());
        respuesta.setApellidoMaternoCliente(nuevo.getApellidoMaternoCliente());
        respuesta.setNombreCliente(nuevo.getNombreCliente());
        return respuesta;
    }

    //listar todos
    public List<Cliente> listarCliente() {
        return clienteRepository.listarCliente();
    }

    //buscar por id
    public Cliente buscarClientePorId(Integer id)
    {
        Cliente cliente = clienteRepository.buscarClientePorId(id);
        if(cliente==null)
        {
            throw new NotFoundException("ERROR: Cliente " + id + " no encontrada");
        }
        return cliente;
    }

    //actualizar
    public clienteDTO actualizarCliente(Integer id, clienteDTO dto) {
        Cliente existente = clienteRepository.buscarClientePorId(id);
        if (existente == null) {
            throw new NotFoundException("Error: el cliente " + id + " no existe");
        }

        existente.setDniCliente(dto.getDniCliente());
        existente.setApellidoPaternoCliente(dto.getApellidoPaternoCliente());
        existente.setApellidoMaternoCliente(dto.getApellidoMaternoCliente());
        existente.setNombreCliente(dto.getNombreCliente());
        existente.setFechaRegistro(new Date());
        clienteRepository.actualizarCliente(id, existente);

        clienteDTO actualizado = new clienteDTO();
        actualizado.setDniCliente(existente.getDniCliente());
        actualizado.setApellidoPaternoCliente(existente.getApellidoPaternoCliente());
        actualizado.setApellidoMaternoCliente(existente.getApellidoMaternoCliente());
        actualizado.setNombreCliente(existente.getNombreCliente());
        return actualizado;
    }


    //eliminar
    public void eliminarCliente(Integer id) {
        boolean eliminado = clienteRepository.eliminarCliente(id);
        if (!eliminado) {
            throw new NotFoundException("Error: cliente " + id + " no encontrado");
        }
    }
}