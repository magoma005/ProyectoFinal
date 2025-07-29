package controlador;

import modelo.Persona;
import DAO.PersonaDAO;
import java.util.List;

// Controlador que gestiona la lógica de negocio entre la interfaz gráfica y los datos persistentes de personas.
public class PersonaControlador {

    // DAO responsable de leer y escribir la lista de personas desde/hacia el archivo .dat
    private final PersonaDAO dao;

    // Lista que almacena en memoria las personas cargadas desde el archivo
    private List<Persona> personas;

    // Constructor que inicializa el DAO y carga la lista de personas desde el archivo persistente
    public PersonaControlador() {
        this.dao = new PersonaDAO();
        this.personas = dao.cargarPersonas();
    }

    // Devuelve la lista actual de personas cargadas en memoria
    public List<Persona> listar() {
        return personas;
    }

    // Agrega una nueva persona al sistema, la guarda en la lista y actualiza el archivo
    public void agregar(Persona persona) {
        personas.add(persona); // Se añade a la lista en memoria
        dao.guardarPersonas(personas); // Se guarda la lista actualizada en el archivo .dat
    }

    // Elimina una persona por su identificación única y actualiza el archivo
    public void eliminarPorIdentificacion(String id) {
        // Se remueve la persona cuyo ID coincida
        personas.removeIf(p -> p.getIdentificacion().equals(id));
        dao.guardarPersonas(personas); // Se guarda la lista modificada
    }

   /* // Validación estática reutilizable para nombre y documento (solo números)
    public static boolean validarPersona(String nombre, String documento) {
        return nombre != null && !nombre.isEmpty() &&
                documento != null && documento.matches("\\d+");
    }*/

    // Actualiza los datos de una persona existente identificada por su ID
    public boolean actualizarPersona(String id, Persona nuevaPersona) {
        for (int i = 0; i < personas.size(); i++) {
            // Busca coincidencia por identificación
            if (personas.get(i).getIdentificacion().equals(id)) {
                // Reemplaza la persona con la nueva
                personas.set(i, nuevaPersona);
                dao.guardarPersonas(personas); // Guarda cambios
                return true; // Actualización exitosa
            }
        }
        return false; // No se encontró la persona
    }

    // Busca y retorna una persona según su identificación. Si no existe, retorna null
    public Persona buscarPorIdentificacion(String id) {
        for (Persona p : personas) {
            if (p.getIdentificacion().equals(id)) {
                return p; // Persona encontrada
            }
        }
        return null; // No encontrada
    }
}