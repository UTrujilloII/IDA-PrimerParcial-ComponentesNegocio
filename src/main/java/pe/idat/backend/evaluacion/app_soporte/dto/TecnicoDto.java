package pe.idat.backend.evaluacion.app_soporte.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TecnicoDto {

    @NotBlank(message = "El nombre del técnico no puede estar vacío")
    private String nombre;

    private String especialidad;
}