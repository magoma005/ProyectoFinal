/*package controlador;

import DAO.CitaDAO;
import DTO.CitaDTO;
import java.util.List;

// Clase que actúa como intermediario entre la vista y el DAO
public class CitaControlador {
    private CitaDAO dao = new CitaDAO();

    // Método para agregar una nueva cita, valida los datos antes de guardar
    public String agregarCita(CitaDTO dto) {
        try {
            // Validar los datos de entrada (fecha y hora)
            validar(dto);
            // Guardar la cita usando el DAO
            dao.guardar(dto);
            return "✅ Cita agendada correctamente.";
        } catch (IllegalArgumentException e) {
            // Si ocurre una excepción por validación, se devuelve el mensaje de error
            return "❌ " + e.getMessage();
        }
    }

    // Método que devuelve la lista de todas las citas guardadas
    public List<CitaDTO> obtenerCitas() {
        return dao.listar();
    }

    // Método para actualizar una cita existente según su índice en la lista
    public String actualizarCita(int indice, CitaDTO dto) {
        // Validar que el índice sea válido (dentro del rango)
        if (indice < 0 || indice >= dao.listar().size())
            return "❌ Índice inválido.";

        // Actualizar la cita en el DAO
        dao.actualizar(indice, dto);
        return "✅ Cita actualizada.";
    }

    // Método para eliminar una cita según su índice
    public String eliminarCita(int indice) {
        // Verifica que el índice esté dentro del rango válido
        if (indice < 0 || indice >= dao.listar().size())
            return "❌ Índice inválido.";

        // Elimina la cita usando el DAO
        dao.eliminar(indice);
        return "✅ Cita eliminada.";
    }

    // Método privado que valida los datos de una cita antes de guardarla o actualizarla
    private void validar(CitaDTO dto) {
        // Verifica que la fecha no sea nula ni esté vacía
        if (dto.getFecha() == null || dto.getFecha().isBlank())
            throw new IllegalArgumentException("La fecha es obligatoria.");

        // Verifica que la hora no sea nula ni esté vacía
        if (dto.getHora() == null || dto.getHora().isBlank())
            throw new IllegalArgumentException("La hora es obligatoria.");
    }
}*/
