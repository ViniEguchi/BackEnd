package school.sptech;

import school.sptech.Exception.CpfInvalidoException;
import school.sptech.Exception.EmaiInvalidoException;
import school.sptech.Exception.TelefoneInvalidoException;
import school.sptech.Model.Faixa;
import school.sptech.Model.Lead;

public class Main {
    public static void main(String[] args) {
        String cpf = "";
        String nome = "";
        String email = "";
        String telefone = "";
        Integer consumoMedio = 0;

        Lead novaLead = cadastroLead(cpf, nome, email, telefone, consumoMedio);
        System.out.println(novaLead);
    }

    static Lead cadastroLead(String cpf, String nome, String email, String telefone, Integer consumoMedio) {
        validarEmail(email);
        validarTelefone(telefone);
        validarCPF(cpf);

        Faixa faixa;
        Integer prioridade;
        String sugestaoRegua;

        if (consumoMedio < 100) {
            faixa = Faixa.BAIXO;
            prioridade = 4;
            sugestaoRegua = "Nutrição mensal por e-mail";
        } else if (consumoMedio < 300) {
            faixa = Faixa.MEDIO;
            prioridade = 3;
            sugestaoRegua = "Contato em até 72h + material educativo";
        } else if (consumoMedio < 700) {
            faixa = Faixa.ALTO;
            prioridade = 2;
            sugestaoRegua = "Contato em até 48h + proposta personalizada";
        } else {
            faixa = Faixa.VIP;
            prioridade = 1;
            sugestaoRegua = "Contato em até 24h + consultoria especializada";
        }

        return new Lead(cpf, nome, email, telefone, consumoMedio, faixa, prioridade, sugestaoRegua);
    }

    static void validarEmail(String email) {
        if (!email.contains("@")) {
            throw new EmaiInvalidoException();
        }
    }

    static void validarCPF(String cpf) {
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new CpfInvalidoException();
        }
    }

    static void validarTelefone(String telefone) {
        if (!telefone.matches("(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})")) {
            throw new TelefoneInvalidoException();
        }
    }
}