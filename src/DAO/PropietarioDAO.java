package DAO;

import DTO.PropietarioDTO;
import java.util.ArrayList;
import java.util.List;

public class PropietarioDAO {
    private final List<PropietarioDTO> propietarios = new ArrayList<>();

    public void guardar(PropietarioDTO dto) {
        propietarios.add(dto);
    }

    public List<PropietarioDTO> listar() {
        return new ArrayList<>(propietarios);
    }

    public void actualizar(int indice, PropietarioDTO dto) {
        if (indice >= 0 && indice < propietarios.size()) {
            propietarios.set(indice, dto);
        }
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < propietarios.size()) {
            propietarios.remove(indice);
        }
    }
}

