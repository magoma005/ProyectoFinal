package controlador;

import modelo.Persona;
import DAO.PersonaDAO;

import java.util.ArrayList;
import java.util.List;

//Controlador que gestiona la lógica de negocio entre la interfaz gráfica y los datos persistentes de personas.
public class PersonaControlador {
    private final PersonaDAO dao;
    private List<Persona> personas;

    public PersonaControlador() {
        this.dao = new PersonaDAO();
        this.personas = dao.cargarPersonas();
    }

    //Devuelve la lista actual de personas.

    public List<Persona> listar() {
        return personas;
    }

    //Agrega una nueva persona al sistema.

    public void agregar(Persona persona) {
        personas.add(persona);
        dao.guardarPersonas(personas);
    }

    //Elimina una persona por su identificación única.

    public void eliminarPorIdentificacion(String id) {
        personas.removeIf(p -> p.getIdentificacion().equals(id));
        dao.guardarPersonas(personas);
    }

    // Validación estática reutilizable
    public static boolean validarPersona(String nombre, String documento) {
        return nombre != null && !nombre.isEmpty() &&
                documento != null && documento.matches("\\d+");
    }
}

