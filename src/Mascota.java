public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    //private ArrayList<Consulta> consultas = new ArrayList<>();
    private Historial historial;

    public Mascota(String nombre, String especie, int edad) {
        //*this.nombre = nombre;
        //this.especie = especie;
        //this.edad = edad;
        //this.consultas = new ArrayList<>();*/
        //Refactor: usar setters + historial
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        this.historial = new Historial();
    }

    public void agregarConsulta(Consulta consulta) {
        // consultas.add(consulta);
        historial.agregarConsulta(consulta); // ✅ delegamos al historial
    }

    public void mostrarHistorial() {
        System.out.println("📋 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        System.out.println("Historial de consultas:");
        historial.mostrarConsultas(); // ✅ nuevo método en Historial
    }

    // Setters con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.isBlank()) {
            throw new IllegalArgumentException("La especie no puede estar vacía.");
        }
        this.especie = especie;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        this.edad = edad;
    }
    // ❌ No se expone el historial directamente para proteger la colección interna
}

/*
Cambios realizados (Refactorización):
- Se reemplazó ArrayList<Consulta> por la clase Historial para mejor encapsulamiento.
- Se agregaron setters con validación para nombre, especie y edad.
- Se usaron setters dentro del constructor para validar al instanciar.
- Se eliminó la posibilidad de exponer o modificar directamente la lista de consultas.
- Se cumplieron las buenas prácticas de diseño limpio y separación de responsabilidades.
*/
