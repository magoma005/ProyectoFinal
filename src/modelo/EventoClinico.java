package modelo;
 //Clase abstracta que representa cualquier evento clínico realizado a una mascota. Es la superclase de Consulta, Vacuna y Cita, permitiendo polimorfismo en registros clínicos.

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class EventoClinico {
    protected LocalDate fecha;

    // Este Constructor vacío es necesario para subclases que no pasen fecha en super()
    public EventoClinico() {}

    public EventoClinico(String fecha) {
        setFecha(fecha);
    }

    // Set con String
    public void setFecha(String fecha) {
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("La fecha no puede estar vacía.");
        }
        try {
            this.fecha = LocalDate.parse(fecha);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Usa AAAA-MM-DD.");
        }
    }

    // Set con LocalDate
    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha.toString();
    }

    public LocalDate getFechaLocalDate() {
        return fecha;
    }

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
