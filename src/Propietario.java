import java.util.ArrayList;
//Se mantuvieron los atributos privados para cumplir con el principio de encapsulamiento
public class Propietario extends Persona {
    //El extends despues de propietario es porque este usa la info ya puesta en Persona
    private String documento;
    private String telefono;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public Propietario(String nombre, String documento, String telefono) {
        super(nombre); // usa constructor de Persona
        setDocumento(documento);
        setTelefono(telefono);
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

    public String getDocumento() {
        return documento;
    }

    public String getTelefono() {
        return telefono;
    }

    // === Métodos de mascotas ===
    /**
     * Agrega una mascota a la lista del propietario.
     */
    public void agregarMascota(Mascota m) {
        if (m != null) {
            mascotas.add(m);
        }
    }


    // === Métodos de visualización ===
    /**
     * Muestra los datos básicos del propietario.
     */
    @Override
    public void mostrarDatos() {
        super.mostrarDatos(); // muestra nombre
        System.out.println("🆔 Documento: " + documento);
        System.out.println("📞 Teléfono: " + telefono);
    }
    /**
     * Muestra la información completa del propietario y su historial de mascotas.
     */
    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        mostrarDatos(); // usa método polimórfico
        System.out.println();
        for (Mascota m : mascotas) {
            m.mostrarHistorial();
        }
    }
}