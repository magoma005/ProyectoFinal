//Se creó la clase Historial para delegar el manejo de consultas de Mascota.

import java.util.ArrayList;

public class Historial {
    private ArrayList<Consulta> consultas;

    public Historial() {
        this.consultas = new ArrayList<>();
    }

    //Se encapsula la lista de consultas.

    public void agregarConsulta(Consulta consulta) {
        if (consulta != null) {
            consultas.add(consulta);
        }
    }

    //Método mostrarConsultas() recorre e imprime cada consulta de forma ordenada.

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
}



