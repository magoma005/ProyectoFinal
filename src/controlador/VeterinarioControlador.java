package controlador;

import DAO.VeterinarioDAO;
import DTO.VeterinarioDTO;
import java.util.List;

public class VeterinarioControlador {
    private VeterinarioDAO dao = new VeterinarioDAO();

    public String agregarVeterinario(VeterinarioDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) return "❌ El nombre es obligatorio.";
        if (dto.getEspecialidad() == null || dto.getEspecialidad().isBlank())
            return "❌ La especialidad es obligatoria.";
        dao.guardar(dto);
        return "✅ Veterinario registrado correctamente.";
    }

    public List<VeterinarioDTO> obtenerVeterinarios() {
        return dao.listar();
    }

    public String actualizarVeterinario(int indice, VeterinarioDTO dto) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.actualizar(indice, dto);
        return "✅ Veterinario actualizado.";
    }

    public String eliminarVeterinario(int indice) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.eliminar(indice);
        return "✅ Veterinario eliminado.";
    }
}