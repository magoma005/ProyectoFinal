package controlador;

import DAO.PropietarioDAO;
import DTO.PropietarioDTO;
import java.util.List;

// Controlador encargado de manejar la lógica relacionada con los propietarios
public class PropietarioControlador {
    // Instancia del DAO responsable de la persistencia de propietarios
    private final PropietarioDAO dao = new PropietarioDAO();
    // Método para agregar un nuevo propietario al sistema
    public String agregarPropietario(PropietarioDTO dto) {
        // Verifica que los datos del propietario sean válidos
        if (!validar(dto)) return "❌ Datos inválidos.";

        // Guarda el propietario en el archivo .dat
        dao.guardar(dto);
        return "✅ Propietario registrado correctamente.";
    }

    // Devuelve la lista de propietarios registrados
    public List<PropietarioDTO> obtenerPropietarios() {
        return dao.listar();
    }

    // Actualiza un propietario en una posición específica de la lista
    public String actualizarPropietario(int indice, PropietarioDTO dto) {
        List<PropietarioDTO> lista = dao.listar();

        // Valida que el índice sea correcto
        if (indice < 0 || indice >= lista.size()) return "❌ Índice inválido.";

        // Actualiza el propietario en el índice dado
        dao.actualizar(indice, dto);
        return "✅ Propietario actualizado.";
    }

    // Elimina un propietario según el índice dado
    public String eliminarPropietario(int indice) {
        List<PropietarioDTO> lista = dao.listar();

        // Valida que el índice esté dentro del rango de la lista
        if (indice < 0 || indice >= lista.size()) return "❌ Índice inválido.";

        // Elimina al propietario del archivo
        dao.eliminar(indice);
        return "✅ Propietario eliminado.";
    }

    // Método privado que valida que los campos requeridos del DTO no estén vacíos
    private boolean validar(PropietarioDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio.");
        if (dto.getTelefono() == null || dto.getTelefono().isBlank())
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        return false;
    }
}

