package DTO;

public class MascotaDTO {
    private String nombre;
    private String especie;
    private int edad;
    private double peso;

    public MascotaDTO(String nombre, String especie, int edad, double peso) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.peso = peso;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }
    public double getPeso() { return peso; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEspecie(String especie) { this.especie = especie; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setPeso(double peso) { this.peso = peso; }
}
