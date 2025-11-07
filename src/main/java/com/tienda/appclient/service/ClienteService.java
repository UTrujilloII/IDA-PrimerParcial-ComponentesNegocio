package com.tienda.appclient.service;

import com.tienda.appclient.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de clientes del sistema.
 * Define las operaciones de negocio disponibles para los clientes.
 *
 * Los clientes son las personas que solicitan soporte técnico.
 * Cada cliente tiene datos personales únicos como DNI.
 *
 * @author Equipo de Desarrollo
 * @version 1.0
 */
public interface ClienteService {

    /**
     * Crea y registra un nuevo cliente en el sistema.
     * Valida que el DNI sea único antes de crear el cliente.
     *
     * @param cliente Datos del cliente a crear
     * @return Cliente creado con su ID asignado
     * @throws DuplicateResourceException si el DNI ya está registrado
     */
    Cliente crear(Cliente cliente);

    /**
     * Busca un cliente específico por su identificador único.
     *
     * @param id Identificador único del cliente
     * @return Cliente encontrado
     * @throws ResourceNotFoundException si el cliente no existe
     */
    Cliente obtenerPorId(Integer id);

    /**
     * Busca un cliente por su DNI (Documento Nacional de Identidad).
     * El DNI es único para cada cliente.
     *
     * @param dni DNI del cliente a buscar
     * @return Optional con el cliente si existe, Optional vacío si no existe
     */
    Optional<Cliente> obtenerPorDni(String dni);

    /**
     * Obtiene la lista completa de todos los clientes registrados.
     *
     * @return Lista de todos los clientes
     */
    List<Cliente> listarTodos();

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param id Identificador del cliente a actualizar
     * @param cliente Nuevos datos del cliente
     * @return Cliente con los datos actualizados
     * @throws ResourceNotFoundException si el cliente no existe
     */
    Cliente actualizar(Integer id, Cliente cliente);

    /**
     * Elimina un cliente del sistema de forma permanente.
     *
     * @param id Identificador del cliente a eliminar
     * @throws ResourceNotFoundException si el cliente no existe
     */
    void eliminar(Integer id);
}

