package DAO;

import modelo.Mascota;
import persistencia.GestorPersistencia;

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    // Ruta al archivo binario donde se almacenarán las mascotas
    private final String RUTA = "data/mascotas.dat";

    // Instancia única del gestor de persistencia para manejar lectura/escritura de objetos
    private final GestorPersistencia gestor = GestorPersistencia.getInstance();

    // Constructor del DAO
    public MascotaDAO() {
        // Verifica si el archivo ya tiene contenido; si no, lo inicializa con una lista vacía
        List<Mascota> mascotas = listar(); // Intenta cargar las mascotas actuales
        if (mascotas.isEmpty()) {
            gestor.guardarLista(RUTA, mascotas); // Si está vacío (o no existe), lo crea con una lista vacía
        }
    }

    // Guarda una nueva mascota agregándola a la lista y reescribiendo el archivo
    public void guardar(Mascota mascota) {
        List<Mascota> mascotas = listar(); // Carga la lista actual desde el archivo
        mascotas.add(mascota);             // Agrega la nueva mascota
        gestor.guardarLista(RUTA, mascotas); // Guarda toda la lista nuevamente en el archivo
    }

    // Retorna la lista completa de mascotas desde el archivo
    public List<Mascota> listar() {
        List<Mascota> mascotas = gestor.cargarLista(RUTA); // Intenta cargar desde archivo
        return mascotas != null ? mascotas : new ArrayList<>(); // Si es null, retorna lista vacía
    }

    // Elimina una mascota por índice y guarda la lista modificada
    public boolean eliminarPorIndice(int indice) {
        List<Mascota> mascotas = listar(); // Carga la lista actual
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.remove(indice); // Elimina la mascota del índice indicado
            gestor.guardarLista(RUTA, mascotas); // Guarda la lista actualizada
            return true;
        }
        return false; // Retorna false si el índice no es válido
    }

    // Actualiza una mascota existente por índice y guarda los cambios
    public boolean actualizarPorIndice(int indice, Mascota nueva) {
        List<Mascota> mascotas = listar(); // Carga la lista actual
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.set(indice, nueva); // Reemplaza la mascota en ese índice con la nueva
            gestor.guardarLista(RUTA, mascotas); // Guarda la lista modificada en el archivo
            return true;
        }
        return false; // Retorna false si el índice es inválido
    }

    // Busca una mascota por su nombre (exacto)
    public Mascota buscarPorNombre(String nombre) {
        for (Mascota m : listar()) { // Recorre todas las mascotas actuales
            if (m.getNombre().equals(nombre)) {
                return m; // Retorna la mascota si encuentra una coincidencia exacta
            }
        }
        return null; // Retorna null si no se encuentra ninguna mascota con ese nombre
    }

    // Guarda directamente una lista completa de mascotas (sobrescribe el archivo)
    public void guardarLista(List<Mascota> lista) {
        gestor.guardarLista(RUTA, lista); // Guarda toda la lista en el archivo .dat
    }

    /*
    private void sobrescribirLista(List<Mascota> mascotas) {
        List<String> lineas = new ArrayList<>();
        for (Mascota m : mascotas) {
            lineas.add(m.toLineaArchivo()); // Convierte la mascota a formato de línea de texto
        }
        archivo.sobrescribirArchivo(lineas); // Guarda líneas en archivo de texto
    }
    */
}

/*import modelo.Mascota;
import DTO.MascotaDTO;
import persistencia.ArchivoManager;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private final ArchivoManager archivo;
    private List<MascotaDTO> mascotas = new ArrayList<>();

    public MascotaDAO() {
        archivo = new ArchivoManager("data/mascotas.txt");
    }

    public void guardar(Mascota mascota) {
        archivo.escribirLinea(mascota.toLineaArchivo());
    }

    public List<Mascota> listar() {
        List<Mascota> mascotas = new ArrayList<>();
        for (String linea : archivo.leerLineas()) {
            Mascota m = Mascota.desdeLineaArchivo(linea);
            if (m != null) mascotas.add(m);
        }
        return mascotas;
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.remove(indice);
        }
    }

    public void actualizar(int indice, MascotaDTO dto) {
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.set(indice, dto);
        }
    }
}*/
