package controlador;

import DTO.MascotaDTO;
import DAO.MascotaDAO;
import java.util.List;

public class MascotaControlador {
    private final MascotaDAO dao = new MascotaDAO();

    // Método para agregar mascota con validaciones
    public String agregarMascota(MascotaDTO dto) {
        if (dto.getNombre().isBlank() || dto.getEspecie().isBlank()) {
            return "Nombre y especie no pueden estar vacíos.";
        }

        if (dto.getEdad() < 0) {
            return "La edad debe ser mayor o igual a 0.";
        }

        if (dto.getPeso() <= 0) {
            return "El peso debe ser mayor que 0.";
        }

        dao.guardar(dto);
        return "Mascota registrada correctamente.";
    }

    // Método para listar mascotas
    public List<MascotaDTO> obtenerMascotas() {
        return dao.listar();
    }

    // Método para eliminar mascota
    public void eliminarMascota(int indice) {
        dao.eliminar(indice);
    }

    // Método para actualizar mascota con validaciones
    public String actualizarMascota(int indice, MascotaDTO dto) {
        if (dto.getNombre().isBlank() || dto.getEspecie().isBlank()) {
            return "Nombre y especie no pueden estar vacíos.";
        }

        if (dto.getEdad() < 0 || dto.getPeso() <= 0) {
            return "Edad y peso deben ser válidos.";
        }

        dao.actualizar(indice, dto);
        return "Mascota actualizada correctamente.";
    }
}
