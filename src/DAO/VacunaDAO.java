package DAO;

import DTO.VacunaDTO;
import java.util.ArrayList;
import java.util.List;

public class VacunaDAO {
    private final List<VacunaDTO> vacunas = new ArrayList<>();

    public void guardar(VacunaDTO dto) {
        vacunas.add(dto);
    }

    public List<VacunaDTO> listar() {
        return new ArrayList<>(vacunas);
    }

    public void actualizar(int indice, VacunaDTO dto) {
        if (indice >= 0 && indice < vacunas.size()) {
            vacunas.set(indice, dto);
        }
    }

    public void eliminar(int indice) {
        if (indice >= 0 && indice < vacunas.size()) {
            vacunas.remove(indice);
        }
    }
}

