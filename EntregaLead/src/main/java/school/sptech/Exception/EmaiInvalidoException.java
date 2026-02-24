package school.sptech.Exception;

public class EmaiInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "O email deve conter o caractere '@'";
    }
}
