import java.util.ArrayList;

public class Propietario {
    private String nombre;
    private long documento;
    private long telefono;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    /* Código original sin validación
    public Propietario(String nombre, long documento, long telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
    */

    // Usamos setters con validación para centralizar la lógica
    public Propietario(String nombre, long documento, long telefono) {
        setNombre(nombre);
        setDocumento(documento);
        setTelefono(telefono);
        this.mascotas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    // Setter con validación de nombre
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    // Setter con validación de documento
    public void setDocumento(long documento) {
        if (String.valueOf(documento).length() < 5) {
            throw new IllegalArgumentException("Documento inválido, debe tener mínimo 5 dígitos.");
        }
        this.documento = documento;
    }

    public long getTelefono() {
        return telefono;
    }

    //Setter con validación de teléfono
    public void setTelefono(long telefono) {
        if (String.valueOf(telefono).length() < 7) {
            throw new IllegalArgumentException("Teléfono inválido, debe tener mínimo 7 dígitos.");
        }
        this.telefono = telefono;
    }

    // Usamos long en lugar de int porque teléfono y documento pueden tener valores muy grandes, en los cuales int tiene un límite en Java.

    public void agregarMascota(Mascota m) {
        if (m != null) {
            mascotas.add(m);
        }
    }


    //Método para mostrar la información completa
    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        System.out.println("\uD83D\uDC64 Propietario: " + nombre);
        System.out.println("\uD83C\uDD94 Documento: " + documento);
        System.out.println("\uD83D\uDCDE Numero de Telefono: " + telefono);
        System.out.println();

        for (Mascota m : mascotas) {
            m.mostrarHistorial();
        }
    }
    public ArrayList<Mascota> getMascotas() {
        return new ArrayList<>(mascotas);
    }
     /* Setter de mascotas eliminado para proteger la colección interna
    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    */
}

//Esta es la clase principal, todo el programa consiste en una conexion, y esta conexion, conectara con el MAIN a continuacion

