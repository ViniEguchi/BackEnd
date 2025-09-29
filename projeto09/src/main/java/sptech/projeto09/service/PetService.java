package sptech.projeto09.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sptech.projeto09.exception.DependenciaNaoEncontradaException;
import sptech.projeto09.exception.PetProtegidoException;
import sptech.projeto09.exception.QuantidadeMaximaPetsException;
import sptech.projeto09.exception.QuantidadeMinimaPetsException;
import sptech.projeto09.model.Pet;
import sptech.projeto09.repository.EspecieRespository;
import sptech.projeto09.repository.PetRepository;
import sptech.projeto09.repository.RacaRespository;
import sptech.projeto09.repository.RegrasRepository;

@Service // por padrão, toda Service é singleton
@RequiredArgsConstructor
@Slf4j // injeção objeto de log via Lombok
public class PetService {

    private final PetRepository repository;

    private final RegrasRepository regrasRepository;

    private final RacaRespository racaRespository;

    private final EspecieRespository especieRespository;

    public Pet salvar(Pet pet) {
        validarQuantidadeMaximaPets();

        if (!racaRespository.existsById(pet.getRaca().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Raça");
        }

        if (!especieRespository.existsById(pet.getEspecie().getCodigo())) {
            throw new DependenciaNaoEncontradaException("Espécie");
        }

        /*
        O save() do repository serve tanto para inserir
        quanto para atualizar (se o id vier preenchido).
        Ele retorna uma versão do objeto com o id
        preenchido (no caso de insert).
         */
        Pet petSalvo = repository.save(pet);
        return petSalvo;
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
}
