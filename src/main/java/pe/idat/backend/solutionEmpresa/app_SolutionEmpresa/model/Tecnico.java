package pe.idat.backend.solutionEmpresa.app_SolutionEmpresa.model;

public class Tecnico {

    private Integer id;
    private String nombre;
    private String especialidad;

    // Constructor vacío
    public Tecnico() {}

    // Constructor con parámetros
    public Tecnico(Integer id, String nombre, String especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}