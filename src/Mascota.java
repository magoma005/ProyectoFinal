public class Mascota {
    private String nombre;
    private String especie;
    private int edad;
    private String clave; // Clave para seguridad
    private Historial historial; // Se reemplazó ArrayList<Consulta> por la clase Historial para mejor encapsulamiento.

    public Mascota(String nombre, String especie, int edad) {
        // Refactor: usar setters + historial
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        this.historial = new Historial();
    }

    public Mascota(String nombre, String especie, int edad, String clave) {
        //Constructor con clave
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setClave(clave);
        this.historial = new Historial();
    }

    public void agregarConsulta(Consulta consulta) {
        historial.agregarConsulta(consulta); // delegamos al historial
    }

    public void mostrarHistorial() {
        System.out.println("📋 Mascota: " + nombre + " | Especie: " + especie + " | Edad: " + edad + " años");
        System.out.println("Historial de consultas:");
        historial.mostrarConsultas();
    }

    // === Setters con validación ===

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

    public void setClave(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("La clave no puede estar vacía.");
        }
        this.clave = clave;
    }

    // === Getters ===

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public int getEdad() {
        return edad;
    }

    public String getClave() {
        return clave;
    }

}
