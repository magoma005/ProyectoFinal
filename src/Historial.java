import java.util.ArrayList;

/**
 * Clase Historial.
 * Administra todos los eventos clínicos de una mascota:
 * consultas, vacunas y citas.
 */
public class Historial {

    // Lista general de eventos clínicos
    private ArrayList<EventoClinico> eventos;

    public Historial() {
        this.eventos = new ArrayList<>();
    }

    /**
     * Agrega un evento clínico al historial.
     */
    public void agregarEvento(EventoClinico evento) {
        if (evento != null) {
            eventos.add(evento);
        }
    }

    /**
     * Muestra todos los eventos clínicos registrados usando polimorfismo.
     */
    public void mostrarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("⚠️ Sin eventos clínicos registrados.");
        } else {
            System.out.println("📋 Historial clínico:");
            for (EventoClinico e : eventos) {
                e.mostrarDetalle(); // Llama al método override según su tipo
                System.out.println("--------------------------");
            }
        }
    }

    public ArrayList<EventoClinico> getEventos() {
        return eventos;
    }
}
