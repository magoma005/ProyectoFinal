package DAO;

// Importación de modelos necesarios
import modelo.Consulta;
import modelo.Mascota;
import modelo.Vacuna;

import java.util.ArrayList;
import java.util.Scanner;

public class CrudMascotas {

    // Lista que almacenará todas las mascotas registradas
    private ArrayList<Mascota> mascotas = new ArrayList<>();

    // Scanner para la lectura de entradas por consola
    private final Scanner sc = new Scanner(System.in);

    // Método principal que muestra el menú del CRUD y ejecuta opciones
    public void menuCrud() {
        int opcion;
        do {
            mostrarMenuPrincipal(); // Muestra las opciones
            opcion = leerEntero("Opción: "); // Lee opción ingresada por el usuario

            // Se ejecuta la opción correspondiente
            switch (opcion) {
                case 1 -> agregarMascota();       // Agregar una nueva mascota
                case 2 -> listarMascotas();       // Mostrar lista de mascotas
                case 3 -> actualizarMascota();    // Modificar datos de una mascota
                case 4 -> eliminarMascota();      // Eliminar mascota por nombre
                case 5 -> registrarConsulta();    // Registrar una consulta médica
                case 6 -> registrarVacuna();      // Registrar vacunación
                //case 7 -> agendarCita();        // Agendar cita
                case 7 -> verHistorialMascota();  // Ver eventos clínicos de la mascota
                case 8 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida."); // Opción inválida
            }

        } while (opcion != 8); // El bucle continúa hasta que se seleccione "Salir"
    }

    // Muestra el menú de opciones del sistema
    private void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ VETERINARIO ===");
        System.out.println("1. Agregar Mascota");
        System.out.println("2. Listar Mascotas");
        System.out.println("3. Actualizar Mascota");
        System.out.println("4. Eliminar Mascota");
        System.out.println("5. Registrar Consulta");
        System.out.println("6. Registrar Vacuna");
        System.out.println("7. Agendar Cita");
        System.out.println("8. Ver Historial de Mascota");
        System.out.println("9. Salir");
    }

    // === CRUD DE MASCOTAS ===

    // Permite ingresar una nueva mascota al sistema
    private void agregarMascota() {
        System.out.println("--- Agregar Mascota ---");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Especie: ");
        int edad = leerEntero("Edad: ");
        String clave = leerTexto("Clave: "); // Contraseña asociada (si aplica)
        Mascota nueva = new Mascota(nombre, especie, edad, clave); // Crea mascota
        mascotas.add(nueva); // Añade a la lista
        System.out.println("✅ Mascota agregada correctamente.");
    }

    // Muestra todas las mascotas registradas
    private void listarMascotas() {
        System.out.println("--- Lista de Mascotas ---");
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            // Imprime los datos de cada mascota
            mascotas.forEach(m -> System.out.println("- Nombre: " + m.getNombre()
                    + ", Especie: " + m.getEspecie()
                    + ", Edad: " + m.getEdad()));
        }
    }

    // Actualiza los datos de una mascota según su nombre
    private void actualizarMascota() {
        System.out.println("--- Actualizar Mascota ---");
        String buscado = leerTexto("Nombre de la mascota a actualizar: ");
        Mascota mascota = buscarMascota(buscado); // Busca mascota
        if (mascota != null) {
            mascota.setNombre(leerTexto("Nuevo nombre: "));
            mascota.setEspecie(leerTexto("Nueva especie: "));
            mascota.setEdad(leerEntero("Nueva edad: "));
            System.out.println("✅ Mascota actualizada correctamente.");
        } else {
            System.out.println("⚠️ Mascota no encontrada.");
        }
    }

    // Elimina una mascota por nombre
    private void eliminarMascota() {
        System.out.println("--- Eliminar Mascota ---");
        String nombreEliminar = leerTexto("Nombre de la mascota a eliminar: ");
        boolean eliminado = mascotas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombreEliminar));
        System.out.println(eliminado ? "✅ Mascota eliminada correctamente."
                : "⚠️ Mascota no encontrada.");
    }

    // === REGISTRO DE EVENTOS CLÍNICOS ===

    // Registra una consulta médica para una mascota seleccionada
    private void registrarConsulta() {
        System.out.println("--- Registrar Consulta ---");
        Mascota m = seleccionarMascota(); // Selecciona mascota
        if (m != null) {
            String fecha = leerTexto("Fecha de consulta (YYYY-MM-DD): ");
            String motivo = leerTexto("Motivo: ");
            String diagnostico = leerTexto("Diagnóstico: ");
            String tratamiento = leerTexto("Tratamiento: ");
            String medicamentos = leerTexto("Medicamentos: ");

            // Crea objeto consulta con los datos y lo agrega al historial
            Consulta c = new Consulta(fecha, motivo, diagnostico, tratamiento, medicamentos, m.getNombre(), "Consulta General", "Sin comentario");
            m.agregarEvento(c);
            System.out.println("✅ Consulta registrada exitosamente.");
        }
    }

    // Registra la vacunación de una mascota
    private void registrarVacuna() {
        System.out.println("--- Registrar Vacuna ---");
        Mascota m = seleccionarMascota(); // Selecciona mascota
        if (m != null) {
            String fecha = leerTexto("Fecha de vacunación (YYYY-MM-DD): ");
            String tipo = leerTexto("Tipo de vacuna: ");
            String lote = leerTexto("Lote de la vacuna: ");
            String proximaDosis = leerTexto("Próxima dosis: ");

            // Crea objeto vacuna y lo agrega al historial
            Vacuna v = new Vacuna(fecha, tipo, lote, proximaDosis);
            m.agregarEvento(v);

            System.out.println("✅ Vacuna registrada exitosamente.");
        }
    }

    // Método para agendar citas
    /*
    private void agendarCita() {
        System.out.println("--- Agendar Cita ---");
        Mascota m = seleccionarMascota();
        if (m != null) {
            String fecha = leerTexto("Fecha de cita (YYYY-MM-DD): ");
            String hora = leerTexto("Hora de cita (HH:MM): ");
            String veterinario = leerTexto("Veterinario: ");
            String estado = leerTexto("Estado de la cita (Agendada/Cancelada/etc): ");

            Cita c = new Cita(fecha, hora, veterinario, estado);
            m.agregarEvento(c);

            System.out.println("✅ Cita agendada exitosamente.");
        }
    }
    */

    // Muestra el historial completo de eventos clínicos de una mascota
    private void verHistorialMascota() {
        System.out.println("--- Historial de Mascota ---");
        Mascota m = seleccionarMascota();
        if (m != null) {
            m.mostrarHistorial();
        }
    }

    // === MÉTODOS DE UTILIDAD ===

    // Busca una mascota por nombre (ignorando mayúsculas/minúsculas)
    private Mascota buscarMascota(String nombre) {
        return mascotas.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Permite seleccionar una mascota pidiendo su nombre al usuario
    private Mascota seleccionarMascota() {
        String nombre = leerTexto("Nombre de la mascota: ");
        Mascota m = buscarMascota(nombre);
        if (m == null) {
            System.out.println("⚠️ Mascota no encontrada.");
        }
        return m;
    }

    // Lee texto desde consola con mensaje personalizado
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    // Lee un número entero desde consola y controla errores
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Ingresa un número válido.");
            }
        }
    }
}


