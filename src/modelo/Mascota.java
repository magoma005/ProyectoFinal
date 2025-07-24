package modelo;

import excepciones.NombreVacioException;
import excepciones.EdadInvalidaException;
import excepciones.EspecieVaciaException;
import excepciones.ClaveVaciaException;
import java.util.ArrayList;


public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private String clave;
    private ArrayList<EventoClinico> historial;

    //Constructor principal con todas las validaciones.

    public Mascota(String nombre, String especie, int edad, String clave) {
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setClave(clave);
        historial = new ArrayList<>();
    }

    //Constructor adicional sin clave (para métodos como desdeLineaArchivo).

    public Mascota(String nombre, String especie, int edad) {
        setNombre(nombre); // antes: this.nombre = nombre;
        setEspecie(especie); // antes: this.especie = especie;
        setEdad(edad); // antes: this.edad = edad;
        this.clave = ""; // para que se inicializa vacío
        historial = new ArrayList<>();
    }

    // Setters

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new NombreVacioException("El nombre de la mascota no puede estar vacío.");
        }
        if (nombre.length() < 3) {
            throw new NombreVacioException("El nombre debe tener al menos 3 caracteres."); //Nueva regla
        }
        this.nombre = nombre;
    }


    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank()) {
            throw new EspecieVaciaException("La especie no puede estar vacía.");
        }
        this.especie = especie;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            // Antes se usaba IllegalArgumentException
            throw new EdadInvalidaException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }

    public void setClave(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new ClaveVaciaException("La clave no puede estar vacía.");
        }
        this.clave = clave;
    }

    // Getters

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

    // Métodos de historial clínico

    public void agregarEvento(EventoClinico evento) {
        if (evento != null) {
            historial.add(evento);
        }
    }

    public void mostrarHistorial() {
        System.out.println("📋 Historial clínico de " + nombre + ":");
        for (EventoClinico e : historial) {
            e.mostrarDetalle(); // Se llama al método override de la subclase
        }
    }

    //Devuelve una representación de la mascota en formato CSV para guardar en archivo.
    public String toLineaArchivo() {
        return nombre + "," + especie + "," + edad + "," + clave;
    }

    //Crea una instancia de Mascota a partir de una línea CSV.
    public static Mascota desdeLineaArchivo(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 4) return null;

        try {
            int edad = Integer.parseInt(partes[2].trim());
            String clave = partes[3].trim();
            return new Mascota(partes[0].trim(), partes[1].trim(), edad, clave);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
