package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.exception;

import java.time.LocalDateTime;

public class ErrorException {

    private String mensaje;
    private String ruta;
    private int estado;
    private LocalDateTime fechaHora;
    private String error; // breve código o título del error (ej: "Operación Fallida")

    public ErrorException(String mensaje, String ruta, int estado, LocalDateTime fechaHora, String error) {
        this.mensaje = mensaje;
        this.ruta = ruta;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.error = error;
    }

    // Getters (no setters necesarios si solo se crea y se devuelve)
    public String getMensaje() {
        return mensaje;
    }

    public String getRuta() {
        return ruta;
    }

    public int getEstado() {
        return estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getError() {
        return error;
    }
}