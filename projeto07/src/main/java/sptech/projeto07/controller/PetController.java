package sptech.projeto07.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sptech.projeto07.exception.PetProtegidoException;
import sptech.projeto07.exception.QuantidadeMinimaPetsException;
import sptech.projeto07.model.Pet;
import sptech.projeto07.repository.PetRepository;
import sptech.projeto07.repository.RegrasRepository;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
// ou  public class PetResource { // é muito usado esse padrão de nome também
public class PetController {

    /*
    Criamos como atributo final e no construtor
    porque isso indica que é um componente
    que o Spring deve INJETAR
     */
    private final PetRepository repository;

    private final RegrasRepository regrasRepository;

    /*
    O Spring Boot vai injetar uma classe que implementa a interface
    PetRepository, que por sua vez estende a interface JpaRepository
    que já tem uma implementação pronta para fazer as operações
    de CRUD no banco de dados H2 (porque foi o Driver que colocamos
    no pom.xml)
     */
//    public PetController(PetRepository repository, RegrasRepository regrasRepository) {
//        this.repository = repository;
//        this.regrasRepository = regrasRepository;
//    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(
            @RequestParam(required = false) String pesquisa
    ) {
        List<Pet> pets = null;

        if (pesquisa == null) {
            pets = repository.findAll();
        } else {
// a pesquisa seja por nome do dono ou do pet
// podendo ser parcial e case INSENSITIVE
            pets = repository
                    .findByNomeDonoContainsIgnoreCaseOrNomePetContainsIgnoreCase(pesquisa, pesquisa);
        }

        return pets.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody @Valid Pet pet) {

        // regra de exemplo: a api só pode ter N pets
        // if (repository.count() == regrasRepository.findAll().getFirst().getMaximoPets()) {
        if (repository.count() == regrasRepository.findTop1By().getMaximoPets()) {
            /*
Usamos ResponseStatusException quando temos regras de negócio
específicas e não queremos alterar o retorno do médodo.
1o argumento -> status
2o argumento -> mensagem
3o argumento -> exceção "pai" (se houver)
             */
            throw new ResponseStatusException(
                    422, "Só pode 100 pets", null);
        }

        /*
        O save() do repository serve tanto para inserir
        quanto para atualizar (se o id vier preenchido).
        Ele retorna uma versão do objeto com o id
        preenchido (no caso de insert).
         */
        Pet petSalvo = repository.save(pet);
        return ResponseEntity.status(201).body(petSalvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetPorId(@PathVariable Integer id) {
/*
O existsById() verifica se existe um registro com o id fornecido
no banco de dados, retornando true ou false
 */
        if (repository.existsById(id)) {
/*
O findById() retorna um Optional<Pet>
Se o id existir no banco, é um Optional com um Pet dentro
Caso contrário, é um Optinal vazio.
Mas, como já estamos num if que valida que o id existe,
o .get() pode ser feito sem medo pois haverá valor
 */
            Pet petEncontrado = repository.findById(id).get();
            return ResponseEntity.status(200).body(petEncontrado);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    //
//    // DELETE por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetPorId(@PathVariable Integer id) {
        // regra de exemplo: a api deve ter pelo menos N pets
        if (repository.count() == regrasRepository.findTop1By().getMinimoPets()) {
            throw new QuantidadeMinimaPetsException();
        }
        if (id == 2) {
            throw new PetProtegidoException(id);
        }

        if (repository.existsById(id)) {
// O deleteById() exclui o registro do banco de dados a partir do id
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    //    PUT por Id
    @PutMapping("/{id}")
    public ResponseEntity<Pet> putPet(
            @PathVariable Integer id,
            @RequestBody @Valid Pet pet
    ) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        pet.setId(id);
        Pet petSalvo = repository.save(pet);
        return ResponseEntity.status(200).body(petSalvo);
    }

    @PatchMapping("/desativacao/{id}")
    public ResponseEntity<Void> desativarPet(
            @PathVariable Integer id) {
        int atualizados = repository.desativarPorId(id);
        return atualizados == 1
                ? ResponseEntity.status(200).build()
                : ResponseEntity.status(404).build();
    }
}



