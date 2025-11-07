package pe.idat.backend.evaluacion.app_soporte.model;

import lombok.Data;

@Data
public class Cliente {

    private String nombre;
    private String email;

    public Cliente() {}

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}