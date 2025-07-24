package DTO;

public class ConsultaDTO {
    private String fecha;
    private String mascota;
    private String motivo;
    private String diagnostico;
    private String tratamiento;

    public ConsultaDTO(String fecha, String mascota, String motivo, String diagnostico, String tratamiento) {
        this.fecha = fecha;
        this.mascota = mascota;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getMascota() { return mascota; }
    public void setMascota(String mascota) { this.mascota = mascota; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }
}
