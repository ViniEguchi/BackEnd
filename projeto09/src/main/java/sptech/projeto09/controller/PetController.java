package sptech.projeto09.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projeto09.dto.PetSimplesResponse;
import sptech.projeto09.model.Pet;
import sptech.projeto09.model.Raca;
import sptech.projeto09.service.PetService;

import java.util.List;

// cria o construtor com os atributos final
@RequiredArgsConstructor
@Slf4j // anotação de log do Lombok
@RestController
@RequestMapping("/pets")
// ou  public class PetResource { // é muito usado esse padrão de nome também
public class PetController {
    private final PetService service;

    @GetMapping("/simples")
    public ResponseEntity<List<PetSimplesResponse>> getSimples() {
        var lista = service.listarSimples();
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(
            @RequestParam(required = false) String pesquisa
    ) {
        List<Pet> pets = service.listar(pesquisa);

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
        Pet petEncontrado = service.obterPorCodigo(id);
        return ResponseEntity.status(200).body(petEncontrado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetPorId(@PathVariable Integer id) {
        service.excluir(id);

        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> putPet(
            @PathVariable Integer id,
            @RequestBody @Valid Pet pet
    ) {
        Pet petSalvo = service.atualizar(id, pet);
        return ResponseEntity.status(200).body(petSalvo);
    }

    @PatchMapping("/desativacao/{id}")
    public ResponseEntity<Void> desativarPet(@PathVariable Integer id) {
        service.desativar(id);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/raca")
    public ResponseEntity<Raca> getRelatorio() {
        Raca primeiraRaca = service.relatorio();
        return ResponseEntity.status(200).body(primeiraRaca);
    }
}
