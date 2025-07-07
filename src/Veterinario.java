public class Veterinario {
    private String nombre;
    private String especialidad;

    public Veterinario(String nombre, String especialidad) {
        /*this.nombre = nombre;
        this.especialidad = especialidad;*/
        //Validación de argumentos
        setNombre(nombre);
        setEspecialidad(especialidad);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        // Validación: Nombre no puede ser vacío
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
        }
        this.nombre = nombre;
    }
    public String getEspecialidad() {
        return especialidad;
    }
    public void setEspecialidad(String especialidad) {
        // ✅ Validación: especialidad no puede ser vacía
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía.");
        }
        this.especialidad = especialidad;
    }
    public void mostrarPerfil() {
        System.out.println("Veterinario: " + nombre + " | Especialidad: " + especialidad);
    }
}

/*
Cambios:
- Se agregaron setters con validaciones para nombre y especialidad, asegurando que no sean nulos ni vacíos.
- El constructor ahora usa los setters para aplicar las validaciones al crear el objeto.
- Se mantuvieron los getters originales.
- Se agregó el método mostrarPerfil() para imprimir la información del veterinario de manera estructurada.
Resultado: mejor encapsulamiento, validación de atributos y reutilización de lógica en el constructor.
*/

//Veterinario son solo gets y conexiones con consulta, es importante empezar desde veterinario a desarrollar el codigo, pues es la parte mas sencilla

