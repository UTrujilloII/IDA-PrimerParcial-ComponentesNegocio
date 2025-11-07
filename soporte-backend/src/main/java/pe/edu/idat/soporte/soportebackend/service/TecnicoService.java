package pe.edu.idat.soporte.soportebackend.service;

import org.springframework.stereotype.Service;
import pe.edu.idat.soporte.soportebackend.dto.TecnicoDTO;
import pe.edu.idat.soporte.soportebackend.exceptions.RecursoNoEncontradoException;
import pe.edu.idat.soporte.soportebackend.model.Tecnico;
import pe.edu.idat.soporte.soportebackend.repository.TecnicoRepository;

import java.util.List;

@Service
public class TecnicoService implements ITecnicoService{
    private final TecnicoRepository tecnicoRepo;

    public TecnicoService(TecnicoRepository tecnicoRepo) {
        this.tecnicoRepo = tecnicoRepo;
    }

    @Override
    public List<Tecnico> obtenerTodos() {
        return this.tecnicoRepo.buscarTodo();
    }

    @Override
    public void registrar(TecnicoDTO dto) {
        Tecnico nuevoTecnico = Tecnico.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .especialidad(dto.getEspecialidad())
                .build();

        this.tecnicoRepo.guardar(nuevoTecnico);
    }

    @Override
    public Tecnico consultarPorId(Integer id) {
        return this.tecnicoRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con el id: " + id));
    }

    @Override
    public void actualizar(Integer id, TecnicoDTO dto) {
        Tecnico existeTecnico = this.tecnicoRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con el id: " + id));

        existeTecnico.setNombre(dto.getNombre());
        existeTecnico.setApellido(dto.getApellido());
        existeTecnico.setEmail(dto.getEmail());
        existeTecnico.setTelefono(dto.getTelefono());
        existeTecnico.setEspecialidad(dto.getEspecialidad());

        this.tecnicoRepo.actualizar(existeTecnico);
    }

    @Override
    public void eliminar(Integer id) {
        this.tecnicoRepo.buscarPorId(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Técnico no encontrado con el id: " + id));
        this.tecnicoRepo.eliminar(id);
    }
}
