package DTO;

public class VeterinarioDTO {
    private String nombre;
    private String especialidad;
    private String telefono;

    public VeterinarioDTO(String nombre, String especialidad, String telefono) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}

