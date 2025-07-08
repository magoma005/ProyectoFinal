public class Veterinario {
    private String nombre;
    private String especialidad;

    public Veterinario(String nombre, String especialidad) {
        //this.nombre = nombre;
        //this.especialidad = especialidad;
        //El constructor ahora usa los setters para aplicar las validaciones al crear el objeto.

        //Validación de argumentos
        setNombre(nombre);
        setEspecialidad(especialidad);
    }

    //Se agregaron setters con validaciones para nombre y especialidad, asegurando que no sean nulos ni vacíos.
    public void setNombre(String nombre) {
        // Validación: Nombre no puede ser vacío
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
        }
        this.nombre = nombre;
    }
    public void setEspecialidad(String especialidad) {
        //Validación: especialidad no puede ser vacía
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }

    // Se agregó el método mostrarPerfil() para imprimir la información del veterinario de manera estructurada.
    public void mostrarPerfil() {
        System.out.println("Veterinario: " + nombre + " | Especialidad: " + especialidad);
    }
}
