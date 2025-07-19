/**
 * Clase que representa la aplicación de una vacuna a la mascota.
 * Extiende EventoClinico e implementa su método abstracto mostrarDetalle().
 * Incluye el nombre de la vacuna aplicada.
 */

public class Vacuna extends EventoClinico {
    private String tipo;
    private String lote;
    private String proximaDosis;

    public Vacuna(String fecha, String tipo, String lote, String proximaDosis) {
        super(fecha);
        setTipo(tipo);
        setLote(lote);
        setProximaDosis(proximaDosis);
    }

    //Setters con su validación

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("El tipo de vacuna no puede estar vacío.");
        }
        this.tipo = tipo;
    }

    public void setLote(String lote) {
        if (lote == null || lote.isBlank()) {
            throw new IllegalArgumentException("El Lote de vacuna no puede estar vacío.");
        }
        this.lote = lote;
    }

    public void setProximaDosis(String proximaDosis) {
        if (proximaDosis == null || proximaDosis.isBlank()) {
            throw new IllegalArgumentException("La info de la proxima dosis no puede estar vacío.");
        }
        this.proximaDosis = proximaDosis;
    }


    //Getter

    public String getTipo() {
        return tipo;
    }

    public String getLote() {
        return lote;
    }

    // === Implementación de método abstracto ===

    @Override
    public void mostrarDetalle() {
        System.out.println("[Vacuna] " + getFecha());
        System.out.println("Tipo: " + tipo);
        System.out.println("Lote: " + lote);
        System.out.println("Próxima dosis: " + proximaDosis);
        System.out.println();
    }
}

