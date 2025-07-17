/**
 * Clase abstracta que representa cualquier evento clínico realizado a una mascota.
 * Es la superclase de Consulta, Vacuna y Cita, permitiendo polimorfismo en registros clínicos.
 * Contiene información común como la fecha.
 */
public abstract class EventoClinico {
    private String fecha;

    //Constructor
    public EventoClinico(String fecha) {
        setFecha(fecha);
    }

    // Setter con validación

    public void setFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        this.fecha = fecha;
    }

    //Getter

    public String getFecha() {
        return fecha;
    }

    /**
     * Método abstracto que muestra los detalles del evento clínico.
     * Implementado en subclases como Consulta, Vacuna y Cita.
     */
    public abstract void mostrarDetalle();
}

/*
────────────────────────────────────────────
💡 ¿Por qué EventoClinico es abstracta y no Persona?
────────────────────────────────────────────

✅ La clase EventoClinico es abstracta porque:

- Representa un concepto general de evento clínico (consulta, cita, vacuna).
- No tiene sentido crear un "EventoClinico" genérico en el sistema.
  Siempre se agendan eventos concretos como Consulta o Vacuna.
- Contiene el método abstracto mostrarDetalle(), que obliga a sus subclases
  a implementar su propia versión según sus necesidades.

────────────────────────────────────────────
❌ Persona no es abstracta aquí porque:

- En este modelo, Persona no se implementó como superclase.
- Propietario y Veterinario se crearon como clases independientes,
  cada una con sus propios atributos y métodos.
- Si se creara una superclase Persona y Propietario y Veterinario heredaran de ella,
  se evaluaría si Persona debería ser abstracta o no.

⚠️ En este caso, EventoClinico es abstracta para evitar crear instancias genéricas
sin propósito claro, mientras que Persona se usa como clases concretas.

────────────────────────────────────────────
*/
