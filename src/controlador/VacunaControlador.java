package controlador;

import DAO.VacunaDAO;
import DTO.VacunaDTO;
import java.util.List;

public class VacunaControlador {
    private VacunaDAO dao = new VacunaDAO();

    public String agregarVacuna(VacunaDTO dto) {
        if (dto.getNombreVacuna() == null || dto.getNombreVacuna().isBlank()) return "❌ El nombre de la vacuna es obligatorio.";
        if (dto.getFechaAplicacion() == null || dto.getFechaAplicacion().isBlank()) return "❌ La fecha es obligatoria.";
        dao.guardar(dto);
        return "✅ Vacuna registrada correctamente.";
    }

    public List<VacunaDTO> obtenerVacunas() {
        return dao.listar();
    }

    public String actualizarVacuna(int indice, VacunaDTO dto) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.actualizar(indice, dto);
        return "✅ Vacuna actualizada.";
    }

    public String eliminarVacuna(int indice) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.eliminar(indice);
        return "✅ Vacuna eliminada.";
    }
}

