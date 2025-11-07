package pe.idat.backend.evaluacion.app_soporte.model;

import lombok.Data;

@Data
public class Tecnico {

    private String nombre;
    private String especialidad;

    public Tecnico() {}

    public Tecnico(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}