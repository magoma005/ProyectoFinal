package DAO;

import modelo.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//DAO para guardar y cargar personas usando serialización binaria. Soporta polimorfismo: guarda propietarios y veterinarios desde una lista común.

public class PersonaDAO {
    private final String archivo = "data/personas.dat";

    //Guarda una lista de personas (veterinarios y propietarios).

    public void guardarPersonas(List<Persona> personas) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(personas);
        } catch (IOException e) {
            System.err.println("Error guardando personas: " + e.getMessage());
        }
    }

    //Carga la lista de personas desde el archivo, o retorna una lista vacía.

    public List<Persona> cargarPersonas() {
        List<Persona> personas = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            personas = (List<Persona>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
        }
        return personas;
    }
}
