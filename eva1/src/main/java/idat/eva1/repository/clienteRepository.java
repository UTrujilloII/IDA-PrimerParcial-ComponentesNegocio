package idat.eva1.repository;

import idat.eva1.model.Cliente;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class clienteRepository
{
    private final Map<Integer, Cliente> clientes = new HashMap<>();
    private final AtomicInteger secuencial = new AtomicInteger(0);

    //registra/crea
    public Cliente registrarCliente(Cliente cliente)
    {
        if (cliente.getIdCliente() == null)
        {
            cliente.setIdCliente(secuencial.incrementAndGet());
        }
        cliente.setFechaRegistro(new Date());
        clientes.put(cliente.getIdCliente(), cliente);
        return cliente;
    }

    //lee
    public List<Cliente> listarCliente()
    {
        return new ArrayList<>(clientes.values());
    }

    //busca/lee por id
    public Cliente buscarClientePorId(Integer id)
    {
        return clientes.get(id);
    }

    //actualiza
    public Cliente actualizarCliente(Integer id, Cliente clienteActualizado) {
        Cliente existe = clientes.get(id);
        if (existe != null) {
            existe.setDniCliente(clienteActualizado.getDniCliente());
            existe.setApellidoPaternoCliente(clienteActualizado.getApellidoPaternoCliente());
            existe.setApellidoMaternoCliente(clienteActualizado.getApellidoMaternoCliente());
            existe.setNombreCliente(clienteActualizado.getNombreCliente());
        }
        return existe;
    }

    //elimina
    public boolean eliminarCliente(Integer id)
    {
        return clientes.remove(id) != null;
    }


}
