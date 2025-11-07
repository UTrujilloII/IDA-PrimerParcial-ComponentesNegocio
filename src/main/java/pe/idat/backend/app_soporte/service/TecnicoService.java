package pe.idat.backend.app_soporte.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.idat.backend.app_soporte.exception.ResourceNotFoundException;
import pe.idat.backend.app_soporte.model.Tecnico;
import pe.idat.backend.app_soporte.repository.SolicitudRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TecnicoService {

    private final SolicitudRepository repository;

    public Tecnico crearTecnico(Tecnico tecnico) {
        return repository.guardarTecnico(tecnico);
    }

    public List<Tecnico> obtenerTodos() {
        return repository.obtenerTodosTecnicos();
    }

    public Tecnico obtenerPorId(Long id) {
        Tecnico tecnico = repository.obtenerTecnicoPorId(id);
        if (tecnico == null) {
            throw new ResourceNotFoundException("Técnico", id);
        }
        return tecnico;
    }

    public Tecnico actualizarTecnico(Long id, Tecnico tecnico) {
        Tecnico tecnicoExistente = obtenerPorId(id);

        tecnicoExistente.setNombre(tecnico.getNombre());
        tecnicoExistente.setApellido(tecnico.getApellido());
        tecnicoExistente.setEmail(tecnico.getEmail());
        tecnicoExistente.setEspecialidad(tecnico.getEspecialidad());
        tecnicoExistente.setDisponible(tecnico.isDisponible());

        return repository.guardarTecnico(tecnicoExistente);
    }

    public void eliminarTecnico(Long id) {
        if (!repository.existeTecnicoPorId(id)) {
            throw new ResourceNotFoundException("Técnico", id);
        }
        repository.eliminarTecnico(id);
    }
}