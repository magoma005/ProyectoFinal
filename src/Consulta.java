import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Consulta {
    private String codigo;  // Ahora es String, generado automáticamente
    // private int fecha;
    //Se cambió 'fecha' de int a LocalDate para mejor manejo de fechas.
    private LocalDate fecha;
    private Veterinario veterinario;

    public Consulta(String fechaTexto, Veterinario veterinario) {
        //*this.codigo = codigo;
        // this.fecha = fecha;
        // this.veterinario = veterinario;*/

        //Validación y conversión de fecha

        // Código generado automáticamente
        //Se cambió el tipo de 'codigo' a String y se genera automáticamente con IDGenerator.
        this.codigo = IDGenerator.generarCodigoConsulta();

        // Validación y conversión de fecha
        setFecha(fechaTexto);

        // Validación de veterinario
        setVeterinario(veterinario);
    }

    //Se actualizó el método mostrarConsulta() para incluir formato y emojis.

    public void mostrarConsulta() {
        System.out.println("🔢 Código de la consulta: " + codigo);
        System.out.println("📅 Fecha: " + fecha);
        if (veterinario != null) {
            veterinario.mostrarPerfil();
        } else {
            System.out.println("Sin veterinario asignado.");
        }
        System.out.println("--------------------------");
    }

    //Se agregaron setters con validación para fecha y veterinario.

public void setFecha(String fechaTexto) {
    try {
        this.fecha = LocalDate.parse(fechaTexto); // formato YYYY-MM-DD
    } catch (DateTimeParseException e) {
        throw new IllegalArgumentException("Formato de fecha inválido. Usa YYYY-MM-DD");
    }
}

public void setVeterinario(Veterinario veterinario) {
    if (veterinario == null) {
        throw new IllegalArgumentException("Se requiere un veterinario para la consulta.");
    }
    this.veterinario = veterinario;
}}
