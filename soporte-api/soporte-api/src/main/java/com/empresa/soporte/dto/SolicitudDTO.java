package com.empresa.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Schema(description = "Datos de transferencia para una solicitud de soporte")
public class SolicitudDTO {

    @Schema(description = "Identificador único de la solicitud", example = "1")
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El título solo puede contener letras y espacios")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El cliente es obligatorio")
    @Valid
    private ClienteDTO cliente;

    @NotNull(message = "El técnico es obligatorio")
    @Valid
    private TecnicoDTO tecnico;

    @Pattern(regexp = "^(Pendiente|En proceso|Finalizado|Cancelado)$",
            message = "El estado debe ser: Pendiente, En proceso, Finalizado o Cancelado")
    private String estado = "Pendiente";

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha debe tener el formato YYYY-MM-DD")
    private String fecha;

    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$",
            message = "La hora debe tener el formato HH:mm (24h)")
    private String hora;

    public SolicitudDTO() {}

    public SolicitudDTO(Long id, String titulo, String descripcion,
                        ClienteDTO cliente, TecnicoDTO tecnico,
                        String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }

    public TecnicoDTO getTecnico() { return tecnico; }
    public void setTecnico(TecnicoDTO tecnico) { this.tecnico = tecnico; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
}
