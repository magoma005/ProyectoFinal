/**
 * Clase que representa la aplicación de una vacuna a la mascota.
 * Extiende EventoClinico e implementa su método abstracto mostrarDetalle().
 * Incluye el nombre de la vacuna aplicada.
 */

public class Vacuna extends EventoClinico {
    private String nombreVacuna;

    //Constructor
    public Vacuna(String fecha, String nombreVacuna) {
        super(fecha);
        setNombreVacuna(nombreVacuna);
    }

    //Setter con validación

    public void setNombreVacuna(String nombreVacuna) {
        if (nombreVacuna == null || nombreVacuna.isBlank()) {
            throw new IllegalArgumentException("El nombre de la vacuna no puede estar vacío.");
        }
        this.nombreVacuna = nombreVacuna;
    }

    //Getter

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    // === Implementación de método abstracto ===

    @Override
    public void mostrarDetalle() {
        System.out.println("💉 Vacuna aplicada: " + nombreVacuna);
        System.out.println("📅 Fecha de aplicación: " + getFecha());
    }
}

