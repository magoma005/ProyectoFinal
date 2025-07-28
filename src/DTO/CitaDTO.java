package DTO;

import java.io.Serializable;

// Clase DTO que representa una cita entre una mascota y un veterinario
// Implementa Serializable para permitir su guardado en archivos binarios
public class CitaDTO implements Serializable {

    // Versión de serialización
    private static final long serialVersionUID = 1L;

    // === Atributos de la cita ===
    private String fecha;        // Fecha de la cita (ej: "2025-08-01")
    private String hora;         // Hora de la cita (ej: "14:30")
    private String mascota;      // Nombre o identificador de la mascota
    private String veterinario;  // Nombre o identificador del veterinario

    // === Constructor ===
    public CitaDTO(String fecha, String hora, String mascota, String veterinario) {
        this.fecha = fecha;
        this.hora = hora;
        this.mascota = mascota;
        this.veterinario = veterinario;
    }

    // === Métodos Getter y Setter ===
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getMascota() { return mascota; }
    public void setMascota(String mascota) { this.mascota = mascota; }
    public String getVeterinario() { return veterinario; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }
}


