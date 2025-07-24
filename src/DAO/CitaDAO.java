package DAO;

import DTO.CitaDTO;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    private final List<CitaDTO> citas = new ArrayList<>();

    public void guardar(CitaDTO dto) {
        citas.add(dto);
    }

    public List<CitaDTO> listar() {
        return new ArrayList<>(citas);
    }

    public void actualizar(int indice, CitaDTO dto) {
        if (indice >= 0 && indice < citas.size()) {
            citas.set(indice, dto);
        }
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < citas.size()) {
            citas.remove(indice);
        }
    }
}

