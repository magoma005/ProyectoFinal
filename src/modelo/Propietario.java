package modelo;

import java.util.ArrayList;

public class Propietario extends Persona {
    private String telefono;
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public Propietario(String nombre, String identificacion, String telefono) {
        super(nombre, identificacion); // Usa el constructor de Persona
        setTelefono(telefono);
    }

    // === Getter y Setter ===
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.length() < 7) {
            throw new IllegalArgumentException("📵 Teléfono inválido, debe tener mínimo 7 caracteres.");
        }
        this.telefono = telefono;
    }

    // === Métodos de mascotas ===
    public void agregarMascota(Mascota m) {
        if (m != null) {
            mascotas.add(m);
        }
    }

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    // === Métodos de visualización ===
    @Override
    public void mostrarDatos() {
        super.mostrarDatos();
        System.out.println("📞 Teléfono: " + telefono);
    }

    public void mostrarInformacionCompleta() {
        System.out.println("===== FICHA CLÍNICA =====");
        mostrarDatos(); // polimorfismo
        System.out.println();
        for (Mascota m : mascotas) {
            m.mostrarHistorial();
        }
    }

    @Override
    public String getTipo() {
        return "Propietario";
    }
}
