package DAO;

import DTO.VacunaDTO;
import java.util.ArrayList;
import java.util.List;

public class VacunaDAO {
    // Lista en memoria que almacena temporalmente las vacunas registradas
    private final List<VacunaDTO> vacunas = new ArrayList<>();

    // Método para guardar una nueva vacuna en la lista
    public void guardar(VacunaDTO dto) {
        vacunas.add(dto); // Agrega el objeto DTO a la lista
    }

    // Método para obtener la lista actual de vacunas
    public List<VacunaDTO> listar() {
        return new ArrayList<>(vacunas); // Devuelve una copia de la lista para evitar modificaciones externas
    }

    // Método para actualizar una vacuna en una posición específica de la lista
    public void actualizar(int indice, VacunaDTO dto) {
        if (indice >= 0 && indice < vacunas.size()) { // Verifica que el índice sea válido
            vacunas.set(indice, dto); // Reemplaza el elemento en el índice con el nuevo DTO
        }
    }

    // Método para eliminar una vacuna según su índice en la lista
    public void eliminar(int indice) {
        if (indice >= 0 && indice < vacunas.size()) { // Verifica que el índice esté dentro del rango válido
            vacunas.remove(indice); // Elimina la vacuna en la posición dada
        }
    }
}

