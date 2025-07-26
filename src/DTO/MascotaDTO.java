package DTO;

import java.io.Serializable;

//DTO para encapsular los datos de una mascota.

public class MascotaDTO implements Serializable {
    private String nombre;
    private String especie;
    private int edad;
    private String clave;

    public MascotaDTO(String nombre, String especie, int edad, String clave) {
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setClave(clave);
    }

    // === Getters ===
    public String getNombre() { return nombre; }
    public String getEspecie() { return especie; }
    public int getEdad() { return edad; }
    public String getClave() { return clave; }

    // === Setters con validación ===
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("❌ El nombre no puede estar vacío.");
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank())
            throw new IllegalArgumentException("❌ La especie no puede estar vacía.");
        this.especie = especie;
    }

    public void setEdad(int edad) {
        if (edad < 0)
            throw new IllegalArgumentException("❌ La edad no puede ser negativa.");
        this.edad = edad;
    }

    public void setClave(String clave) {
        if (clave == null || clave.isBlank())
            throw new IllegalArgumentException("❌ La clave no puede estar vacía.");
        this.clave = clave;
    }

    // === Representación para listas ===
    @Override
    public String toString() {
        return nombre + " - " + especie + " - " + edad + " años";
    }
}
