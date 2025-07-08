import java.util.ArrayList;
//Se mantuvieron los atributos privados para cumplir con el principio de encapsulamiento
public class Propietario {
    private String nombre;
    private String documento;
    private String telefono;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    /* Código original sin validación
    public Propietario(String nombre, long documento, long telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }
    */

    // Usamos setters con validación para centralizar la lógica
    public Propietario(String nombre, String documento, String telefono) {
        setNombre(nombre);
        setDocumento(documento);
        setTelefono(telefono);
        this.mascotas = new ArrayList<>();
    }

    //Se agregaron setters con validación para nombre, documento y teléfono
    //Se agregaron validaciones mínimas de negocio (ej. nombre no vacío, documento y teléfono con mínimo de dígitos).
    // Setter con validación de nombre
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setDocumento(String documento) {
        if (documento == null || documento.length() < 5) {
            throw new IllegalArgumentException("Documento inválido, debe tener mínimo 5 caracteres.");
        }
        this.documento = documento;
    }


    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 7) {
            throw new IllegalArgumentException("Teléfono inválido, debe tener mínimo 7 caracteres.");
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
     /* Setter de mascotas eliminado para proteger la colección interna
    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
    */
}

