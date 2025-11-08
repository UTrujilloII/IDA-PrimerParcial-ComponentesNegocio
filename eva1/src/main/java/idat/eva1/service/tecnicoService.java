package idat.eva1.service;


import idat.eva1.dto.tecnicoDTO;
import idat.eva1.exception.NotFoundException;
import idat.eva1.model.Tecnico;
import idat.eva1.repository.tecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tecnicoService {

    //comunica con repository
    private final tecnicoRepository tecnicoRepository;
    public tecnicoService(tecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;

    }

    //crear
    public tecnicoDTO registrarTecnico(tecnicoDTO dto) {
        Tecnico tecnico = new Tecnico();
        tecnico.setDniTecnico(dto.getDniTecnico());
        tecnico.setApellidoPaternoTecnico(dto.getApellidoPaternoTecnico());
        tecnico.setApellidoMaternoTecnico(dto.getApellidoMaternoTecnico());
        tecnico.setNombreTecnico(dto.getNombreTecnico());
        tecnico.setEmail(dto.getEmail());

        Tecnico nuevo = tecnicoRepository.registrarTecnico(tecnico);
        tecnicoDTO respuesta = new tecnicoDTO();
        respuesta.setDniTecnico(nuevo.getDniTecnico());
        respuesta.setApellidoPaternoTecnico(nuevo.getApellidoPaternoTecnico());
        respuesta.setApellidoMaternoTecnico(nuevo.getApellidoMaternoTecnico());
        respuesta.setNombreTecnico(nuevo.getNombreTecnico());
        respuesta.setEmail(nuevo.getEmail());
        return respuesta;
    }

    //listar todos
    public List<Tecnico> listarTecnico() {
        return tecnicoRepository.listarTecnico();
    }

    //buscar por id
    public Tecnico buscarTecnicoPorId(Integer id)
    {
        Tecnico tecnico = tecnicoRepository.buscarTecnicoPorId(id);
        if(tecnico == null){
            throw new NotFoundException("ERROR: cliente " + id + " no encontrado");
        }
        return tecnico;
    }

    //actualizar
    public Tecnico actualizarTecnico(Integer id, Tecnico tecnicoActualizado) {
        return tecnicoRepository.actualizarTecnico(id, tecnicoActualizado);
    }
    public tecnicoDTO actualizarTecnico(Integer id, tecnicoDTO dto) {
        Tecnico existente = tecnicoRepository.buscarTecnicoPorId(id);
        if (existente == null) {
            throw new NotFoundException("Error: El tecnico " + id + " no existe");
        }

        existente.setDniTecnico(dto.getDniTecnico());
        existente.setApellidoPaternoTecnico(dto.getApellidoPaternoTecnico());
        existente.setApellidoMaternoTecnico(dto.getApellidoMaternoTecnico());
        existente.setNombreTecnico(dto.getNombreTecnico());
        existente.setEmail(dto.getEmail());
        tecnicoRepository.actualizarTecnico(id, existente);

        tecnicoDTO actualizado = new tecnicoDTO();
        actualizado.setDniTecnico(existente.getDniTecnico());
        actualizado.setApellidoPaternoTecnico(existente.getApellidoPaternoTecnico());
        actualizado.setApellidoMaternoTecnico(existente.getApellidoMaternoTecnico());
        actualizado.setNombreTecnico(existente.getNombreTecnico());
        actualizado.setEmail(existente.getEmail());
        return actualizado;
    }

    //eliminar
    public void eliminarTecnico(Integer id)
    {
        boolean eliminado = tecnicoRepository.eliminarTecnico(id);
        if(!eliminado){
            throw new NotFoundException("Error: Cliente " + id + " no encontrado");
        }
    }
}
