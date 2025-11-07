package com.tienda.appclient.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa a un cliente en el sistema.
 * Encapsula la información personal del cliente que solicita soporte técnico.
 *
 * @author Equipo Backend
 * @version 1.0
 */
@Entity
@Table(name = "clientes")
public class Cliente {

    /**
     * Identificador único del cliente (clave primaria).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Documento Nacional de Identidad del cliente.
     * Debe ser único en el sistema.
     */
    @Column(nullable = false, unique = true)
    private String dni;

    /**
     * Apellido paterno del cliente.
     */
    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    /**
     * Apellido materno del cliente (opcional).
     */
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    /**
     * Nombres completos del cliente.
     */
    @Column(nullable = false)
    private String nombres;

    /**
     * Fecha de nacimiento del cliente.
     */
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Cliente() {
    }

    /**
     * Constructor completo para crear un cliente con todos sus datos.
     *
     * @param dni Documento Nacional de Identidad
     * @param apellidoPaterno Apellido paterno
     * @param apellidoMaterno Apellido materno
     * @param nombres Nombres completos
     * @param fechaNacimiento Fecha de nacimiento
     */
    public Cliente(String dni, String apellidoPaterno, String apellidoMaterno, String nombres, LocalDate fechaNacimiento) {
        this.dni = dni;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters and setters
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

    /**
     * Compara este cliente con otro objeto para determinar igualdad.
     * Dos clientes son iguales si tienen el mismo DNI.
     *
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    /**
     * Genera el código hash del cliente basado en su DNI.
     *
     * @return Código hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    /**
     * Genera una representación en texto del cliente.
     *
     * @return String con los datos del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                '}';
    }
}

