package pe.edu.idat.soporte.soportebackend.repository;

import org.springframework.stereotype.Repository;

import pe.edu.idat.soporte.soportebackend.model.Solicitud;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SolicitudRepository implements ISolicitudRepository{
    private final Map<Integer,Solicitud> bdSolicitud=new HashMap<>();
    private final AtomicInteger secuencial =new AtomicInteger(0);


    @Override
    public List<Solicitud> buscarTodo() {
        return new ArrayList<>(bdSolicitud.values());
    }

    @Override
    public void guardar(Solicitud solicitud) {
        if(solicitud.getIdSolicitud()==null){
            solicitud.setIdSolicitud(secuencial.incrementAndGet());
        }
        bdSolicitud.put(solicitud.getIdSolicitud(), solicitud);
    }

    @Override
    public Optional<Solicitud> buscarPorId(Integer id) {
        return Optional.ofNullable(bdSolicitud.get(id));
    }

    @Override
    public void actualizar(Solicitud solicitud) {
        if(bdSolicitud.containsKey(solicitud.getIdSolicitud())){
            bdSolicitud.put(solicitud.getIdSolicitud(),solicitud);
        }
    }

    @Override
    public void eliminar(Integer id) {
        bdSolicitud.remove(id);
    }
}
