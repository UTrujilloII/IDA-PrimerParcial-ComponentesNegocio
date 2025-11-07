package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model;

import java.time.LocalDateTime; // Import necesario para manejar fechas

public class Solicitud {

    private Integer id;
    private String descripcion;
    private LocalDateTime fechaRegistro;
    private String estado;
    private Integer clienteId;
    private Integer tecnicoId;

    // Constructor vacío
    public Solicitud() {}

    // Constructor con parámetros
    public Solicitud(Integer id, String descripcion, LocalDateTime fechaRegistro, String estado, Integer clienteId, Integer tecnicoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
        this.clienteId = clienteId;
        this.tecnicoId = tecnicoId;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Integer tecnicoId) {
        this.tecnicoId = tecnicoId;
    }
}