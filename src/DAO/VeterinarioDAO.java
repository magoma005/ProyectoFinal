package DAO;

import DTO.VeterinarioDTO;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioDAO {
    private final List<VeterinarioDTO> veterinarios = new ArrayList<>();

    public void guardar(VeterinarioDTO dto) {
        veterinarios.add(dto);
    }

    public List<VeterinarioDTO> listar() {
        return new ArrayList<>(veterinarios);
    }

    public void actualizar(int indice, VeterinarioDTO dto) {
        if (indice >= 0 && indice < veterinarios.size()) {
            veterinarios.set(indice, dto);
        }
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < veterinarios.size()) {
            veterinarios.remove(indice);
        }
    }
}
