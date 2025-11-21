package sptech.projeto13.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sptech.projeto13.exception.DependenciaNaoEncontradaException;
import sptech.projeto13.exception.PetProtegidoException;
import sptech.projeto13.exception.QuantidadeMinimaPetsException;
import sptech.projeto13.model.Especie;
import sptech.projeto13.model.Pet;
import sptech.projeto13.model.Raca;
import sptech.projeto13.model.Regras;
import sptech.projeto13.repository.EspecieRespository;
import sptech.projeto13.repository.PetRepository;
import sptech.projeto13.repository.RacaRespository;
import sptech.projeto13.repository.RegrasRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PetServiceTest {

    PetService service;
    RacaRespository racaRespository;
    PetRepository petRepository;
    RegrasRepository regrasRepository;
    EspecieRespository especieRespository;

    @BeforeEach
    void setup() {
        petRepository = mock(PetRepository.class);
        racaRespository = mock(RacaRespository.class);
        regrasRepository = mock(RegrasRepository.class);
        especieRespository = mock(EspecieRespository.class);
        service = new PetService(petRepository, regrasRepository, racaRespository, especieRespository);
    }

    @Test
    @DisplayName("Exclusão que extrapola o mínimo deve ser negada")
    void test() {
        Regras regras = mock(Regras.class);

        when(regras.getMinimoPets()).thenReturn(3);

        when(regrasRepository.findTop1By()).thenReturn(regras);

        when(petRepository.count()).thenReturn(3L);

        assertThrows(QuantidadeMinimaPetsException.class, () ->
                service.excluir(99)
        );
    }

    @Test
    @DisplayName("A exclusão do id 2 deve ser negada")
    void testExcluirIdProtegido() {
        Regras regras = mock(Regras.class);

        when(regras.getMinimoPets()).thenReturn(-1);

        when(regrasRepository.findTop1By()).thenReturn(regras);

        Integer id = 2;

        assertThrows(PetProtegidoException.class, () ->
                service.excluir(id)
        );
    }

    @Test
    @DisplayName("Novo pet com raça inválida")
    void testSalvarRacaInvalida() {
        String codigoInvalido = "XYZ";
        Pet pet = mock(Pet.class);
        Raca raca = mock(Raca.class);
        Especie especie = mock(Especie.class);

        Regras regras = mock(Regras.class);

        when(regras.getMaximoPets()).thenReturn(10);
        when(regrasRepository.findTop1By()).thenReturn(regras);
        when(petRepository.count()).thenReturn(3L);

        when(pet.getRaca()).thenReturn(raca);
        when(raca.getCodigo()).thenReturn(codigoInvalido);
        when(pet.getEspecie()).thenReturn(especie);

        when(racaRespository.existsById(codigoInvalido)).thenReturn(false);

        assertThrows(DependenciaNaoEncontradaException.class, () ->
                service.salvar(pet)
        );
    }
}