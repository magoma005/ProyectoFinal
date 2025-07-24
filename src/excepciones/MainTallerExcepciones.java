package excepciones;

import modelo.Mascota; // Se importa la clase Mascota para solucionar el error
import java.util.Scanner;

public class MainTallerExcepciones {
    public static void main(String[] args) {
        System.out.println("Inicio del programa...");

        Scanner sc = new Scanner(System.in);

        try {
            Mascota m1 = null;
            m1.mostrarHistorial(); // Esto lanza NullPointerException
        } catch (NullPointerException e) {
            System.out.println("⚠️ Error: objeto no inicializado.");
        }

        try {
            int edad = 10;
            int divisor = 0;
            int resultado = edad / divisor; // Error
        } catch (ArithmeticException e) {
            System.out.println("⚠️ Error aritmético: división por cero.");
        }

        // Lectura desde consola con validación
        System.out.println("=== Registro de nueva mascota ===");

        try {
            System.out.print("Ingrese nombre de la mascota: ");
            String nombre = sc.nextLine();

            System.out.print("Ingrese especie de la mascota: ");
            String especie = sc.nextLine();

            System.out.print("Ingrese edad de la mascota: ");
            int edad = Integer.parseInt(sc.nextLine());

            // Creamos mascota usando constructor con validaciones personalizadas
            Mascota nuevaMascota = new Mascota(nombre, especie, edad);

            System.out.println("✅ Mascota registrada exitosamente.");
            nuevaMascota.mostrarHistorial(); // muestra historial vacío por defecto

        } catch (NombreVacioException | EdadInvalidaException e) {
            System.out.println("⚠️ Excepción personalizada: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ La edad debe ser un número entero válido.");
        } finally {
            sc.close();
        }

        System.out.println("Fin del programa.");
    }
}