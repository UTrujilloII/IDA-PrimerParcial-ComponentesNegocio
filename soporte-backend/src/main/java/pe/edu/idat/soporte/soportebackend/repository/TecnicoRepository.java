package pe.edu.idat.soporte.soportebackend.repository;

import org.springframework.stereotype.Repository;
import pe.edu.idat.soporte.soportebackend.model.Cliente;
import pe.edu.idat.soporte.soportebackend.model.Solicitud;
import pe.edu.idat.soporte.soportebackend.model.Tecnico;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TecnicoRepository implements ITecnicoRepository{

    private final Map<Integer, Tecnico> bdTecnico = new HashMap<>();
    private final AtomicInteger secuencial = new AtomicInteger(0);

    @Override
    public List<Tecnico> buscarTodo() {
        return new ArrayList<>(bdTecnico.values());
    }

    @Override
    public void guardar(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            tecnico.setId(secuencial.incrementAndGet());
        }
        bdTecnico.put(tecnico.getId(), tecnico);
    }

    @Override
    public Optional<Tecnico> buscarPorId(Integer id) {
        return Optional.ofNullable(bdTecnico.get(id));
    }

    @Override
    public void actualizar(Tecnico tecnico) {
        if (bdTecnico.containsKey(tecnico.getId())) {
            bdTecnico.put(tecnico.getId(), tecnico);
        }
    }

    @Override
    public void eliminar(Integer id) {
        bdTecnico.remove(id);
    }
}
