package sptech.projeto13.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sptech.projeto13.model.Raca;
import sptech.projeto13.repository.RacaRespository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

/*
Aqui fizemos testes de INTEGRAÇÃO (ou integrados)
pois tivemos que integrar a RacaController com o RacaRepository
 */
class RacaControllerTest {

    RacaRespository racaRespository;
    RacaController controller;

    @BeforeEach
    void setup() {
        racaRespository = mock(RacaRespository.class);
        controller = new RacaController(racaRespository);
    }

    @Test
    @DisplayName("GET, /racas sem dados - 204")
    void testGetSemDados() {
        // preparação para o Teste
        // programando o mock para não retornar dados
        when(racaRespository.findAll())
                .thenReturn(new ArrayList<>());

        var resultado = controller.obterRacasSCache();

        // verificações
        // veio status 204
        assertEquals(204, resultado.getStatusCodeValue());

        // veio sem corpo
        assertNull(resultado.getBody());
    }

    @Test
    @DisplayName("GET, /racas com dados - 200")
    void testGetComDados() {
        // preparação para o teste
        // programando o mock para retornar dados
        var listaRacas = List.of(
                mock(Raca.class), mock(Raca.class), mock(Raca.class)
        ); // lista de 3 racas mockadas ("de mentirinha")

        when(racaRespository.findAll()).thenReturn(listaRacas);
        // quando alguém invocar o findAll()
        // vai receber a lista de 3 racas

        var resultado = controller.obterRacasSCache();

        // Verificações
        // veio status 200
        assertEquals(200, resultado.getStatusCodeValue());

        // veio com corpo
        assertNotNull(resultado.getBody());

        // corpo tem 3 racas?
        assertEquals(3, resultado.getBody().size());
    }

    @Test
    @DisplayName("DELETE id inexistente - 404")
    void testeDeleteInvexistente() {
        String codigoInvalido = "XYZ";

        when(racaRespository.existsById(codigoInvalido)).thenReturn(false);

        var resultado = controller.excluirRaca(codigoInvalido);

        // retorna 404?
        assertEquals(404, resultado.getStatusCodeValue());

        // retorna SEM corpo (corpo null)
        assertNull(resultado.getBody());
    }

    @Test
    @DisplayName("Get id existente - 200 c/ a raca correta")
    void testeGetExistente() {
        String codigoValido = "VIRA_LATA";

        Raca racaEsperada = new Raca("VIRA_LATA", "Vira Lata");

        when(racaRespository.findById(codigoValido)).thenReturn(Optional.of(racaEsperada));

        var resultado = controller.obterRacaCCache(codigoValido);

        assertEquals(200, resultado.getStatusCodeValue());

        assertNotNull(resultado.getBody());

        assertEquals(racaEsperada, resultado.getBody());
    }

    @Test
    @DisplayName("POST raca válida retorna 201 com corpo")
    void testNovaRaca() {
        Raca racaInserida = new Raca("ABACAXI", "ABACAXI");

        when(racaRespository.save(racaInserida)).thenReturn(racaInserida);

        var resultado = controller.criarRaca(racaInserida);

        assertEquals(201, resultado.getStatusCodeValue());

        assertNotNull(resultado.getBody());

        assertEquals(racaInserida, resultado.getBody());
    }
}