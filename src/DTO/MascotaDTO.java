package DTO;

public class MascotaDTO {
    private String nombre;
    private String especie;
    private int edad;
    private String clave;

    public MascotaDTO(String nombre, String especie, int edad, String clave) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.clave = clave;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public String getClave() { return clave; }
    public int getEdad() { return edad; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setClave(String clave) { this.clave = clave; }
    public void setEdad(int edad) { this.edad = edad; }
}
