package idat.eva1.repository;


import idat.eva1.model.Solicitud;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class solicitudRepository {

    private final Map<Integer, Solicitud> solicitudes = new HashMap<>();
    private final AtomicInteger secuencial = new AtomicInteger(0);

    //crear/registrar
    public Solicitud registrarSolicitud(Solicitud solicitud)
    {
        if (solicitud.getIdSolicitud() == null)
        {
            solicitud.setIdSolicitud(secuencial.incrementAndGet());
        }
        solicitudes.put(solicitud.getIdSolicitud(), solicitud);
        return solicitud;
    }

    //lee
    public List<Solicitud> listarSolicitud() {
        return new ArrayList<>(solicitudes.values());
    }

    //busca por id
    public Solicitud buscarSolicitudPorId(Integer id) {
        return solicitudes.get(id);
    }

    //actualiza
    public Solicitud actualizarSolicitudPorId(Integer id, Solicitud solicitudActualizada) {
        Solicitud existe = solicitudes.get(solicitudActualizada.getIdSolicitud());
        if (existe != null)
        {
            existe.setIdCliente(solicitudActualizada.getIdCliente());
            existe.setIdTecnico(solicitudActualizada.getIdTecnico());
            existe.setDescripcion(solicitudActualizada.getDescripcion());
            existe.setFechaRegistro(solicitudActualizada.getFechaRegistro());
        }

        return existe;
    }

    //elimina
    public void eliminarSolicitud(Integer id)
    {
        solicitudes.remove(id);
    }
}
