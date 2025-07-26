package DAO;

import modelo.Mascota;
import persistencia.GestorPersistencia; // Usamos el nuevo gestor

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    //private final ArchivoManager archivo;

    // Ruta al archivo .dat
    private final String RUTA = "data/mascotas.dat";

    // Instancia única del gestor de persistencia (Singleton)
    private final GestorPersistencia gestor = GestorPersistencia.getInstance();

    public MascotaDAO() {
        // Verifica que el archivo exista al inicio
        List<Mascota> mascotas = listar(); // Esto asegura que si el archivo no existe, se cree más adelante
        if (mascotas.isEmpty()) {
            gestor.guardarLista(RUTA, mascotas); // Crea archivo vacío si no existe
        }
    }


    public void guardar(Mascota mascota) {
        //archivo.escribirLinea(mascota.toLineaArchivo());
        List<Mascota> mascotas = listar(); // Cargamos la lista actual
        mascotas.add(mascota);             // Añadimos la nueva mascota
        gestor.guardarLista(RUTA, mascotas); // Guardamos toda la lista
    }

    public List<Mascota> listar() {
        List<Mascota> mascotas = gestor.cargarLista(RUTA);
        return mascotas != null ? mascotas : new ArrayList<>();
        /*        List<Mascota> mascotas = new ArrayList<>();
        for (String linea : archivo.leerLineas()) {
            Mascota m = Mascota.desdeLineaArchivo(linea);
            if (m != null) mascotas.add(m);
        }
        return mascotas;*/
    }

    public boolean eliminarPorIndice(int indice) {
        List<Mascota> mascotas = listar();
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.remove(indice);
            //sobrescribirLista(mascotas);
            gestor.guardarLista(RUTA, mascotas); // Sobrescribimos con la nueva lista
            return true;
        }
        return false;
    }

    public boolean actualizarPorIndice(int indice, Mascota nueva) {
        List<Mascota> mascotas = listar();
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.set(indice, nueva);
            // sobrescribirLista(mascotas);
            gestor.guardarLista(RUTA, mascotas); // Guardamos la lista modificada
            return true;
        }
        return false;
    }
    /*    private void sobrescribirLista(List<Mascota> mascotas) {
        List<String> lineas = new ArrayList<>();
        for (Mascota m : mascotas) {
            lineas.add(m.toLineaArchivo());
        }
        archivo.sobrescribirArchivo(lineas);
    }*/
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
