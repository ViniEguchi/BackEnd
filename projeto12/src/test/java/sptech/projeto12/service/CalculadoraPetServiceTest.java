package sptech.projeto12.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import sptech.projeto12.dto.MinimoMaximoRacaoDiaGramas;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraPetServiceTest {

    CalculadoraPetService service = new CalculadoraPetService();

    @Test
    @DisplayName("Deve calcular a quantidade diária de ração para cães em gramas")
    void getRacaoDiaGramasCaes() {
        Double pesoGramas = 1000.0; // 1 Kg
        Double esperado = 20.0; // 2% de 1000

        Double resultado = service.getRacaoDiaGramasCaes(pesoGramas);

        assertEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Não deve calcular p/ quantidade inválida")
    void getRacaoDiaGramasCaesInvalido() {
        Double pesoGramas = -900.0; // peso inválido

        assertThrows(IllegalArgumentException.class, () ->
                service.getRacaoDiaGramasCaes(pesoGramas)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100.0, 2.0",
            "1000.0, 20.0",
            "1000.01, 15.00015",
            "5000.0, 75.0",
            "5000.01, 50.0001",
            "10000.0, 100.0"
    })
    @DisplayName("Deve calcular correto p/ todos os pesos")
    void getRacaoDiaTodosPesos(Double pesoGramas, Double recaoEsperada) {
        Double resultado = service.getRacaoDiaGramasCaes(pesoGramas);

        assertEquals(recaoEsperada, resultado);
    }

    @Test
    @DisplayName("Deve invalidar data futura")
    void validarValorPorIdadeInvalido() {
        LocalDate dataFutura = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () ->
                service.getValorPorIdade(dataFutura)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2025-11-10, 6000.0",  // 0 meses
            "2025-09-10, 6000.0",  // 2 meses
            "2025-06-10, 6000.0",  // 6 meses
            "2025-04-10, 4000.0",  // 7 meses
            "2025-02-10, 4000.0",  // 9 meses
            "2024-11-10, 4000.0",  // 12 meses
            "2023-12-15, 2000.0",  // 13 meses
            "2023-01-15, 2000.0"   // 24 meses
    })
    @DisplayName("Exercício de sala")
    void validarValorPorIdadeTodos(String dataNascimento, Double valorEsperado) {
        LocalDate data = LocalDate.parse(dataNascimento);
        Double resultado = service.getValorPorIdade(data);

        assertEquals(valorEsperado, resultado);
    }

    @Test
    @DisplayName("Não deve aceitar peso menor igual 0")
    void validarPesoInvalido() {
        double pesoInvalido = 0.0;
        LocalDate dataNascimento = LocalDate.of(2024, 12, 15);

        assertThrows(IllegalArgumentException.class, () ->
                service.calcularRacaoMaximaMinima(pesoInvalido, dataNascimento)
        );
    }

    @Test
    @DisplayName("Não deve aceitar data nascimento futuro")
    void validarNascimentoFuturo() {
        LocalDate dataInvalida = LocalDate.now().plusDays(1);
        Double peso = 10.0;

        assertThrows(IllegalArgumentException.class, () ->
                service.calcularRacaoMaximaMinima(peso, dataInvalida)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1000, 2025-11-15, 20, 30",
            "1000, 2023-11-15, 20, 30",
            "1000, 2022-11-15, 15, 20",
            "1000, 2017-11-15, 15, 20",
            "1000, 2016-11-15, 10, 15",
            "1000, 2000-11-15, 10, 15"
    })
    @DisplayName("Testes com idade nos limites")
    void validarCalcularRacaoMaximaMinima(Double peso, String dataNascimento, Double minEsperado, Double maxEsperado) {
        LocalDate data = LocalDate.parse(dataNascimento);

        MinimoMaximoRacaoDiaGramas resultado = service.calcularRacaoMaximaMinima(peso, data);

        assertEquals(minEsperado, resultado.minimo());
        assertEquals(maxEsperado, resultado.maximo());
    }
}