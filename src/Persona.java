/**
 * Clase que representa a cualquier persona en el sistema,
 * ya sea propietario, veterinario u otro tipo de usuario futuro.
 * Contiene atributos y métodos comunes.
 *
 */
public class Persona {
    private String nombre;

    public Persona(String nombre) {
        setNombre(nombre);
    }

    // === Setter con validación ===

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    // === Getter ===

    public String getNombre() {
        return nombre;
    }

    // === Método mostrarDatos ===

    /**
     * Muestra los datos básicos de la persona en consola.
     */
    public void mostrarDatos() {
        System.out.println("👤 Nombre: " + nombre);
    }
}

