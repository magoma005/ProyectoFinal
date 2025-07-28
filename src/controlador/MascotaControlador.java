package controlador;

import DTO.MascotaDTO;
import DAO.MascotaDAO;
import modelo.Mascota;
import java.util.ArrayList;
import java.util.List;

public class MascotaControlador {
    private MascotaDAO dao = new MascotaDAO();

    // Método para agregar mascota (agrega validaciones)
    public String agregarMascota(MascotaDTO dto) {
        try {
            validar(dto);
            Mascota m = new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave());
            dao.guardar(m);
            return "✅ Mascota guardada correctamente.";
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // Método para obtener todas las mascotas
    public List<MascotaDTO> obtenerEntidades() {
        List<Mascota> mascotas = dao.listar();
        List<MascotaDTO> listaDTO = new ArrayList<>();
        for (Mascota m : mascotas) {
            listaDTO.add(new MascotaDTO(m.getNombre(), m.getEspecie(), m.getEdad(), "***"));
        }
        return listaDTO;
    }

    // Método privado que devuelve las mascotas con claves REALES (para uso interno)
    public List<MascotaDTO> obtenerEntidadesConClaveReal() {
        List<Mascota> mascotas = dao.listar();
        List<MascotaDTO> listaDTO = new ArrayList<>();
        for (Mascota m : mascotas) {
            // Aquí sí se usa la clave real
            listaDTO.add(new MascotaDTO(m.getNombre(), m.getEspecie(), m.getEdad(), m.getClave()));
        }
        return listaDTO;
    }


    // Método para actualizar mascota por índice
    public String actualizarEntidad(int indice, MascotaDTO dto) {
        try {
            validar(dto);
            Mascota nueva = new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave());
            boolean actualizado = dao.actualizarPorIndice(indice, nueva);
            if (actualizado) {
                return "✅ Mascota actualizada correctamente.";
            } else {
                return "❌ No se encontró la mascota a actualizar.";
            }
        } catch (Exception e) {
            return "❌ Error: " + e.getMessage();
        }
    }

    // Método para eliminar mascota por índice
    public String eliminarEntidad(int indice) {
        boolean eliminado = dao.eliminarPorIndice(indice);
        if (eliminado) {
            return "✅ Mascota eliminada correctamente.";
        } else {
            return "❌ No se encontró la mascota a eliminar.";
        }
    }

    // Validaciones de campos
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

    public MascotaDTO buscarPorNombreYEspecie(String nombre, String especie) {
        for (MascotaDTO m : obtenerEntidades()) {
            if (m.getNombre().equals(nombre) && m.getEspecie().equals(especie)) {
                return m;
            }
        }
        return null;
    }

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



    // Método para guardar la lista completa de mascotas (para persistencia total)
    public void guardarEntidades(List<MascotaDTO> lista) {
        List<Mascota> entidades = new ArrayList<>();
        for (MascotaDTO dto : lista) {
            entidades.add(new Mascota(dto.getNombre(), dto.getEspecie(), dto.getEdad(), dto.getClave()));
        }
        dao.guardarLista(entidades);
    }

}
