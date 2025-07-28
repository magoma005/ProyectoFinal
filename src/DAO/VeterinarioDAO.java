package DAO;

import DTO.VeterinarioDTO;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {

    // Lista que almacena en memoria los objetos de tipo VeterinarioDTO
    private final List<VeterinarioDTO> veterinarios = new ArrayList<>();

    // Método para guardar un nuevo veterinario en la lista
    public void guardar(VeterinarioDTO dto) {
        veterinarios.add(dto); // Agrega el veterinario recibido a la lista
    }

    // Método que devuelve una copia de la lista de veterinarios
    public List<VeterinarioDTO> listar() {
        return new ArrayList<>(veterinarios); // Se retorna una copia para proteger la lista original
    }

    // Método para actualizar los datos de un veterinario existente
    public void actualizar(int indice, VeterinarioDTO dto) {
        // Se verifica que el índice esté dentro del rango válido
        if (indice >= 0 && indice < veterinarios.size()) {
            veterinarios.set(indice, dto); // Se reemplaza el veterinario en la posición indicada
        }
    }

    // Método para eliminar un veterinario según su índice
    public void eliminar(int indice) {
        // Se verifica que el índice sea válido
        if (indice >= 0 && indice < veterinarios.size()) {
            veterinarios.remove(indice); // Se elimina el veterinario en la posición indicada
        }
    }
}
