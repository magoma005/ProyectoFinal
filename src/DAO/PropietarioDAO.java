package DAO;

import DTO.PropietarioDTO;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    // Lista en memoria que actúa como base de datos temporal para propietarios
    private final List<PropietarioDTO> propietarios = new ArrayList<>();

    // Guarda un nuevo propietario en la lista
    public void guardar(PropietarioDTO dto) {
        propietarios.add(dto);
    }

    // Retorna una copia de la lista actual de propietarios
    public List<PropietarioDTO> listar() {
        return new ArrayList<>(propietarios); // Se devuelve una copia para evitar modificaciones externas
    }

    // Actualiza un propietario en la posición dada con los datos nuevos
    public void actualizar(int indice, PropietarioDTO dto) {
        if (indice >= 0 && indice < propietarios.size()) { // Verifica que el índice sea válido
            propietarios.set(indice, dto); // Reemplaza el propietario en la posición especificada
        }
    }

    // Elimina un propietario según su índice en la lista
    public void eliminar(int indice) {
        if (indice >= 0 && indice < propietarios.size()) { // Verifica que el índice esté dentro del rango
            propietarios.remove(indice); // Elimina el propietario en la posición dada
        }
    }
}


