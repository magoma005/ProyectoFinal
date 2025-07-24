package excepciones;

public class ClaveVaciaException extends RuntimeException {
    public ClaveVaciaException(String mensaje) {
        super(mensaje);
    }
}
