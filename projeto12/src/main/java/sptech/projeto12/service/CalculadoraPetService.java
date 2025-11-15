package sptech.projeto12.service;

import org.springframework.stereotype.Service;
import sptech.projeto12.dto.MinimoMaximoRacaoDiaGramas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CalculadoraPetService {

    /* Formula atualizada
        Até 1 Kg: 2%
        De 1Kg até 5 Kg: 1,5%
        Acima de 5Kg: 1%
     */
    public Double getRacaoDiaGramasCaes(Double pesoGramas) {
        if (pesoGramas <= 0) {
            throw new IllegalArgumentException();
        }

        if (pesoGramas <= 1000) {
            return pesoGramas * 0.02;
        } else if (pesoGramas <= 5000) {
            return pesoGramas * 0.015;
        } else {
            return pesoGramas * 0.01;
        }
    }

    public Double getValorPorIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();

        if (dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException();
        }

        long meses = ChronoUnit.MONTHS.between(dataNascimento, hoje);

        if (meses <= 6) {
            return 6000.0;
        } else if (meses <= 12) {
            return 4000.0;
        } else {
            return 2000.0;
        }
    }

    public MinimoMaximoRacaoDiaGramas calcularRacaoMaximaMinima(Double peso, LocalDate dataNascimento) {
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior ou igual a 0");
        }

        LocalDate hoje = LocalDate.now();

        if (dataNascimento.isAfter(hoje)) {
            throw new IllegalArgumentException("O nascimento não pode ser posterior a hoje");
        }

        Long anos = ChronoUnit.YEARS.between(dataNascimento, hoje);

        Double min;
        Double max;

        if (anos <= 2) {
            min = calcularPesoRacao(peso, 2.0);
            max = calcularPesoRacao(peso, 3.0);
        } else if (anos <= 8) {
            min = calcularPesoRacao(peso, 1.5);
            max = calcularPesoRacao(peso, 2.0);
        } else {
            min = calcularPesoRacao(peso, 1.0);
            max = calcularPesoRacao(peso, 1.5);
        }

        return new MinimoMaximoRacaoDiaGramas(min, max);
    }

    Double calcularPesoRacao(Double peso, Double porcentagem) {
        return peso * (porcentagem / 100.0);
    }
}
