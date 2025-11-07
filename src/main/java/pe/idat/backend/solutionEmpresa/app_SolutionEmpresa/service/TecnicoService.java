package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.service;

import java.util.List;
import java.util.Optional;
import pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model.Tecnico;

public interface TecnicoService {
    Tecnico guardarTecnico(Tecnico tecnico);

    Optional<Tecnico> buscarTecnicoPorId(Integer id);

    List<Tecnico> listarTecnicos();

    void eliminarTecnicoPorId(Integer id);

    boolean existeTecnicoPorId(Integer id);
}
