package school.sptech.Exception;

public class TelefoneInvalidoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX";
    }
}
