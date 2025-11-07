package com.empresa.soporte.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Datos del cliente del sistema de soporte")
public class ClienteDTO {

    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "El nombre del cliente solo puede contener letras")
    @Schema(description = "Nombre completo del cliente", example = "Luis Rodríguez")
    private String nombre;

    @NotBlank(message = "El correo del cliente es obligatorio")
    @Email(message = "El correo debe tener un formato válido (ejemplo@dominio.com)")
    @Schema(description = "Correo electrónico del cliente", example = "luis@example.com")
    private String correo;

    public ClienteDTO() {}

    public ClienteDTO(Long id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
