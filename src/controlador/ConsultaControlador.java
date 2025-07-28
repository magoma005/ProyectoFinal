package controlador;

import DAO.ConsultaDAO;
import DTO.ConsultaDTO;
import java.util.List;

// Clase encargada de manejar la lógica relacionada con las consultas médicas
public class ConsultaControlador {

    // Instancia del DAO que maneja la persistencia de las consultas
    private ConsultaDAO dao = new ConsultaDAO();

    // Método para registrar una nueva consulta
    public String agendarConsulta(ConsultaDTO dto) {
        try {
            // Validar que los datos obligatorios estén presentes
            validar(dto);
            // Guardar la consulta si todo está correcto
            dao.guardar(dto);
            return "✅ Consulta registrada correctamente.";
        } catch (IllegalArgumentException e) {
            // Si hay un error de validación, se captura y se devuelve el mensaje
            return "❌ " + e.getMessage();
        }
    }

    // Método para obtener la lista completa de consultas
    public List<ConsultaDTO> obtenerConsultas() {
        return dao.listar();
    }

    // Método para actualizar una consulta existente en una posición específica
    public String actualizarConsulta(int indice, ConsultaDTO dto) {
        // Validar que el índice esté dentro del rango válido
        if (indice < 0 || indice >= dao.listar().size())
            return "❌ Índice inválido.";

        // Actualizar los datos de la consulta en la posición indicada
        dao.actualizar(indice, dto);
        return "✅ Consulta actualizada.";
    }

    // Método para eliminar una consulta según su posición en la lista
    public String eliminarConsulta(int indice) {
        // Verificar que el índice esté dentro de los límites permitidos
        if (indice < 0 || indice >= dao.listar().size())
            return "❌ Índice inválido.";

        // Eliminar la consulta usando el DAO
        dao.eliminar(indice);
        return "✅ Consulta eliminada.";
    }

    // Método privado para validar los campos obligatorios de una consulta
    private void validar(ConsultaDTO dto) {
        // Verifica que el campo 'servicio' no esté vacío
        if (dto.getServicio() == null || dto.getServicio().isBlank())
            throw new IllegalArgumentException("El servicio es obligatorio.");

        // Verifica que el campo 'diagnóstico' no esté vacío
        if (dto.getDiagnostico() == null || dto.getDiagnostico().isBlank())
            throw new IllegalArgumentException("El diagnóstico es obligatorio.");
    }
}

