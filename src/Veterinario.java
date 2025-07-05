public class Veterinario {
    private String nombre;
    private String especialidad;

    public Veterinario(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    public String getNombre () {
        return nombre;
    }

    public String getEspecialidad () {
        return especialidad;
    }
}

//Veterinario son solo gets y conexiones con consulta, es importante empezar desde veterinario a desarrollar el codigo, pues es la parte mas sencilla

