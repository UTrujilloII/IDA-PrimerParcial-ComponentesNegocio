package com.tienda.appclient.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

/**
 * Data Transfer Object para Cliente.
 * Encapsula los datos de un cliente con validaciones declarativas.
 * Separa la capa de presentación del modelo de dominio.
 *
 * @author Equipo Backend
 * @version 1.0
 */
public class ClienteDTO {

    /**
     * Identificador único del cliente.
     */
    private Integer id;

    /**
     * DNI del cliente.
     * Validaciones: No vacío, 8-12 caracteres, solo letras/números/guiones
     */
    @NotBlank(message = "DNI es obligatorio")
    @Size(min = 8, max = 12, message = "DNI debe tener entre 8 y 12 caracteres")
    @Pattern(regexp = "^[0-9A-Za-z-]+$", message = "DNI sólo puede contener letras, números y guiones")
    private String dni;

    /**
     * Apellido paterno del cliente.
     * Validaciones: No vacío, máximo 100 caracteres
     */
    @NotBlank(message = "Apellido paterno es obligatorio")
    @Size(max = 100, message = "Apellido paterno demasiado largo")
    private String apellidoPaterno;

    /**
     * Apellido materno del cliente (opcional).
     */
    @Size(max = 100, message = "Apellido materno demasiado largo")
    private String apellidoMaterno;

    /**
     * Nombres del cliente.
     * Validaciones: No vacío, máximo 200 caracteres
     */
    @NotBlank(message = "Nombres es obligatorio")
    @Size(max = 200, message = "Nombres demasiado largos")
    private String nombres;

    /**
     * Fecha de nacimiento del cliente.
     * Validaciones: No nula, no puede ser futura
     */
    @NotNull(message = "Fecha de nacimiento es obligatoria")
    @PastOrPresent(message = "Fecha de nacimiento no puede ser futura")
    private LocalDate fechaNacimiento;

    /**
     * Constructor por defecto.
     */
    public ClienteDTO() {
    }

    // getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
