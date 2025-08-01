import DAO.CrudMascotas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

      /*  // === Registro del Propietario ===
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
        propietario.mostrarInformacionCompleta(); */

        // === MENÚ CRUD DE MASCOTAS ===
        System.out.println("\n¿Desea abrir el CRUD del veterinario? (s/n): ");
        String opcionCrud = sc.nextLine();
        if (opcionCrud.equalsIgnoreCase("s")) {
            CrudMascotas crud = new CrudMascotas();
            crud.menuCrud(); // Abre el menú CRUD del veterinario
        }

        sc.close();
    }
}
