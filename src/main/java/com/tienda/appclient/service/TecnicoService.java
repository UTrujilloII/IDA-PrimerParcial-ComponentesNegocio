package com.tienda.appclient.service;

import com.tienda.appclient.dto.TecnicoDTO;
import java.util.List;

/**
 * Interfaz de servicio para la gestión de técnicos de soporte.
 * Define las operaciones de negocio disponibles para los técnicos.
 *
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public interface TecnicoService {

    /**
     * Obtiene la lista completa de todos los técnicos registrados en el sistema.
     *
     * @return Lista de TecnicoDTO con todos los técnicos (activos e inactivos)
     */
    List<TecnicoDTO> findAll();

    /**
     * Busca un técnico específico por su identificador único.
     *
     * @param id Identificador único del técnico
     * @return TecnicoDTO con los datos del técnico encontrado
     * @throws ResourceNotFoundException si el técnico con el ID especificado no existe
     */
    TecnicoDTO findById(Integer id);

    /**
     * Busca un técnico por su código único (ejemplo: TEC-001).
     *
     * @param codigo Código único del técnico
     * @return TecnicoDTO con los datos del técnico encontrado
     * @throws ResourceNotFoundException si no existe un técnico con ese código
     */
    TecnicoDTO findByCodigo(String codigo);

    /**
     * Crea y registra un nuevo técnico en el sistema.
     * Realiza validaciones de unicidad para código y email.
     * Normaliza el email a minúsculas y elimina espacios del código.
     *
     * @param tecnicoDTO Datos del técnico a crear
     * @return TecnicoDTO del técnico creado con su ID asignado
     * @throws DuplicateResourceException si el código o email ya existen
     */
    TecnicoDTO create(TecnicoDTO tecnicoDTO);

    /**
     * Actualiza los datos de un técnico existente.
     * Valida que no se dupliquen código o email con otros técnicos.
     * Normaliza los datos antes de guardar.
     *
     * @param id Identificador del técnico a actualizar
     * @param tecnicoDTO Nuevos datos del técnico
     * @return TecnicoDTO con los datos actualizados
     * @throws ResourceNotFoundException si el técnico no existe
     * @throws DuplicateResourceException si el código o email ya pertenecen a otro técnico
     */
    TecnicoDTO update(Integer id, TecnicoDTO tecnicoDTO);

    /**
     * Elimina un técnico del sistema de forma permanente.
     *
     * @param id Identificador del técnico a eliminar
     * @throws ResourceNotFoundException si el técnico no existe
     */
    void delete(Integer id);

    /**
     * Obtiene la lista de técnicos filtrados por su estado (activo/inactivo).
     * Los técnicos activos son aquellos disponibles para asignación de solicitudes.
     *
     * @param activo true para técnicos activos, false para inactivos
     * @return Lista de TecnicoDTO que coinciden con el estado especificado
     */
    List<TecnicoDTO> findByActivo(Boolean activo);

    /**
     * Busca técnicos por su área de especialidad.
     * Útil para asignar solicitudes según la especialización requerida.
     *
     * @param especialidad Área de especialidad (ej: "Software", "Hardware", "Redes")
     * @return Lista de TecnicoDTO que tienen la especialidad especificada
     */
    List<TecnicoDTO> findByEspecialidad(String especialidad);
}



