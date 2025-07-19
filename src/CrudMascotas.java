import java.util.ArrayList;
import java.util.Scanner;

public class CrudMascotas {

    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public void menuCrud() {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Opción: ");

            switch (opcion) {
                case 1 -> agregarMascota();
                case 2 -> listarMascotas();
                case 3 -> actualizarMascota();
                case 4 -> eliminarMascota();
                case 5 -> registrarConsulta();
                case 6 -> registrarVacuna();
                case 7 -> agendarCita();
                case 8 -> verHistorialMascota();
                case 9 -> System.out.println("👋 Saliendo del sistema...");
                default -> System.out.println("⚠️ Opción inválida.");
            }

        } while (opcion != 9);
    }

    // MENU
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

    //CRUD MASCOTAS

    private void agregarMascota() {
        System.out.println("--- Agregar Mascota ---");
        String nombre = leerTexto("Nombre: ");
        String especie = leerTexto("Especie: ");
        int edad = leerEntero("Edad: ");
        String clave = leerTexto("Clave: ");
        Mascota nueva = new Mascota(nombre, especie, edad, clave);
        mascotas.add(nueva);
        System.out.println("✅ Mascota agregada correctamente.");
    }

    private void listarMascotas() {
        System.out.println("--- Lista de Mascotas ---");
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            mascotas.forEach(m -> System.out.println("- Nombre: " + m.getNombre()
                    + ", Especie: " + m.getEspecie()
                    + ", Edad: " + m.getEdad()));
        }
    }

    private void actualizarMascota() {
        System.out.println("--- Actualizar Mascota ---");
        String buscado = leerTexto("Nombre de la mascota a actualizar: ");
        Mascota mascota = buscarMascota(buscado);
        if (mascota != null) {
            mascota.setNombre(leerTexto("Nuevo nombre: "));
            mascota.setEspecie(leerTexto("Nueva especie: "));
            mascota.setEdad(leerEntero("Nueva edad: "));
            System.out.println("✅ Mascota actualizada correctamente.");
        } else {
            System.out.println("⚠️ Mascota no encontrada.");
        }
    }

    private void eliminarMascota() {
        System.out.println("--- Eliminar Mascota ---");
        String nombreEliminar = leerTexto("Nombre de la mascota a eliminar: ");
        boolean eliminado = mascotas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombreEliminar));
        System.out.println(eliminado ? "✅ Mascota eliminada correctamente."
                : "⚠️ Mascota no encontrada.");
    }

    //EVENTOS CLINICOS

    private void registrarConsulta() {
        System.out.println("--- Registrar Consulta ---");
        Mascota m = seleccionarMascota();
        if (m != null) {
            String fecha = leerTexto("Fecha de consulta (YYYY-MM-DD): ");
            String motivo = leerTexto("Motivo: ");
            String diagnostico = leerTexto("Diagnóstico: ");
            String tratamiento = leerTexto("Tratamiento: ");
            String medicamentos = leerTexto("Medicamentos: ");
            Consulta c = new Consulta(fecha, motivo, diagnostico, tratamiento, medicamentos, m.getNombre(), "Consulta General", "Sin comentario");
            m.agregarEvento(c);
            System.out.println("✅ Consulta registrada exitosamente.");
        }
    }

    private void registrarVacuna() {
        System.out.println("--- Registrar Vacuna ---");
        Mascota m = seleccionarMascota();
        if (m != null) {
            String fecha = leerTexto("Fecha de vacunación (YYYY-MM-DD): ");
            String tipo = leerTexto("Tipo de vacuna: ");
            String lote = leerTexto("Lote de la vacuna: ");
            String proximaDosis = leerTexto("Próxima dosis: ");

            Vacuna v = new Vacuna(fecha, tipo, lote, proximaDosis);
            m.agregarEvento(v);

            System.out.println("✅ Vacuna registrada exitosamente.");
        }
    }

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

    private void verHistorialMascota() {
        System.out.println("--- Historial de Mascota ---");
        Mascota m = seleccionarMascota();
        if (m != null) {
            m.mostrarHistorial();
        }
    }

    //UTILITARIOS

    private Mascota buscarMascota(String nombre) {
        return mascotas.stream()
                .filter(m -> m.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    private Mascota seleccionarMascota() {
        String nombre = leerTexto("Nombre de la mascota: ");
        Mascota m = buscarMascota(nombre);
        if (m == null) {
            System.out.println("⚠️ Mascota no encontrada.");
        }
        return m;
    }

    //Para lectura segura de datos, evitando duplicación.
    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

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

