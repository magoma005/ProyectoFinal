package DTO;

public class VacunaDTO {
    private String nombreVacuna;
    private String fechaAplicacion;
    private String mascota;

    public VacunaDTO(String nombreVacuna, String fechaAplicacion, String mascota) {
        this.nombreVacuna = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.mascota = mascota;
    }

    public String getNombreVacuna() { return nombreVacuna; }
    public void setNombreVacuna(String nombreVacuna) { this.nombreVacuna = nombreVacuna; }

    public String getFechaAplicacion() { return fechaAplicacion; }
    public void setFechaAplicacion(String fechaAplicacion) { this.fechaAplicacion = fechaAplicacion; }

    public String getMascota() { return mascota; }
    public void setMascota(String mascota) { this.mascota = mascota; }
}

