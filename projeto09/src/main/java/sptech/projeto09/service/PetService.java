package sptech.projeto09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sptech.projeto09.dto.PetSimplesResponse;
import sptech.projeto09.exception.DependenciaNaoEncontradaException;
import sptech.projeto09.exception.PetProtegidoException;
import sptech.projeto09.exception.QuantidadeMaximaPetsException;
import sptech.projeto09.exception.QuantidadeMinimaPetsException;
import sptech.projeto09.model.Pet;
import sptech.projeto09.model.Raca;
import sptech.projeto09.repository.EspecieRespository;
import sptech.projeto09.repository.PetRepository;
import sptech.projeto09.repository.RacaRespository;
import sptech.projeto09.repository.RegrasRepository;

import java.util.List;

@Service // por padrão, toda Service é singleton
@RequiredArgsConstructor
@Slf4j // injeção objeto de log via Lombok
public class PetService {

    private final PetRepository repository;

    private final RegrasRepository regrasRepository;

    private final RacaRespository racaRespository;

    private final EspecieRespository especieRespository;

    public List<PetSimplesResponse> listarSimples() {
        return repository.findAllSimples();
    }

    public List<Pet> listar(String pesquisa) {
        List<Pet> pets = null;

        if (pesquisa == null) {
            pets = repository.findAll();
        } else {
            pets = repository
                    .findByNomeDonoContainsIgnoreCaseOrNomePetContainsIgnoreCase(pesquisa, pesquisa);
        }

        return pets;
    }

    public Pet obterPorCodigo(Integer codigo) {
        return repository.findById(codigo)
                .orElseThrow(() -> new DependenciaNaoEncontradaException("Pet"));
    }

    public Pet salvar(Pet pet) {
        validarQuantidadeMaximaPets();

        if (!racaRespository.existsById(pet.getRaca().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Raça");
        }

        if (!especieRespository.existsById(pet.getEspecie().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Espécie");
        }

        return repository.save(pet);
    }

    private void validarQuantidadeMaximaPets() {
        // regra de exemplo: a api só pode ter N pets
        // if (repository.count() == regrasRepository.findAll().getFirst().getMaximoPets()) {
        if (repository.count() == regrasRepository.findTop1By().getMaximoPets()) {
            log.error("Quantidade máxima de pets excedida");
            throw new QuantidadeMaximaPetsException();
        }
    }

    public void excluir(Integer codigo) {
        if (repository.count() == regrasRepository.findTop1By().getMinimoPets()) {
            System.out.println("kiweuhfiuwheuifwh iufhweuifhweiuf");
            throw new QuantidadeMinimaPetsException();
        }

        if (!repository.existsById(codigo)) {
            throw new DependenciaNaoEncontradaException("Pet");
        }

        repository.deleteById(codigo);
    }

    public Pet atualizar(Integer id, Pet pet) {
        if (!repository.existsById(id)) {
            throw new DependenciaNaoEncontradaException("Pet");
        }
        pet.setId(id);
        return repository.save(pet);
    }

    public void desativar(Integer id) {
        if (!repository.existsById(id)) {
            throw new DependenciaNaoEncontradaException("Pet");
        }

        repository.desativarPorId(id);
    }

    public Raca relatorio() {
        return racaRespository.findAll().getFirst();
    }
}
