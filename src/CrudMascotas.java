import java.util.ArrayList;
import java.util.Scanner;

public class CrudMascotas {

    private ArrayList<Mascota> mascotas = new ArrayList<>();

    public void menuCrud() {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== CRUD MASCOTAS ===");
            System.out.println("1. Agregar Mascota");
            System.out.println("2. Listar Mascotas");
            System.out.println("3. Actualizar Mascota");
            System.out.println("4. Eliminar Mascota");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1 -> agregarMascota(sc);
                case 2 -> listarMascotas();
                case 3 -> actualizarMascota(sc);
                case 4 -> eliminarMascota(sc);
                case 5 -> System.out.println("Saliendo del CRUD de Mascotas...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        //sc.close(); // No cerrar aquí si se usa en el main con otros menús
    }

    private void agregarMascota(Scanner sc) {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Especie: ");
            String especie = sc.nextLine();
            System.out.print("Edad: ");
            int edad = Integer.parseInt(sc.nextLine());
            Mascota nueva = new Mascota(nombre, especie, edad);
            mascotas.add(nueva);
            System.out.println("Mascota agregada correctamente.");
        } catch (Exception e) {
            System.out.println("Error al agregar mascota: " + e.getMessage());
        }
    }

    private void listarMascotas() {
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
        } else {
            for (Mascota m : mascotas) {
                System.out.println("- Nombre: " + m.getNombre() + ", Especie: " + m.getEspecie() + ", Edad: " + m.getEdad());
            }
        }
    }

    private void actualizarMascota(Scanner sc) {
        System.out.print("Nombre de la mascota a actualizar: ");
        String buscado = sc.nextLine();
        boolean encontrado = false;
        for (Mascota m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(buscado)) {
                try {
                    System.out.print("Nuevo nombre: ");
                    m.setNombre(sc.nextLine());
                    System.out.print("Nueva especie: ");
                    m.setEspecie(sc.nextLine());
                    System.out.print("Nueva edad: ");
                    m.setEdad(Integer.parseInt(sc.nextLine()));
                    System.out.println("Mascota actualizada correctamente.");
                } catch (Exception e) {
                    System.out.println("Error al actualizar: " + e.getMessage());
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) System.out.println("Mascota no encontrada.");
    }

    private void eliminarMascota(Scanner sc) {
        System.out.print("Nombre de la mascota a eliminar: ");
        String nombreEliminar = sc.nextLine();
        boolean eliminado = mascotas.removeIf(m -> m.getNombre().equalsIgnoreCase(nombreEliminar));
        if (eliminado) {
            System.out.println("Mascota eliminada correctamente.");
        } else {
            System.out.println("Mascota no encontrada.");
        }
    }
}
