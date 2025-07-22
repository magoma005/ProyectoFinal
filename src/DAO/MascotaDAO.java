package DAO;

import DTO.MascotaDTO;
import java.util.ArrayList;
import java.util.List;

public class MascotaDAO {
    private List<MascotaDTO> mascotas = new ArrayList<>();

    public void guardar(MascotaDTO dto) {
        mascotas.add(dto);
    }

    public List<MascotaDTO> listar() {
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
}
