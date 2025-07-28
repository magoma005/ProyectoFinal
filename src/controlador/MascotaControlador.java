package controlador;

import DTO.MascotaDTO;
import DAO.MascotaDAO;
import modelo.Mascota;
import java.util.ArrayList;
import java.util.List;

public class MascotaControlador {
    private MascotaDAO dao = new MascotaDAO(); // DAO encargado de manejar la persistencia de las mascotas

    // ===============================
    // Método para agregar una mascota. Realiza validaciones y guarda la nueva mascota en el archivo
    // ===============================
    public String agregarMascota(MascotaDTO dto) {
        try {
            validar(dto); // Validación de datos obligatorios
            Mascota m = new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave());
            dao.guardar(m); // Se guarda en archivo .dat
            return "✅ Mascota guardada correctamente.";
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // ============================================
    // Método que retorna la lista de mascotas sin mostrar claves reales. Se usa para mostrar datos de forma segura (con clave oculta)
    // ============================================
    public List<MascotaDTO> obtenerEntidades() {
        List<Mascota> mascotas = dao.listar(); // Obtiene lista original de objetos Mascota
        List<MascotaDTO> listaDTO = new ArrayList<>();
        for (Mascota m : mascotas) {
            // Se enmascara la clave con "***"
            listaDTO.add(new MascotaDTO(m.getNombre(), m.getEspecie(), m.getEdad(), "***"));
        }
        return listaDTO;
    }

    // =====================================================
    // Método que retorna la lista de mascotas CON clave real. Se usa internamente para edición, búsqueda o validaciones
    // =====================================================
    public List<MascotaDTO> obtenerEntidadesConClaveReal() {
        List<Mascota> mascotas = dao.listar();
        List<MascotaDTO> listaDTO = new ArrayList<>();
        for (Mascota m : mascotas) {
            listaDTO.add(new MascotaDTO(m.getNombre(), m.getEspecie(), m.getEdad(), m.getClave()));
        }
        return listaDTO;
    }

    // ================================================
    // Método para actualizar una mascota según su índice. Realiza validaciones y reemplaza el objeto original
    // ================================================
    public String actualizarEntidad(int indice, MascotaDTO dto) {
        try {
            validar(dto);
            Mascota nueva = new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave());
            boolean actualizado = dao.actualizarPorIndice(indice, nueva); // Reemplaza la mascota en esa posición
            if (actualizado) {
                return "✅ Mascota actualizada correctamente.";
            } else {
                return "❌ No se encontró la mascota a actualizar.";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // =============================================
    // Método para eliminar una mascota por su índice
    // =============================================
    public String eliminarEntidad(int indice) {
        boolean eliminado = dao.eliminarPorIndice(indice);
        if (eliminado) {
            return "✅ Mascota eliminada correctamente.";
        } else {
            return "❌ No se encontró la mascota a eliminar.";
        }
    }

    // ============================
    // Método privado de validación. Lanza excepciones si algún campo no cumple con lo requerido
    // ============================
    private void validar(MascotaDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (dto.getEspecie() == null || dto.getEspecie().isBlank()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        if (dto.getEdad() < 0) {
            throw new IllegalArgumentException("Edad inválida.");
        }
        if (dto.getClave() == null || dto.getClave().isBlank()) {
            throw new IllegalArgumentException("La clave no puede estar vacía.");
        }
    }

    // ======================================================
    // Método para buscar una mascota por su nombre y especie. Retorna el DTO encontrado o null si no existe
    // ======================================================
    public MascotaDTO buscarPorNombreYEspecie(String nombre, String especie) {
        for (MascotaDTO m : obtenerEntidades()) {
            if (m.getNombre().equals(nombre) && m.getEspecie().equals(especie)) {
                return m;
            }
        }
        return null;
    }

    // ========================================================
    // Método para actualizar una mascota a partir de su clave. Se recorre la lista hasta encontrar la mascota con esa clave
    // ========================================================
    public String actualizarPorClave(String claveOriginal, MascotaDTO nueva) {
        List<Mascota> lista = dao.listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getClave().equals(claveOriginal)) {
                Mascota mascotaActualizada = new Mascota(
                        nueva.getNombre(),
                        nueva.getEspecie(),
                        nueva.getEdad(),
                        nueva.getClave()
                );
                dao.actualizarPorIndice(i, mascotaActualizada);
                return "✅ Mascota actualizada correctamente.";
            }
        }
        return "❌ No se encontró la mascota para actualizar.";
    }

    // =================================================
    // Método que guarda una lista completa de mascotas. Se usa cuando se quiere sobrescribir todo el archivo
    // =================================================
    public void guardarEntidades(List<MascotaDTO> lista) {
        List<Mascota> entidades = new ArrayList<>();
        for (MascotaDTO dto : lista) {
            entidades.add(new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave()));
        }
        dao.guardarLista(entidades);
    }

}

