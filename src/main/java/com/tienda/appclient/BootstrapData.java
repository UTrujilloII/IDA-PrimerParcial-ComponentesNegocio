package com.tienda.appclient;

import com.tienda.appclient.model.Cliente;
import com.tienda.appclient.model.Solicitud;
import com.tienda.appclient.model.Tecnico;
import com.tienda.appclient.repository.ClienteRepository;
import com.tienda.appclient.repository.SolicitudRepository;
import com.tienda.appclient.repository.TecnicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class BootstrapData implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;
    private final SolicitudRepository solicitudRepository;

    public BootstrapData(ClienteRepository clienteRepository,
                         TecnicoRepository tecnicoRepository,
                         SolicitudRepository solicitudRepository) {
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cargar Clientes
        Cliente cliente1 = clienteRepository.save(new Cliente("12345678", "Perez", "Gomez", "Juan", LocalDate.of(1990, 5, 12)));
        Cliente cliente2 = clienteRepository.save(new Cliente("87654321", "Lopez", "Diaz", "María", LocalDate.of(1985, 8, 3)));
        Cliente cliente3 = clienteRepository.save(new Cliente("11223344", "Rodriguez", "Santos", "Carlos", LocalDate.of(1992, 3, 20)));

        System.out.println("Cargados " + clienteRepository.count() + " clientes");

        // Cargar Técnicos
        Tecnico tecnico1 = tecnicoRepository.save(new Tecnico("TEC-001", "Pedro", "Ramirez", "pedro.ramirez@soporte.com", "Redes"));
        Tecnico tecnico2 = tecnicoRepository.save(new Tecnico("TEC-002", "Ana", "Martinez", "ana.martinez@soporte.com", "Software"));
        Tecnico tecnico3 = tecnicoRepository.save(new Tecnico("TEC-003", "Luis", "Fernandez", "luis.fernandez@soporte.com", "Hardware"));

        System.out.println("Cargados " + tecnicoRepository.count() + " técnicos");

        // Cargar Solicitudes
        Solicitud sol1 = new Solicitud();
        sol1.setCodigo("SOL-2025-001");
        sol1.setAsunto("Problema con conexión a internet");
        sol1.setDescripcion("El cliente no puede conectarse a internet desde su computadora de escritorio");
        sol1.setCliente(cliente1);
        sol1.setPrioridad(Solicitud.PrioridadSolicitud.ALTA);
        sol1.setEstado(Solicitud.EstadoSolicitud.PENDIENTE);
        sol1.setFechaCreacion(LocalDateTime.now().minusDays(2));
        solicitudRepository.save(sol1);

        Solicitud sol2 = new Solicitud();
        sol2.setCodigo("SOL-2025-002");
        sol2.setAsunto("Instalación de software contable");
        sol2.setDescripcion("Necesita instalación y configuración del sistema contable");
        sol2.setCliente(cliente2);
        sol2.setTecnico(tecnico2);
        sol2.setPrioridad(Solicitud.PrioridadSolicitud.MEDIA);
        sol2.setEstado(Solicitud.EstadoSolicitud.ASIGNADA);
        sol2.setFechaCreacion(LocalDateTime.now().minusDays(1));
        sol2.setFechaAsignacion(LocalDateTime.now().minusHours(12));
        solicitudRepository.save(sol2);

        Solicitud sol3 = new Solicitud();
        sol3.setCodigo("SOL-2025-003");
        sol3.setAsunto("Computadora no enciende");
        sol3.setDescripcion("La computadora del área de ventas no enciende, posible problema de hardware");
        sol3.setCliente(cliente3);
        sol3.setTecnico(tecnico3);
        sol3.setPrioridad(Solicitud.PrioridadSolicitud.URGENTE);
        sol3.setEstado(Solicitud.EstadoSolicitud.EN_PROCESO);
        sol3.setFechaCreacion(LocalDateTime.now().minusHours(3));
        sol3.setFechaAsignacion(LocalDateTime.now().minusHours(2));
        solicitudRepository.save(sol3);

        Solicitud sol4 = new Solicitud();
        sol4.setCodigo("SOL-2025-004");
        sol4.setAsunto("Configuración de correo electrónico");
        sol4.setDescripcion("Necesita configurar el correo corporativo en dispositivo móvil");
        sol4.setCliente(cliente1);
        sol4.setPrioridad(Solicitud.PrioridadSolicitud.BAJA);
        sol4.setEstado(Solicitud.EstadoSolicitud.PENDIENTE);
        sol4.setFechaCreacion(LocalDateTime.now().minusHours(5));
        solicitudRepository.save(sol4);

        System.out.println("Cargadas " + solicitudRepository.count() + " solicitudes");
        System.out.println("=== Datos de ejemplo cargados exitosamente ===");
    }
}

