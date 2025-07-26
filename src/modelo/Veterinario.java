package modelo;

/**
 * Clase Veterinario que hereda de Persona.
 * Representa a un profesional veterinario con una especialidad médica.
 */
public class Veterinario extends Persona {
    private String especialidad;

    //Constructor de Veterinario.
    public Veterinario(String nombre, String identificacion, String especialidad) {
        super(nombre, identificacion); // Usa constructor de Persona
        setEspecialidad(especialidad); // Aplica validación
    }

    //Asigna la especialidad del veterinario.


    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("❌ La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }

    //Devuelve la especialidad actual.

    public String getEspecialidad() {
        return especialidad;
    }

    // === Métodos de visualización ===

    //Muestra los datos básicos del veterinario.

    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // nombre e identificación
        System.out.println("🩺 Especialidad: " + especialidad);
    }

    //Muestra el perfil completo del veterinario. Por ahora, es igual a mostrarDatos pero puede expandirse.

    public void mostrarPerfil() {
        mostrarDatos();
    }

    //Retorna el tipo de persona para uso polimórfico.

    @Override
    public String getTipo() {
        return "Veterinario";
    }
}
