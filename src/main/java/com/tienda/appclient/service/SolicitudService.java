package com.tienda.appclient.service;

import com.tienda.appclient.dto.SolicitudDTO;
import com.tienda.appclient.model.Solicitud;
import java.util.List;

/**
 * Interfaz de servicio para la gestión de solicitudes de soporte técnico.
 * Define las operaciones de negocio disponibles para las solicitudes.
 *
 * Una solicitud representa un ticket de soporte técnico que:
 * - Es creada por un cliente
 * - Puede ser asignada a un técnico
 * - Pasa por diferentes estados (PENDIENTE, ASIGNADA, EN_PROCESO, RESUELTA, etc.)
 * - Tiene una prioridad (BAJA, MEDIA, ALTA, URGENTE)
 *
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public interface SolicitudService {

    /**
     * Obtiene la lista completa de todas las solicitudes registradas.
     *
     * @return Lista de SolicitudDTO con todas las solicitudes
     */
    List<SolicitudDTO> findAll();

    /**
     * Busca una solicitud específica por su identificador único.
     *
     * @param id Identificador único de la solicitud
     * @return SolicitudDTO con los datos de la solicitud
     * @throws ResourceNotFoundException si la solicitud no existe
     */
    SolicitudDTO findById(Integer id);

    /**
     * Busca una solicitud por su código único (ejemplo: SOL-001).
     *
     * @param codigo Código único de la solicitud
     * @return SolicitudDTO con los datos de la solicitud
     * @throws ResourceNotFoundException si no existe una solicitud con ese código
     */
    SolicitudDTO findByCodigo(String codigo);

    /**
     * Crea y registra una nueva solicitud de soporte técnico.
     * Valida que el código sea único y que el cliente exista.
     * Establece valores por defecto si no se proporcionan:
     * - Estado: PENDIENTE
     * - Prioridad: MEDIA
     * - Fecha de creación: fecha actual
     *
     * @param solicitudDTO Datos de la solicitud a crear
     * @return SolicitudDTO de la solicitud creada con su ID asignado
     * @throws DuplicateResourceException si el código ya existe
     * @throws ResourceNotFoundException si el cliente o técnico no existen
     */
    SolicitudDTO create(SolicitudDTO solicitudDTO);

    /**
     * Actualiza los datos de una solicitud existente.
     * Permite modificar asunto, descripción, estado, prioridad, etc.
     * Valida que el código no se duplique con otras solicitudes.
     *
     * @param id Identificador de la solicitud a actualizar
     * @param solicitudDTO Nuevos datos de la solicitud
     * @return SolicitudDTO con los datos actualizados
     * @throws ResourceNotFoundException si la solicitud, cliente o técnico no existen
     * @throws DuplicateResourceException si el nuevo código ya existe
     */
    SolicitudDTO update(Integer id, SolicitudDTO solicitudDTO);

    /**
     * Elimina una solicitud del sistema de forma permanente.
     *
     * @param id Identificador de la solicitud a eliminar
     * @throws ResourceNotFoundException si la solicitud no existe
     */
    void delete(Integer id);

    /**
     * Filtra solicitudes por su estado actual.
     * Estados posibles: PENDIENTE, ASIGNADA, EN_PROCESO, RESUELTA, CERRADA, CANCELADA
     *
     * @param estado Estado de las solicitudes a buscar
     * @return Lista de solicitudes que tienen el estado especificado
     */
    List<SolicitudDTO> findByEstado(Solicitud.EstadoSolicitud estado);

    /**
     * Filtra solicitudes por su nivel de prioridad.
     * Prioridades: BAJA, MEDIA, ALTA, URGENTE
     *
     * @param prioridad Nivel de prioridad de las solicitudes a buscar
     * @return Lista de solicitudes con la prioridad especificada
     */
    List<SolicitudDTO> findByPrioridad(Solicitud.PrioridadSolicitud prioridad);

    /**
     * Obtiene todas las solicitudes de un cliente específico.
     * Útil para ver el historial de solicitudes de un cliente.
     *
     * @param clienteId Identificador del cliente
     * @return Lista de solicitudes del cliente
     * @throws ResourceNotFoundException si el cliente no existe
     */
    List<SolicitudDTO> findByCliente(Integer clienteId);

    /**
     * Obtiene todas las solicitudes asignadas a un técnico específico.
     * Útil para ver la carga de trabajo de un técnico.
     *
     * @param tecnicoId Identificador del técnico
     * @return Lista de solicitudes asignadas al técnico
     * @throws ResourceNotFoundException si el técnico no existe
     */
    List<SolicitudDTO> findByTecnico(Integer tecnicoId);

    /**
     * Obtiene todas las solicitudes que aún no tienen técnico asignado.
     * Útil para identificar solicitudes pendientes de asignación.
     *
     * @return Lista de solicitudes sin técnico asignado
     */
    List<SolicitudDTO> findSinAsignar();

    /**
     * Asigna un técnico a una solicitud específica.
     * Valida que el técnico esté activo antes de asignar.
     * Registra la fecha de asignación automáticamente.
     * Si la solicitud está PENDIENTE, cambia su estado a ASIGNADA.
     *
     * @param solicitudId Identificador de la solicitud
     * @param tecnicoId Identificador del técnico a asignar
     * @return SolicitudDTO actualizada con el técnico asignado
     * @throws ResourceNotFoundException si la solicitud o técnico no existen
     * @throws BusinessRuleException si el técnico no está activo
     */
    SolicitudDTO asignarTecnico(Integer solicitudId, Integer tecnicoId);

    /**
     * Cambia el estado de una solicitud.
     * Estados: PENDIENTE → ASIGNADA → EN_PROCESO → RESUELTA → CERRADA
     * También puede ser CANCELADA en cualquier momento.
     * Si el nuevo estado es RESUELTA, registra la fecha de resolución.
     *
     * @param solicitudId Identificador de la solicitud
     * @param nuevoEstado Nuevo estado a establecer
     * @return SolicitudDTO con el estado actualizado
     * @throws ResourceNotFoundException si la solicitud no existe
     */
    SolicitudDTO cambiarEstado(Integer solicitudId, Solicitud.EstadoSolicitud nuevoEstado);
}

