package DTO;

public class ConsultaDTO {
    private String fecha;
    private String mascota;
    private String servicio;
    private String diagnostico;

    public ConsultaDTO(String fecha, String mascota, String servicio, String diagnostico) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.servicio = servicio;
        this.diagnostico = diagnostico;
    }

    public String getFecha() { return fecha; }
    public String getMascota() { return mascota; }
    public String getServicio() { return servicio; }
    public String getDiagnostico() { return diagnostico; }

    // Compatibilidad
    public String getMotivo() { return diagnostico; }
    public String getTratamiento() { return diagnostico; }
}
