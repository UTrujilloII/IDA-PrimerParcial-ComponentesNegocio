package pe.edu.idat.soporte.soportebackend.service;
import org.springframework.stereotype.Service;
import pe.edu.idat.soporte.soportebackend.dto.SolicitudDTO;
import pe.edu.idat.soporte.soportebackend.exceptions.RecursoNoEncontradoException;
import pe.edu.idat.soporte.soportebackend.model.Cliente;
import pe.edu.idat.soporte.soportebackend.model.EstadoSolicitud;
import pe.edu.idat.soporte.soportebackend.model.Solicitud;

import pe.edu.idat.soporte.soportebackend.model.Tecnico;
import pe.edu.idat.soporte.soportebackend.repository.ClienteRepository;
import pe.edu.idat.soporte.soportebackend.repository.SolicitudRepository;
import pe.edu.idat.soporte.soportebackend.repository.TecnicoRepository;


import java.time.LocalDate;
import java.util.List;


@Service
public class SolicitudService implements ISolicitudService{

    private final SolicitudRepository SolicitudRepo;
    private final TecnicoRepository TecnicoRepo;
    private final ClienteRepository ClienteRepo;

    public SolicitudService(SolicitudRepository SolicitudRepo, TecnicoRepository TecnicoRepo, ClienteRepository ClienteRepo) {
        this.SolicitudRepo = SolicitudRepo;
        this.TecnicoRepo = TecnicoRepo;
        this.ClienteRepo = ClienteRepo;
    }

    @Override
    public List<Solicitud> obtenerTodos() {
        return this.SolicitudRepo.buscarTodo();
    }

    @Override
    public void registrar(SolicitudDTO solicituddto) {

        Tecnico t1=TecnicoRepo.buscarPorId(solicituddto.getIdTecnico()).orElseThrow(()->new RecursoNoEncontradoException("tecnico no encontrado"));
        Cliente c1=ClienteRepo.buscarPorId(solicituddto.getIdCliente()).orElseThrow(()->new RecursoNoEncontradoException("cliente no encontrado"));

        Solicitud s =Solicitud.builder()
                .titulo(solicituddto.getTitulo())
                .descripcion(solicituddto.getDescripcion())
                .cliente(c1)
                .tecnico(t1)
                .fechaCreacion(LocalDate.now())
                .estado(EstadoSolicitud.PENDIENTE)
                .build();
        this.SolicitudRepo.guardar(s);
    }

    @Override
    public Solicitud consultarPorId(Integer id) {
        return this.SolicitudRepo.buscarPorId(id).orElseThrow(()-> new RecursoNoEncontradoException("solicitud no encontrada con el id: " + id));
    }

    @Override
    public void actualizar(Integer id,SolicitudDTO solicituddto) {
        Solicitud existeSolicitud=this.SolicitudRepo.buscarPorId(id).orElseThrow(()-> new RecursoNoEncontradoException("solicitud no encontrada"));
        Tecnico t1 = this.TecnicoRepo.buscarPorId(solicituddto.getIdTecnico()).orElseThrow(() -> new RecursoNoEncontradoException("tecnico no encontrado"));
        Cliente c1 = this.ClienteRepo.buscarPorId(solicituddto.getIdCliente()).orElseThrow(() -> new RecursoNoEncontradoException("cliente no encontrado"));

        existeSolicitud.setTitulo(solicituddto.getTitulo());
        existeSolicitud.setDescripcion(solicituddto.getDescripcion());
        existeSolicitud.setCliente(c1);
        existeSolicitud.setTecnico(t1);
        this.SolicitudRepo.actualizar(existeSolicitud);
    }

    @Override
    public void eliminar(Integer id) {
        this.SolicitudRepo.buscarPorId(id).orElseThrow(()-> new RecursoNoEncontradoException("solicitud no encontrada"));
        this.SolicitudRepo.eliminar(id);
    }
}
