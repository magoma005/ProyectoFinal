package DTO;

public class CitaDTO {
    private String fecha;
    private String hora;
    private String mascota;
    private String veterinario;

    public CitaDTO(String fecha, String hora, String mascota, String veterinario) {
        this.fecha = fecha;
        this.hora = hora;
        this.mascota = mascota;
        this.veterinario = veterinario;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getMascota() { return mascota; }
    public void setMascota(String mascota) { this.mascota = mascota; }

    public String getVeterinario() { return veterinario; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
}

