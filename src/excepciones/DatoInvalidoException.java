package excepciones;

//Excepción personalizada para validar datos inválidos en formularios o al construir objetos DTO.

public class DatoInvalidoException extends Exception {
    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
