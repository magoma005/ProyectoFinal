package modelo;

import util.IDGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consulta extends EventoClinico {
    private String codigo;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private String medicamentos;
    private String mascota;
    private String servicio;
    private String comentario;

    public Consulta(String fechaTexto, String motivo, String diagnostico, String tratamiento, String medicamentos, String mascota, String servicio, String comentario) {
        setFecha(fechaTexto);
        setMotivo(motivo);
        setDiagnostico(diagnostico);
        setTratamiento(tratamiento);
        setMedicamentos(medicamentos);
        setMascota(mascota);
        setServicio(servicio);
        setComentario(comentario);
        this.codigo = IDGenerator.generarCodigoConsulta();
    }

    // Constructor simplificado para agendar consulta desde GUI.
    public Consulta(String fechaTexto, String mascota, String servicio, String comentario) {
        setFecha(fechaTexto);
        setMascota(mascota);
        setServicio(servicio);
        setComentario(comentario);
        this.codigo = IDGenerator.generarCodigoConsulta();
    }

    public void setFecha(String fechaTexto) {
        try {
            LocalDate fechaParseada = LocalDate.parse(fechaTexto); // formato YYYY-MM-DD
            super.setFecha(fechaParseada);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Usa YYYY-MM-DD");
        }
    }

    public void setMotivo(String motivo) {
        if (motivo == null || motivo.isBlank()) throw new IllegalArgumentException("El motivo no puede estar vacío.");
        this.motivo = motivo;
    }

    public void setDiagnostico(String diagnostico) {
        if (diagnostico == null || diagnostico.isBlank()) throw new IllegalArgumentException("El diagnóstico no puede estar vacío.");
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        if (tratamiento == null || tratamiento.isBlank()) throw new IllegalArgumentException("El tratamiento no puede estar vacío.");
        this.tratamiento = tratamiento;
    }

    public void setMedicamentos(String medicamentos) {
        if (medicamentos == null || medicamentos.isBlank()) throw new IllegalArgumentException("El medicamento no puede estar vacío.");
        this.medicamentos = medicamentos;
    }

    public void setMascota(String mascota) {
        if (mascota == null || mascota.isBlank()) throw new IllegalArgumentException("La mascota no puede estar vacía.");
        this.mascota = mascota;
    }

    public void setServicio(String servicio) {
        if (servicio == null || servicio.isBlank()) throw new IllegalArgumentException("El servicio no puede estar vacío.");
        this.servicio = servicio;
    }

    public void setComentario(String comentario) {
        if (comentario == null || comentario.isBlank()) throw new IllegalArgumentException("El comentario no puede estar vacío.");
        this.comentario = comentario;
    }

    // === Getters ===
    public String getMotivo() { return motivo; }
    public String getDiagnostico() { return diagnostico; }
    public String getTratamiento() { return tratamiento; }
    public String getMedicamentos() { return medicamentos; }
    public String getMascota() { return mascota; }
    public String getServicio() { return servicio; }
    public String getComentario() { return comentario; }
    public String getCodigo() { return codigo; }

    // === Método abstracto implementado ===
    @Override
    public void mostrarDetalle() {
        System.out.println("[Consulta] " + getFecha() + " - " + motivo);
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Tratamiento: " + tratamiento);
        System.out.println("Medicamentos: " + medicamentos);
        System.out.println();
    }

    /**
     * Convierte la consulta en línea CSV para guardar en archivo.
     */
    public String toLineaArchivo() {
        return codigo + "," +
                getFecha().toString() + "," +
                safe(motivo) + "," +
                safe(diagnostico) + "," +
                safe(tratamiento) + "," +
                safe(medicamentos) + "," +
                safe(mascota) + "," +
                safe(servicio) + "," +
                safe(comentario);
    }

    /**
     * Crea un objeto Consulta a partir de una línea CSV.
     * Retorna null si la línea es inválida.
     */
    public static Consulta desdeLineaArchivo(String linea) {
        String[] partes = linea.split(",", -1); // -1 para incluir campos vacíos
        if (partes.length != 9) return null;
        try {
            Consulta c = new Consulta(
                    partes[1], // fecha
                    partes[2], // motivo
                    partes[3], // diagnostico
                    partes[4], // tratamiento
                    partes[5], // medicamentos
                    partes[6], // mascota
                    partes[7], // servicio
                    partes[8]  // comentario
            );
            c.codigo = partes[0]; // Usa el código original leído del archivo
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    //Asegura que no haya nulls en la escritura del archivo.

    private static String safe(String s) {
        return (s == null) ? "" : s;
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