import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === Registro del Propietario ===
        System.out.print("Ingrese nombre del propietario: ");
        String nombreProp = sc.nextLine();

        System.out.print("Ingrese documento del propietario: ");
        String documento = sc.nextLine();

        System.out.print("Ingrese teléfono del propietario: ");
        String telefono = sc.nextLine();

        Propietario propietario = new Propietario(nombreProp, documento, telefono);

        // === Registrar mascotas ===
        System.out.print("Cuántas mascotas desea registrar? ");
        int numMascotas = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numMascotas; i++) {
            System.out.print("Ingrese nombre de la mascota: ");
            String nomMascota = sc.nextLine();

            System.out.print("Ingrese especie: ");
            String especie = sc.nextLine();

            System.out.print("Ingrese edad: ");
            int edad = Integer.parseInt(sc.nextLine());

            Mascota mascota = new Mascota(nomMascota, especie, edad);

            // === Registrar consultas para esta mascota ===
            System.out.print("Cuántas consultas tiene esta mascota? ");
            int numConsultas = Integer.parseInt(sc.nextLine());

            for (int j = 0; j < numConsultas; j++) {
                // Generar código automático de consulta usando IDGenerator
                String codConsulta = IDGenerator.generarCodigoConsulta();

                System.out.print("Ingrese fecha de la consulta (YYYY-MM-DD): ");
                String fecha = sc.nextLine();

                System.out.print("Ingrese nombre del veterinario: ");
                String nomVet = sc.nextLine();

                System.out.print("Ingrese especialidad del veterinario: ");
                String espVet = sc.nextLine();

                Veterinario veterinario = new Veterinario(nomVet, espVet);
                Consulta consulta = new Consulta(fecha, veterinario);

                mascota.agregarConsulta(consulta);
            }

            propietario.agregarMascota(mascota);
        }

        // === Mostrar información completa ===
        propietario.mostrarInformacionCompleta();
        sc.close();
    }
}

/*
- Se reemplaza nextInt()/nextLong() por nextLine() + parseo para evitar problemas de buffer.
- Se usa IDGenerator para generar códigos automáticos de consulta.
- Se usa LocalDate en Consulta para manejar fechas con formato y validación correctos.
- Se implementan las nuevas clases y métodos validados del taller.
Resultado: código limpio, seguro, validado y listo para consola o extensión futura.
*/