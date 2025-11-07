package com.empresa.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos del técnico asignado a la solicitud")
public class TecnicoDTO {

    @Schema(description = "Identificador del técnico", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del técnico es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre del técnico solo puede contener letras")
    @Schema(description = "Nombre del técnico", example = "Carlos López")
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "La especialidad solo puede contener letras")
    @Schema(description = "Especialidad del técnico", example = "Hardware")
    private String especialidad;

    public TecnicoDTO() {}

    public TecnicoDTO(Long id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
