package pe.idat.backend.evaluacion.app_soporte.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDto {

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Size(min = 3, message = "El nombre debe tener mínimo 3 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser una dirección de email válida")
    private String email;
}