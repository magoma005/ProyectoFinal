package DAO;

import modelo.Mascota;
import persistencia.ArchivoManager;

import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private final ArchivoManager archivo;

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

    public boolean eliminarPorIndice(int indice) {
        List<Mascota> mascotas = listar();
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.remove(indice);
            sobrescribirLista(mascotas);
            return true;
        }
        return false;
    }

    public boolean actualizarPorIndice(int indice, Mascota nueva) {
        List<Mascota> mascotas = listar();
        if (indice >= 0 && indice < mascotas.size()) {
            mascotas.set(indice, nueva);
            sobrescribirLista(mascotas);
            return true;
        }
        return false;
    }

    private void sobrescribirLista(List<Mascota> mascotas) {
        List<String> lineas = new ArrayList<>();
        for (Mascota m : mascotas) {
            lineas.add(m.toLineaArchivo());
        }
        archivo.sobrescribirArchivo(lineas);
    }
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
