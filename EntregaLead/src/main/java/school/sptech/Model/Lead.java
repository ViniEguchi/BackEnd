package school.sptech.Model;

public record Lead(
        String cpf,
        String nome,
        String email,
        String telefone,
        Integer consumoMedio,
        Faixa faixa,
        Integer prioridade,
        String sugestaoRegua
) {
}
