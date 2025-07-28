package controlador;

import DAO.VacunaDAO;
import DTO.VacunaDTO;
import java.util.List;

// Controlador encargado de manejar la lógica relacionada con las vacunas
public class VacunaControlador {

    // Instancia del DAO que maneja la persistencia de las vacunas
    private final VacunaDAO dao = new VacunaDAO();

    // Método para agregar una nueva vacuna al sistema
    public String agregarVacuna(VacunaDTO dto) {
        // Verifica que los datos ingresados sean válidos
        if (!validar(dto)) return "❌ Datos inválidos.";

        // Guarda la vacuna en el archivo persistente
        dao.guardar(dto);
        return "✅ Vacuna registrada correctamente.";
    }

    // Método que retorna todas las vacunas almacenadas
    public List<VacunaDTO> obtenerVacunas() {
        return dao.listar();
    }

    // Método que actualiza los datos de una vacuna en un índice específico
    public String actualizarVacuna(int indice, VacunaDTO dto) {
        List<VacunaDTO> lista = dao.listar();

        // Verifica que el índice sea válido dentro de la lista
        if (indice < 0 || indice >= lista.size()) return "❌ Índice inválido.";

        // Actualiza la vacuna en la posición dada
        dao.actualizar(indice, dto);
        return "✅ Vacuna actualizada.";
    }

    // Método que elimina una vacuna según su índice en la lista
    public String eliminarVacuna(int indice) {
        List<VacunaDTO> lista = dao.listar();

        // Verifica que el índice esté dentro del rango válido
        if (indice < 0 || indice >= lista.size()) return "❌ Índice inválido.";

        // Elimina la vacuna del almacenamiento
        dao.eliminar(indice);
        return "✅ Vacuna eliminada.";
    }

    // Método privado que valida que los datos esenciales de la vacuna estén completos
    private boolean validar(VacunaDTO dto) {
        return dto.getNombreVacuna() != null && !dto.getNombreVacuna().isBlank()
                && dto.getFechaAplicacion() != null && !dto.getFechaAplicacion().isBlank();
    }
}


