package excepciones;

public class EspecieVaciaException extends RuntimeException {
    public EspecieVaciaException(String mensaje) {
        super(mensaje);
    }
}
