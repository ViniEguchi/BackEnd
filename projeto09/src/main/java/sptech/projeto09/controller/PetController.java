package sptech.projeto09.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sptech.projeto09.dto.PetSimplesResponse;
import sptech.projeto09.exception.DependenciaNaoEncontradaException;
import sptech.projeto09.exception.PetProtegidoException;
import sptech.projeto09.exception.QuantidadeMinimaPetsException;
import sptech.projeto09.model.Pet;
import sptech.projeto09.model.Raca;
import sptech.projeto09.repository.EspecieRespository;
import sptech.projeto09.repository.PetRepository;
import sptech.projeto09.repository.RacaRespository;
import sptech.projeto09.repository.RegrasRepository;
import sptech.projeto09.service.PetService;

import java.util.List;

// cria o construtor com os atributos final
@RequiredArgsConstructor
@Slf4j // anotação de log do Lombok
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

    private final RacaRespository racaRespository;

    private final EspecieRespository especieRespository;

    private final PetService service;


    /*
    O Spring Boot vai injetar uma classe que implementa a interface
    PetRepository, que por sua vez estende a interface JpaRepository
    que já tem uma implementação pronta para fazer as operações
    de CRUD no banco de dados H2 (porque foi o Driver que colocamos
    no pom.xml)
     */
    /*public PetController(PetRepository repository, RegrasRepository regrasRepository) {
        this.repository = repository;
        this.regrasRepository = regrasRepository;
    }*/

    @GetMapping("/simples")
    public ResponseEntity<List<PetSimplesResponse>> getSimples() {
        var lista = repository.findAllSimples();
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

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

        log.debug("A lista veio com {} pets", pets.size());

        return pets.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody @Valid Pet pet) {

        Pet petSalvo = service.salvar(pet);

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

//    DELETE por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetPorId(@PathVariable Integer id) {
        service.excluir(id);

        return ResponseEntity.status(204).build();
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


    @GetMapping("/raca")
    public ResponseEntity<Raca> getRelatorio() {
        Raca primeiraRaca = racaRespository.findAll().getFirst();
        return ResponseEntity.status(200).body(primeiraRaca);
    }
}



