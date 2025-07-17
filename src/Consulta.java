/**
 * Extiende EventoClinico e implementa su método abstracto mostrarDetalle().
 */

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consulta extends EventoClinico {
    //Atributos
    private String motivo;

    /**
     * Constructor de Consulta.
     */
    public Consulta(String fecha, String motivo) {
        super(fecha);
        setMotivo(motivo);
    }

    // === Setter con validación ===

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo no puede estar vacío.");
        }
        this.motivo = motivo;
    }


    //Getter

    public String getMotivo() {
        return motivo;
    }

    // === Implementación de método abstracto ===

    @Override
    public void mostrarDetalle() {
        System.out.println("📅 Consulta en fecha: " + getFecha());
        System.out.println("📝 Motivo: " + motivo);
    }
}

    /**public void mostrarConsulta() {
        System.out.println("🔢 Código de la consulta: " + codigo);
        System.out.println("🐾 Mascota: " + mascota);
        System.out.println("📅 Fecha: " + fecha);
        System.out.println("🩺 Servicio: " + servicio);
        System.out.println("💬 Comentario: " + comentario);
        System.out.println("--------------------------");
    }

    public void setFecha(String fechaTexto) {
        try {
            this.fecha = LocalDate.parse(fechaTexto); // formato YYYY-MM-DD
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Usa YYYY-MM-DD");
        }
    }

    public String getCodigo() { return codigo; }
    public LocalDate getFecha() { return fecha; }
    public String getMascota() { return mascota; }
    public String getServicio() { return servicio; }
    public String getComentario() { return comentario; }
}**/