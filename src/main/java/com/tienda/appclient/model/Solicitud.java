package com.tienda.appclient.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una solicitud de soporte técnico.
 * Encapsula toda la información sobre una petición de ayuda técnica,
 * incluyendo el cliente que la solicita, el técnico asignado, y el estado del proceso.
 *
 * @author Equipo Backend
 * @version 1.0
 */
@Entity
@Table(name = "solicitudes")
public class Solicitud {

    /**
     * Identificador único de la solicitud (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Código único de la solicitud (ej: SOL-2025-001).
     */
    @Column(nullable = false, unique = true)
    private String codigo;

    /**
     * Asunto breve de la solicitud.
     */
    @Column(nullable = false)
    private String asunto;

    /**
     * Descripción detallada del problema o requerimiento.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Cliente que genera la solicitud.
     */
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    /**
     * Técnico asignado a la solicitud (puede ser null si no está asignada).
     */
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    /**
     * Estado actual de la solicitud.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

    /**
     * Nivel de prioridad de la solicitud.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PrioridadSolicitud prioridad = PrioridadSolicitud.MEDIA;

    /**
     * Fecha y hora de creación de la solicitud.
     */
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    /**
     * Fecha y hora de asignación a un técnico.
     */
    @Column(name = "fecha_asignacion")
    private LocalDateTime fechaAsignacion;

    /**
     * Fecha y hora de resolución de la solicitud.
     */
    @Column(name = "fecha_resolucion")
    private LocalDateTime fechaResolucion;

    /**
     * Observaciones adicionales sobre la solicitud.
     */
    @Column(columnDefinition = "TEXT")
    private String observaciones;

    /**
     * Constructor por defecto que inicializa la fecha de creación.
     */
    public Solicitud() {
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor para crear una solicitud con datos básicos.
     *
     * @param codigo Código único de la solicitud
     * @param asunto Asunto de la solicitud
     * @param descripcion Descripción detallada
     * @param cliente Cliente que solicita
     * @param prioridad Nivel de prioridad
     */
    public Solicitud(String codigo, String asunto, String descripcion, Cliente cliente, PrioridadSolicitud prioridad) {
        this.codigo = codigo;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.prioridad = prioridad != null ? prioridad : PrioridadSolicitud.MEDIA;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Enumeración de los posibles estados de una solicitud.
     */
    public enum EstadoSolicitud {
        /** Solicitud recién creada, sin asignar */
        PENDIENTE,
        /** Solicitud asignada a un técnico */
        ASIGNADA,
        /** Técnico trabajando en la solicitud */
        EN_PROCESO,
        /** Problema resuelto */
        RESUELTA,
        /** Solicitud cerrada definitivamente */
        CERRADA,
        /** Solicitud cancelada por el cliente */
        CANCELADA
    }

    /**
     * Enumeración de los niveles de prioridad.
     */
    public enum PrioridadSolicitud {
        /** Prioridad baja, no urgente */
        BAJA,
        /** Prioridad media, atención normal */
        MEDIA,
        /** Prioridad alta, requiere atención pronto */
        ALTA,
        /** Prioridad urgente, atención inmediata */
        URGENTE
    }

    // Métodos de negocio

    /**
     * Asigna un técnico a esta solicitud y actualiza el estado.
     *
     * @param tecnico Técnico a asignar
     * @throws IllegalStateException si la solicitud ya está cerrada o cancelada
     */
    public void asignarTecnico(Tecnico tecnico) {
        if (this.estado == EstadoSolicitud.CERRADA || this.estado == EstadoSolicitud.CANCELADA) {
            throw new IllegalStateException("No se puede asignar técnico a una solicitud cerrada o cancelada");
        }
        this.tecnico = tecnico;
        this.fechaAsignacion = LocalDateTime.now();
        if (this.estado == EstadoSolicitud.PENDIENTE) {
            this.estado = EstadoSolicitud.ASIGNADA;
        }
    }

    /**
     * Marca la solicitud como resuelta.
     *
     * @throws IllegalStateException si la solicitud no tiene técnico asignado
     */
    public void marcarComoResuelta() {
        if (this.tecnico == null) {
            throw new IllegalStateException("No se puede resolver una solicitud sin técnico asignado");
        }
        this.estado = EstadoSolicitud.RESUELTA;
        this.fechaResolucion = LocalDateTime.now();
    }

    /**
     * Verifica si la solicitud está pendiente de asignación.
     *
     * @return true si está pendiente, false en caso contrario
     */
    public boolean estaPendiente() {
        return this.estado == EstadoSolicitud.PENDIENTE;
    }

    /**
     * Verifica si la solicitud ha sido resuelta.
     *
     * @return true si está resuelta, false en caso contrario
     */
    public boolean estaResuelta() {
        return this.estado == EstadoSolicitud.RESUELTA || this.estado == EstadoSolicitud.CERRADA;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadSolicitud prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public LocalDateTime getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDateTime fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Compara esta solicitud con otro objeto para determinar igualdad.
     * Dos solicitudes son iguales si tienen el mismo código.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(codigo, solicitud.codigo);
    }

    /**
     * Genera el código hash de la solicitud basado en su código.
     *
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Genera una representación en texto de la solicitud.
     *
     * @return String con los datos de la solicitud
     */
    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", asunto='" + asunto + '\'' +
                ", estado=" + estado +
                ", prioridad=" + prioridad +
                ", cliente=" + (cliente != null ? cliente.getNombres() : "null") +
                ", tecnico=" + (tecnico != null ? tecnico.getNombres() : "sin asignar") +
                '}';
    }
}

