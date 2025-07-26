package persistencia;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Clase que maneja la serialización y deserialización de datos. Implementa el patrón Singleton para asegurar una única instancia.

public class GestorPersistencia {

    // Instancia única del singleton
    private static GestorPersistencia instancia;

    // Constructor privado para evitar instanciación directa
    private GestorPersistencia() {
    }

    // Método para obtener la única instancia del gestor
    public static synchronized GestorPersistencia getInstance() {
        if (instancia == null) {
            instancia = new GestorPersistencia();
        }
        return instancia;
    }

    // Guarda una lista de objetos serializables en la ruta especificada
    public <T extends Serializable> void guardarLista(String ruta, List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar datos: " + e.getMessage());
        }
    }

    // Carga una lista de objetos serializables desde la ruta especificada
    public <T extends Serializable> List<T> cargarLista(String ruta) {
        File archivo = new File(ruta);

        // 🔧 Crear archivo si no existe (como hace ArchivoManager)
        try {
            if (!archivo.exists()) {
                archivo.getParentFile().mkdirs(); // Crea carpeta si no existe
                archivo.createNewFile();          // Crea archivo vacío
                return new ArrayList<>();         // Devuelve lista vacía
            }
        } catch (IOException e) {
            System.err.println("❌ Error creando archivo: " + e.getMessage());
            return new ArrayList<>();
        }

        // Luego intenta cargar
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("❌ Error al leer datos: " + e.getMessage());
        }

        return new ArrayList<>();
    }

}