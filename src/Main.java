import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Registrar propietario
        System.out.print("Ingrese nombre del propietario: ");
        String nombreProp = sc.nextLine();

        System.out.print("Ingrese documento del propietario: ");
        long docProp = sc.nextLong();
        sc.nextLine(); // Limpiar buffer

        System.out.print("Ingrese teléfono del propietario: ");
        long telProp = sc.nextLong();
        sc.nextLine(); // Limpiar buffer

        // nextInt() y nextLong() no leen el Enter (salto de línea) al final de un número.
        // Usamos sc.nextLine() para limpiar el buffer y evitar que la siguiente lectura de texto se salte.

        Propietario propietario = new Propietario(nombreProp, docProp, telProp);

        //Se llama a la clase al final de su pedido

        // Registrar mascotas
        System.out.print("Cuántas mascotas desea registrar? ");
        int numMascotas = sc.nextInt();
        sc.nextLine(); // Limpiar buffer


        for (int i = 0; i < numMascotas; i++) {
            // Este for se repite numMascotas veces para registrar cada mascota ingresada por el usuario.
            System.out.print("Ingrese nombre de la mascota: ");
            String nomMascota = sc.nextLine();

            System.out.print("Ingrese especie: ");
            String especie = sc.nextLine();

            System.out.print("Ingrese edad: ");
            int edad = sc.nextInt();
            sc.nextLine();

            Mascota mascota = new Mascota(nomMascota, especie, edad);

            // Registrar consultas para esta mascota
            System.out.print("Cuántas consultas tiene esta mascota? ");
            int numConsultas = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < numConsultas; j++) {
                System.out.print("Ingrese código de la consulta: ");
                int codConsulta = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                System.out.print("Ingrese fecha de la consulta: ");
                int fecha = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                System.out.print("Ingrese nombre del veterinario: ");
                String nomVet = sc.nextLine();

                System.out.print("Ingrese especialidad del veterinario: ");
                String espVet = sc.nextLine();

                Veterinario veterinario = new Veterinario(nomVet, espVet);
                Consulta consulta = new Consulta(codConsulta, fecha, veterinario);

                mascota.agregarConsulta(consulta);


                mascota.agregarConsulta(consulta);
            }

            propietario.agregarMascota(mascota);
        }

        // Mostrar información completa
        propietario.mostrarInformacionCompleta();

        sc.close();
    }
}