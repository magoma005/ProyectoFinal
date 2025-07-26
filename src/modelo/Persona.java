package modelo;

import java.io.Serializable;

/**
 * Clase abstracta que representa a cualquier persona en el sistema,
 * ya sea propietario, veterinario u otro tipo de usuario.
 */
public abstract class Persona implements Serializable {
    private String nombre;
    private String identificacion;
    private Mascota mascotaACargo;


    public Persona(String nombre, String identificacion) {
        setNombre(nombre);
        setIdentificacion(identificacion);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isBlank()) {
            throw new IllegalArgumentException("La identificación no puede estar vacía.");
        }
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Mascota getMascotaACargo() {
        return mascotaACargo;
    }

    public void setMascotaACargo(Mascota mascotaACargo) {
        this.mascotaACargo = mascotaACargo;
    }

    //Método abstracto que debe implementar cada subclase para indicar su tipo.

    public abstract String getTipo();

    //Muestra los datos básicos de la persona. Las subclases pueden extender este método.

    public void mostrarDatos() {
        System.out.println("👤 Nombre: " + nombre);
        System.out.println("🆔 Identificación: " + identificacion);
    }

    @Override
    public String toString() {
        return getTipo() + " - " + nombre + " (" + identificacion + ")";
    }
}
