package pe.idat.backend.app_soporte.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tecnico {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad; // Hardware, Software, Redes, etc.

    private boolean disponible = true;

    // Constructor personalizado sin el campo disponible (por defecto true)
    public Tecnico(Long id, String nombre, String apellido, String email, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.especialidad = especialidad;
        this.disponible = true;
    }

    // Método de negocio personalizado
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}