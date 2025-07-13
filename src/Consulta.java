import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consulta {
    private String codigo;
    private LocalDate fecha;
    private String mascota;
    private String servicio;
    private String comentario;

    public Consulta(String fechaTexto, String mascota, String servicio, String comentario) {
        this.codigo = IDGenerator.generarCodigoConsulta();
        setFecha(fechaTexto);
        this.mascota = mascota;
        this.servicio = servicio;
        this.comentario = comentario;
    }

    public void mostrarConsulta() {
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
}
