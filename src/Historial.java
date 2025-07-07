import java.util.ArrayList;

public class Historial {
    private ArrayList<Consulta> consultas;

    public Historial() {
        this.consultas = new ArrayList<>();
    }
    public void agregarConsulta(Consulta consulta) {
        if (consulta != null) {
            consultas.add(consulta);
        }
    }
    public void mostrarConsultas() {
        if (consultas.isEmpty()) {
            System.out.println("⚠️ Sin consultas registradas.");
        } else {
            for (Consulta c : consultas) {
                c.mostrarConsulta();
                System.out.println("--------------------------");
            }
        }
    }
    public ArrayList<Consulta> getConsultas() {
        // ✅ Retorna copia defensiva para proteger la colección interna
        return new ArrayList<>(consultas);
    }
}

/*
✅ Cambios y explicación:
- Se creó la clase Historial para delegar el manejo de consultas de Mascota.
- Se encapsula la lista de consultas.
- Se usa una copia defensiva en getConsultas() para proteger el ArrayList interno.
- Método mostrarConsultas() recorre e imprime cada consulta de forma ordenada.
*/



