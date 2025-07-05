import java.util.ArrayList;

public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private ArrayList<Consulta> consultas = new ArrayList<>();

    public Mascota(String nombre, String especie, int edad) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
    }

    public void agregarConsulta(Consulta c) {
        consultas.add(c);
    }

    public void mostrarHistorial() {
        System.out.println("\uD83D\uDC36 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        //el \uD83D\uDC36 significa emoji de perrito
        System.out.println("Historial de consultas:");
        for (Consulta c : consultas) {
            c.mostrarDetalleConsulta();
        }
    }
}
