public class IDGenerator {
    private static int contadorConsulta = 0;
    private static int contadorMascota = 0;

    public static String generarCodigoConsulta() {
        return "C" + (++contadorConsulta);
    }

    public static String generarCodigoMascota() {
        return "M" + (++contadorMascota);
    }
}

/*
Cambios y explicación:
- Se creó la clase IDGenerator para generar códigos automáticos únicos.
- Uso de variables estáticas para llevar el conteo global.
- Método generarCodigoConsulta() devuelve un código con prefijo 'C' y número incremental.
- Método generarCodigoMascota() devuelve un código con prefijo 'M' y número incremental.
- Centraliza la generación de IDs, evitando errores de codificación manual en otras clases.
*/
