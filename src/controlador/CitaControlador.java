package controlador;

import DAO.CitaDAO;
import DTO.CitaDTO;
import java.util.List;

public class CitaControlador {
    private CitaDAO dao = new CitaDAO();

    public String agregarCita(CitaDTO dto) {
        if (dto.getFecha() == null || dto.getFecha().isBlank()) return "❌ La fecha es obligatoria.";
        if (dto.getHora() == null || dto.getHora().isBlank()) return "❌ La hora es obligatoria.";
        dao.guardar(dto);
        return "✅ Cita agendada correctamente.";
    }

    public List<CitaDTO> obtenerCitas() {
        return dao.listar();
    }

    public String actualizarCita(int indice, CitaDTO dto) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.actualizar(indice, dto);
        return "✅ Cita actualizada.";
    }

    public String eliminarCita(int indice) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.eliminar(indice);
        return "✅ Cita eliminada.";
    }
}
