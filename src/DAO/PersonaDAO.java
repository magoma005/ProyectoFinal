package DAO;

import modelo.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Este DAO Utiliza serialización binaria para guardar y cargar una lista de personas (propietarios, veterinarios, etc.)
// Soporta polimorfismo gracias al uso de la clase base Persona.

public class PersonaDAO {

    // Ruta del archivo binario donde se guardan las personas.
    private final String archivo = "data/personas.dat";

    // Verifica si el archivo existe, si no, crea uno nuevo con lista vacía.
    public PersonaDAO() {
        File f = new File(archivo);
        if (!f.exists()) {
            guardarPersonas(new ArrayList<>()); // Inicializa el archivo si no existe
        }
    }

    // Guarda una lista completa de personas sobrescribiendo el archivo actual.
    // Esto permite actualizar todo el contenido de forma persistente.
    public void guardarPersonas(List<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personas); // Serializa la lista completa
        } catch (IOException e) {
            System.err.println("Error guardando personas: " + e.getMessage());
        }
    }

    // Carga y retorna la lista de personas desde el archivo binario.
    // Si hay algún problema, devuelve una lista vacía para evitar errores en ejecución.
    public List<Persona> cargarPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            personas = (List<Persona>) ois.readObject(); // Deserializa la lista completa
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
        return personas;
    }
}
