package school.sptech.Exception;

public class CpfInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "O cpf deve estar no formato 'XXX.XXX.XXX-XX'";
    }
}
