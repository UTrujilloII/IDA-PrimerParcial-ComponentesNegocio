package com.tienda.appclient.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa a un técnico de soporte en el sistema.
 * Los técnicos son asignados a las solicitudes de soporte técnico.
 *
 * @author Equipo Backend
 * @version 1.0
 */
@Entity
@Table(name = "tecnicos")
public class Tecnico {

    /**
     * Identificador único del técnico (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Código único del técnico (ej: TEC-001).
     */
    @Column(nullable = false, unique = true)
    private String codigo;

    /**
     * Nombres del técnico.
     */
    @Column(nullable = false)
    private String nombres;

    /**
     * Apellidos del técnico.
     */
    @Column(nullable = false)
    private String apellidos;

    /**
     * Email corporativo del técnico.
     * Debe ser único en el sistema.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Especialidad técnica (ej: Redes, Software, Hardware).
     */
    @Column(nullable = false)
    private String especialidad;

    /**
     * Indica si el técnico está activo para recibir asignaciones.
     */
    private Boolean activo = true;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Tecnico() {
    }

    /**
     * Constructor completo para crear un técnico con todos sus datos.
     *
     * @param codigo Código único del técnico
     * @param nombres Nombres del técnico
     * @param apellidos Apellidos del técnico
     * @param email Email corporativo
     * @param especialidad Área de especialidad
     */
    public Tecnico(String codigo, String nombres, String apellidos, String email, String especialidad) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.especialidad = especialidad;
        this.activo = true;
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

    /**
     * Compara este técnico con otro objeto para determinar igualdad.
     * Dos técnicos son iguales si tienen el mismo código.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tecnico tecnico = (Tecnico) o;
        return Objects.equals(codigo, tecnico.codigo);
    }

    /**
     * Genera el código hash del técnico basado en su código.
     *
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Genera una representación en texto del técnico.
     *
     * @return String con los datos del técnico
     */
    @Override
    public String toString() {
        return "Tecnico{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", activo=" + activo +
                '}';
    }
}

