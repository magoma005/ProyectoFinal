package controlador;

import DAO.VeterinarioDAO;
import DTO.VeterinarioDTO;
import java.util.List;

// Controlador que gestiona la lógica relacionada con los veterinarios
public class VeterinarioControlador {

    // Instancia del DAO para acceder a la persistencia de veterinarios
    private final VeterinarioDAO dao = new VeterinarioDAO();

    // Método para agregar un nuevo veterinario al sistema
    public String agregarVeterinario(VeterinarioDTO dto) {
        try {
            // Validación de los datos del veterinario
            validar(dto);

            // Guarda el veterinario si los datos son válidos
            dao.guardar(dto);
            return "✅ Veterinario registrado correctamente.";
        } catch (IllegalArgumentException e) {
            // Retorna el mensaje de error si hay datos inválidos
            return "❌ " + e.getMessage();
        }
    }

    // Método para obtener la lista de todos los veterinarios registrados
    public List<VeterinarioDTO> obtenerVeterinarios() {
        return dao.listar();
    }

    // Método para actualizar los datos de un veterinario dado su índice
    public String actualizarVeterinario(int indice, VeterinarioDTO dto) {
        // Verifica que el índice esté dentro del rango de la lista
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";

        // Actualiza la información del veterinario en la posición indicada
        dao.actualizar(indice, dto);
        return "✅ Veterinario actualizado.";
    }

    // Método para eliminar un veterinario usando su índice
    public String eliminarVeterinario(int indice) {
        // Verifica que el índice sea válido
        if (indice < 0 || indice >= dao.listar().size()) return "❌ Índice inválido.";

        // Elimina el veterinario de la lista
        dao.eliminar(indice);
        return "✅ Veterinario eliminado.";
    }

    // Método privado que valida los campos obligatorios del veterinario
    private void validar(VeterinarioDTO dto) {
        // Verifica que el nombre no esté vacío o nulo
        if (dto.getNombre() == null || dto.getNombre().isBlank())
            throw new IllegalArgumentException("El nombre es obligatorio.");

        // Verifica que la especialidad no esté vacía o nula
        if (dto.getEspecialidad() == null || dto.getEspecialidad().isBlank())
            throw new IllegalArgumentException("La especialidad es obligatoria.");
    }
}