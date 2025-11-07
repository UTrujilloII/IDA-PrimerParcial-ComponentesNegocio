package com.empresa.soporte.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    @Column(nullable = false)
    private String estado = "Pendiente";

    private LocalDateTime fechaRegistro;

    public Solicitud() {}

    public Solicitud(Long id, String titulo, String descripcion, Cliente cliente, Tecnico tecnico) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.fechaRegistro = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Tecnico getTecnico() { return tecnico; }
    public void setTecnico(Tecnico tecnico) { this.tecnico = tecnico; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    // Métodos para lacompatibilidad con el controlador
    @Transient
    public String getFecha() {
        return fechaRegistro != null ? fechaRegistro.toLocalDate().toString() : null;
    }

    @Transient
    public String getHora() {
        return fechaRegistro != null ? fechaRegistro.toLocalTime().toString().substring(0, 5) : null;
    }
}
