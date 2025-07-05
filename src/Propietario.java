import java.util.ArrayList;

public class Propietario {
    private String nombre;
    private long documento;
    private long telefono;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public Propietario(String nombre, long documento, long telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    // Usamos long en lugar de int porque teléfono y documento pueden tener valores muy grandes, en los cuales int tiene un límite en Java.

    public void agregarMascota(Mascota m) {
        mascotas.add(m);
    }

    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        System.out.println("\uD83D\uDC64 Propietario: " + nombre);
        // \uD83D\uDC64 significa emoji de persona
        System.out.println("\uD83C\uDD94 Documento: " + documento);
        // \uD83C\uDD94 significa emoji de ID
        System.out.println("\uD83D\uDCDE Numero de Telefono: " + telefono);
        // \uD83D\uDCDE significa emoji de telefono
        System.out.println();
        for (Mascota m : mascotas) {
            m.mostrarHistorial();
        }
    }
}

//Esta es la clase principal, todo el programa consiste en una conexion, y esta conexion, conectara con el MAIN a continuacion

