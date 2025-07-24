package controlador;

import DAO.PropietarioDAO;
import DTO.PropietarioDTO;
import java.util.List;

public class PropietarioControlador {
    private PropietarioDAO dao = new PropietarioDAO();

    public String agregarPropietario(PropietarioDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) return "❌ El nombre es obligatorio.";
        if (dto.getTelefono() == null || dto.getTelefono().isBlank()) return "❌ El teléfono es obligatorio.";
        dao.guardar(dto);
        return "✅ Propietario registrado correctamente.";
    }

    public List<PropietarioDTO> obtenerPropietarios() {
        return dao.listar();
    }

    public String actualizarPropietario(int indice, PropietarioDTO dto) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.actualizar(indice, dto);
        return "✅ Propietario actualizado.";
    }

    public String eliminarPropietario(int indice) {
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";
        dao.eliminar(indice);
        return "✅ Propietario eliminado.";
    }
}
