package modelo;

import java.util.ArrayList;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private String clave; // Clave para seguridad
    private ArrayList<EventoClinico> historial;

    public Mascota(String nombre, String especie, int edad, String clave) {
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setClave(clave);
        historial = new ArrayList<>();
    }

    //Setters con su respectiva validación

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        this.especie = especie;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }

    public void setClave(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("La clave no puede estar vacía.");
        }
        this.clave = clave;
    }

    // === Getters ===

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public String getClave() {
        return clave;
    }

    /**public void agregarConsulta(Consulta consulta) {
        historial.agregarConsulta(consulta); // delegamos al historial
    }**/

    // Método para agregar un evento clínico al historial
    public void agregarEvento(EventoClinico evento) {
        if (evento != null) {
            historial.add(evento);
        }
    }

    public void mostrarHistorial() {
        System.out.println("📋 Historial clínico de " + nombre + ":");
        for (EventoClinico e : historial) {
            e.mostrarDetalle(); // se llama al método override de la subclase
        }
    }
}
