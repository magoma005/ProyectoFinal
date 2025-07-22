package util;//Se creó la clase IDGenerator para generar códigos automáticos únicos.

public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;

    //Método generarCodigoConsulta() devuelve un código con prefijo 'C' y número incremental.

    public static String generarCodigoConsulta() {
        return "C" + (++contadorConsulta);
    }

    //Método generarCodigoMascota() devuelve un código con prefijo 'M' y número incremental.

    public static String generarCodigoMascota() {
        return "M" + (++contadorMascota);
    }
}
