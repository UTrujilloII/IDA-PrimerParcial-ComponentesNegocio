package com.tienda.appclient.dto;

import jakarta.validation.constraints.*;

public class TecnicoDTO {

    private Integer id;

    @NotBlank(message = "Código es obligatorio")
    @Size(min = 3, max = 20, message = "Código debe tener entre 3 y 20 caracteres")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Código solo puede contener letras mayúsculas, números y guiones")
    private String codigo;

    @NotBlank(message = "Nombres es obligatorio")
    @Size(max = 100, message = "Nombres demasiado largos")
    private String nombres;

    @NotBlank(message = "Apellidos es obligatorio")
    @Size(max = 100, message = "Apellidos demasiado largos")
    private String apellidos;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Email debe ser válido")
    private String email;

    @NotBlank(message = "Especialidad es obligatoria")
    @Size(max = 100, message = "Especialidad demasiado larga")
    private String especialidad;

    private Boolean activo = true;

    // Constructors
    public TecnicoDTO() {
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}

