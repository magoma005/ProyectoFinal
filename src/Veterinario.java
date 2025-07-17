/**
 Esta clase ahora extiende de Persona y añade la especialidad médica.
 * Incluye métodos para mostrar su perfil.
 */

public class Veterinario extends Persona {
    private String especialidad;

    public Veterinario(String nombre, String especialidad) {
        //this.nombre = nombre;
        //this.especialidad = especialidad;
        //El constructor ahora usa los setters para aplicar las validaciones al crear el objeto.

        //Validación de argumentos
        super(nombre); // usa constructor de Persona
        setEspecialidad(especialidad);
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    // === Métodos de visualización ===

    /**
     * Muestra los datos básicos del veterinario.
     */

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // muestra nombre
        System.out.println("🩺 Especialidad: " + especialidad);
    }

    /**
     * Muestra el perfil completo del veterinario.
     */

    public void mostrarPerfil() {
        mostrarDatos();
    }
}
