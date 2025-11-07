package com.tienda.appclient.dto;

import com.tienda.appclient.model.Solicitud;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class SolicitudDTO {

    private Integer id;

    @NotBlank(message = "Código es obligatorio")
    @Size(min = 5, max = 30, message = "Código debe tener entre 5 y 30 caracteres")
    private String codigo;

    @NotBlank(message = "Asunto es obligatorio")
    @Size(min = 5, max = 200, message = "Asunto debe tener entre 5 y 200 caracteres")
    private String asunto;

    @NotBlank(message = "Descripción es obligatoria")
    @Size(min = 10, max = 2000, message = "Descripción debe tener entre 10 y 2000 caracteres")
    private String descripcion;

    @NotNull(message = "Cliente ID es obligatorio")
    private Integer clienteId;

    private Integer tecnicoId;

    @NotNull(message = "Estado es obligatorio")
    private Solicitud.EstadoSolicitud estado;

    @NotNull(message = "Prioridad es obligatoria")
    private Solicitud.PrioridadSolicitud prioridad;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaAsignacion;

    private LocalDateTime fechaResolucion;

    @Size(max = 1000, message = "Observaciones demasiado largas")
    private String observaciones;

    // Para respuestas que incluyen info del cliente y técnico
    private ClienteDTO cliente;
    private TecnicoDTO tecnico;

    // Constructors
    public SolicitudDTO() {
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

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Integer tecnicoId) {
        this.tecnicoId = tecnicoId;
    }

    public Solicitud.EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(Solicitud.EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Solicitud.PrioridadSolicitud getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Solicitud.PrioridadSolicitud prioridad) {
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public TecnicoDTO getTecnico() {
        return tecnico;
    }

    public void setTecnico(TecnicoDTO tecnico) {
        this.tecnico = tecnico;
    }
}

