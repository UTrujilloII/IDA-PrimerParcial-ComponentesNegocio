package idat.eva1.repository;

import idat.eva1.model.Tecnico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class tecnicoRepository {

    private final Map<Integer, Tecnico> tecnicos = new HashMap<>();
    private final AtomicInteger secuencial = new AtomicInteger(0);

    //crea
    public Tecnico registrarTecnico(Tecnico tecnico)
    {
        if (tecnico.getIdTecnico() == null)
        {
            tecnico.setIdTecnico(secuencial.incrementAndGet());
        }
        tecnicos.put(tecnico.getIdTecnico(), tecnico);
        return tecnico;
    }

    //lee
    public List<Tecnico> listarTecnico() {
        return new ArrayList<>(tecnicos.values());
    }

    //lee/busca por id
    public Tecnico buscarTecnicoPorId(Integer id) {
        return tecnicos.get(id);
    }

    //actualiza
    public Tecnico actualizarTecnico(Integer id, Tecnico tecnicoActualizado) {
        Tecnico existe = tecnicos.get(id);
        if (existe != null) {
            existe.setDniTecnico(tecnicoActualizado.getDniTecnico());
            existe.setApellidoPaternoTecnico(tecnicoActualizado.getApellidoPaternoTecnico());
            existe.setApellidoMaternoTecnico(tecnicoActualizado.getApellidoMaternoTecnico());
            existe.setNombreTecnico(tecnicoActualizado.getNombreTecnico());
            existe.setEmail(tecnicoActualizado.getEmail());
        }
        return existe;
    }

    //elimina
    public boolean eliminarTecnico(Integer id) {
        return tecnicos.remove(id) != null;
    }
}
