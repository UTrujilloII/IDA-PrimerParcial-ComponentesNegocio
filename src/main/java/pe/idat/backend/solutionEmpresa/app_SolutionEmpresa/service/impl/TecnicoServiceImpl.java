package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.BadRequestException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception.ResourceNotFoundException;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Tecnico;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.repository.TecnicoRepository;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service.TecnicoService;

@Service
public class TecnicoServiceImpl implements TecnicoService {
    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    public Tecnico guardarTecnico(Tecnico tecnico) {
        if (tecnico == null) {
            throw new BadRequestException("El técnico no puede ser nulo.");
        } else if (tecnico.getNombre() != null && !tecnico.getNombre().trim().isEmpty()) {
            String especialidad = tecnico.getEspecialidad();
            if (especialidad != null && !especialidad.trim().isEmpty()) {
                if (!especialidad.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{3,}$")) {
                    throw new BadRequestException("Ingrese una especialidad válida y existente (solo letras, mínimo 3 caracteres).");
                } else {
                    return this.tecnicoRepository.guardar(tecnico);
                }
            } else {
                throw new BadRequestException("La especialidad del técnico es obligatoria.");
            }
        } else {
            throw new BadRequestException("El nombre del técnico es obligatorio.");
        }
    }

    public Optional<Tecnico> buscarTecnicoPorId(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del técnico no puede ser nulo al buscar.");
        } else {
            return this.tecnicoRepository.buscarPorId(id);
        }
    }

    public Tecnico obtenerTecnicoPorIdOError(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del técnico no puede ser nulo.");
        } else {
            return (Tecnico)this.tecnicoRepository.buscarPorId(id).orElseThrow(() -> new ResourceNotFoundException("Técnico no encontrado con ID: " + id));
        }
    }

    public List<Tecnico> listarTecnicos() {
        return this.tecnicoRepository.listarTodos();
    }

    public void eliminarTecnicoPorId(Integer id) {
        if (id == null) {
            throw new BadRequestException("El ID del técnico no puede ser nulo para eliminar.");
        } else if (!this.tecnicoRepository.existePorId(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: técnico no encontrado con ID " + id);
        } else {
            this.tecnicoRepository.eliminarPorId(id);
        }
    }

    public boolean existeTecnicoPorId(Integer id) {
        return id == null ? false : this.tecnicoRepository.existePorId(id);
    }
}
