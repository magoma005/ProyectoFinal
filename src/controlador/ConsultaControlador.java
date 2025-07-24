package controlador;

import DAO.ConsultaDAO;
import DTO.ConsultaDTO;
import java.util.List;

public class ConsultaControlador {
    private ConsultaDAO dao = new ConsultaDAO();

    public String agregarConsulta(ConsultaDTO dto) {
        if (dto.getMotivo() == null || dto.getMotivo().isBlank()) return "❌ El motivo es obligatorio.";
        if (dto.getDiagnostico() == null || dto.getDiagnostico().isBlank()) return "❌ El diagnóstico es obligatorio.";
        if (dto.getTratamiento() == null || dto.getTratamiento().isBlank()) return "❌ El tratamiento es obligatorio.";
        dao.guardar(dto);
        return "✅ Consulta registrada correctamente.";
    }

    public List<ConsultaDTO> obtenerConsultas() {
        return dao.listar();
    }

    public String actualizarConsulta(int indice, ConsultaDTO dto) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.actualizar(indice, dto);
        return "✅ Consulta actualizada.";
    }

    public String eliminarConsulta(int indice) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.eliminar(indice);
        return "✅ Consulta eliminada.";
    }
}
