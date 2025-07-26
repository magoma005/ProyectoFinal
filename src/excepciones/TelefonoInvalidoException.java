package excepciones;

public class TelefonoInvalidoException extends RuntimeException {
    public TelefonoInvalidoException(String mensaje) {
        super(mensaje);
    }
}