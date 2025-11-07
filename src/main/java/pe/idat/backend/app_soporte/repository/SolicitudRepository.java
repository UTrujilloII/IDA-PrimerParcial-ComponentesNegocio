package pe.idat.backend.app_soporte.repository;

import org.springframework.stereotype.Repository;
import pe.idat.backend.app_soporte.model.Cliente;
import pe.idat.backend.app_soporte.model.Solicitud;
import pe.idat.backend.app_soporte.model.Tecnico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class SolicitudRepository {

    // Datos privados - ENCAPSULAMIENTO
    private final Map<Long, Solicitud> solicitudes = new HashMap<>();
    private final Map<Long, Cliente> clientes = new HashMap<>();
    private final Map<Long, Tecnico> tecnicos = new HashMap<>();
    private final AtomicLong solicitudIdCounter = new AtomicLong(1);
    private final AtomicLong clienteIdCounter = new AtomicLong(1);
    private final AtomicLong tecnicoIdCounter = new AtomicLong(1);

    public SolicitudRepository() {
        cargarDatosIniciales();
    }

    // Método privado - solo usado internamente
    private void cargarDatosIniciales() {
        // Cargar clientes de ejemplo
        Cliente cliente1 = new Cliente(clienteIdCounter.getAndIncrement(),
                "Juan", "Pérez", "juan.perez@email.com", "987654321", "TechCorp");
        Cliente cliente2 = new Cliente(clienteIdCounter.getAndIncrement(),
                "María", "García", "maria.garcia@email.com", "987654322", "Innovatech");

        clientes.put(cliente1.getId(), cliente1);
        clientes.put(cliente2.getId(), cliente2);

        // Cargar técnicos de ejemplo
        Tecnico tecnico1 = new Tecnico(tecnicoIdCounter.getAndIncrement(),
                "Carlos", "Rodríguez", "carlos.rodriguez@soporte.com", "Software");
        Tecnico tecnico2 = new Tecnico(tecnicoIdCounter.getAndIncrement(),
                "Ana", "López", "ana.lopez@soporte.com", "Hardware");

        tecnicos.put(tecnico1.getId(), tecnico1);
        tecnicos.put(tecnico2.getId(), tecnico2);

        // Cargar solicitudes de ejemplo
        Solicitud solicitud1 = new Solicitud(solicitudIdCounter.getAndIncrement(),
                "Error en sistema de facturación",
                "El sistema no genera facturas correctamente desde ayer",
                cliente1, "ALTA", "PENDIENTE");
        solicitud1.setTecnicoAsignado(tecnico1);

        Solicitud solicitud2 = new Solicitud(solicitudIdCounter.getAndIncrement(),
                "Computadora no enciende",
                "La computadora del área de ventas no enciende",
                cliente2, "MEDIA", "EN_PROCESO");
        solicitud2.setTecnicoAsignado(tecnico2);

        solicitudes.put(solicitud1.getId(), solicitud1);
        solicitudes.put(solicitud2.getId(), solicitud2);
    }

    // ========== MÉTODOS PÚBLICOS PARA SOLICITUDES ==========

    public Solicitud guardar(Solicitud solicitud) {
        if (solicitud.getId() == null) {
            solicitud.setId(solicitudIdCounter.getAndIncrement());
        }
        solicitudes.put(solicitud.getId(), solicitud);
        return solicitud;
    }

    public List<Solicitud> obtenerTodas() {
        return new ArrayList<>(solicitudes.values());
    }

    public Solicitud obtenerPorId(Long id) {
        return solicitudes.get(id);
    }

    public void eliminar(Long id) {
        solicitudes.remove(id);
    }

    public boolean existePorId(Long id) {
        return solicitudes.containsKey(id);
    }

    // ========== MÉTODOS PÚBLICOS PARA CLIENTES ==========

    public Cliente guardarCliente(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(clienteIdCounter.getAndIncrement());
        }
        clientes.put(cliente.getId(), cliente);
        return cliente;
    }

    public List<Cliente> obtenerTodosClientes() {
        return new ArrayList<>(clientes.values());
    }

    public Cliente obtenerClientePorId(Long id) {
        return clientes.get(id);
    }

    public void eliminarCliente(Long id) {
        clientes.remove(id);
    }

    public boolean existeClientePorId(Long id) {
        return clientes.containsKey(id);
    }

    // ========== MÉTODOS PÚBLICOS PARA TÉCNICOS ==========

    public Tecnico guardarTecnico(Tecnico tecnico) {
        if (tecnico.getId() == null) {
            tecnico.setId(tecnicoIdCounter.getAndIncrement());
        }
        tecnicos.put(tecnico.getId(), tecnico);
        return tecnico;
    }

    public List<Tecnico> obtenerTodosTecnicos() {
        return new ArrayList<>(tecnicos.values());
    }

    public Tecnico obtenerTecnicoPorId(Long id) {
        return tecnicos.get(id);
    }

    public void eliminarTecnico(Long id) {
        tecnicos.remove(id);
    }

    public boolean existeTecnicoPorId(Long id) {
        return tecnicos.containsKey(id);
    }
}