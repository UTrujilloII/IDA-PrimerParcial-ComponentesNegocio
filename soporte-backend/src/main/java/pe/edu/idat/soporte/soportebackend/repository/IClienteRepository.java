package pe.edu.idat.soporte.soportebackend.repository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.soporte.soportebackend.model.Cliente;
import pe.edu.idat.soporte.soportebackend.model.Solicitud;
import pe.edu.idat.soporte.soportebackend.model.Tecnico;

import java.util.List;
import java.util.Optional;


public interface IClienteRepository extends IGenericRepository<Cliente>{

//    Optional<Cliente> buscarPorId(Integer id);

}
