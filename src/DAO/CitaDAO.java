package DAO;

import DTO.CitaDTO;
import java.util.ArrayList;
import java.util.List;

// Esta clase es para gestionar la persistencia en memoria de las citas
public class CitaDAO {

    // Lista que almacena las citas en memoria
    private final List<CitaDTO> citas = new ArrayList<>();

    // Método para guardar una nueva cita en la lista
    public void guardar(CitaDTO dto) {
        citas.add(dto); // Agrega la cita al final de la lista
    }

    // Método para obtener una copia de todas las citas almacenadas
    public List<CitaDTO> listar() {
        return new ArrayList<>(citas); // Devuelve una nueva lista con los elementos actuales
    }

    // Método para actualizar una cita existente según su índice en la lista
    public void actualizar(int indice, CitaDTO dto) {
        // Verifica que el índice sea válido
        if (indice >= 0 && indice < citas.size()) {
            citas.set(indice, dto); // Reemplaza la cita en la posición dada
        }
    }

    // Método para eliminar una cita según su índice
    public void eliminar(int indice) {
        // Verifica que el índice esté dentro de los límites de la lista
        if (indice >= 0 && indice < citas.size()) {
            citas.remove(indice); // Elimina la cita de la lista
        }
    }
}
