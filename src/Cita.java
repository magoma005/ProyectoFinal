/**
 * Clase que representa una cita agendada para la mascota.
 * Extiende EventoClinico e implementa su método abstracto mostrarDetalle().
 * Incluye el servicio solicitado.
 */

public class Cita extends EventoClinico {
    private String servicio;

    //Constructor
    public Cita(String fecha, String servicio) {
        super(fecha);
        setServicio(servicio);
    }

    //Setter con validación

    public void setServicio(String servicio) {
        if (servicio == null || servicio.isBlank()) {
            throw new IllegalArgumentException("El servicio no puede estar vacío.");
        }
        this.servicio = servicio;
    }

    //Getter

    public String getServicio() {
        return servicio;
    }

    // === Implementación de método abstracto ===

    @Override
    public void mostrarDetalle() {
        System.out.println("📅 Cita para el " + getFecha());
        System.out.println("🔧 Servicio: " + servicio);
    }
}
