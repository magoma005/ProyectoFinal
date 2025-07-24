/*package modelo;

//Clase que representa una cita agendada para la mascota. Extiende EventoClinico e implementa su método abstracto mostrarDetalle().

public class Cita extends EventoClinico {
    private String hora;
    private String veterinario;
    private String estado;

    public Cita(String fecha, String hora, String veterinario, String estado) {
        super(fecha);
        //setServicio(servicio);
        setHora(hora);
        setVeterinario(veterinario);
        setEstado(estado);
    }

    //Setters
    public void setHora(String hora) {
        if (hora == null || hora.isBlank()) {
            throw new IllegalArgumentException("La hora no puede estar vacío.");
        }
        this.hora = hora;
    }

    public void setVeterinario(String veterinario) {
        if (veterinario == null || veterinario.isBlank()) {
            throw new IllegalArgumentException("El nombre del veterinario no puede estar vacío.");
        }
        this.veterinario = veterinario;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado de la cita no puede estar vacía.");
        }
        this.estado = estado;
    }

    //Getters
    public String getHora() {
        return hora;
    }

    public String getVeterinario() {
        return veterinario;
    }

    public String getEstado() {
        return estado;
    }

    /**public void setServicio(String servicio) {
        if (servicio == null || servicio.isBlank()) {
            throw new IllegalArgumentException("El servicio no puede estar vacío.");
        }
        this.servicio = servicio;
    }

    //Getter

    public String getServicio() {
        return servicio;
    }

    //Implementación de método abstracto

    @Override
    public void mostrarDetalle() {
            System.out.println("[Cita] " + getFecha() + " " + hora);
            System.out.println("Veterinario: " + veterinario);
            System.out.println("Estado: " + estado);
            System.out.println();
        }
}

*/

