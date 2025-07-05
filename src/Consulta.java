public class Consulta {
    private int codigo;
    private int fecha;
    private Veterinario veterinario;

    public Consulta(int codigo, int fecha, Veterinario veterinario){
        this.codigo = codigo;
        this.fecha = fecha;
        this.veterinario = veterinario;
    }

    public void mostrarDetalleConsulta(){
        System.out.println("Codigo de la consulta: " + codigo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Veterinario: " + veterinario.getNombre() + " | Especialidad del Veterinario: " + veterinario.getEspecialidad());
        System.out.println("--------------------------");
    }
}

//Aqui le datos uso a los datos (gets) ya definidos en Veterinario, osea, su nombre y especialidad
//Tambien guardamos info acerca de la consulta
