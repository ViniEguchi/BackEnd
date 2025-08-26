package sptech.projeto04.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.projeto04.model.Pet;
import sptech.projeto04.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
// ou  public class PetResource {
public class PetController {

    /*
    Criamos como atributo final e no construtor porque isso indica que é um componente que o Spring deve Injetar
     */
    private final PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    // GET /pets com 204 sem corpo ou 200 com a lista de pets
    // GET /pets?filtro=john -> parâmetro de requisição opcional
    @GetMapping
    public ResponseEntity<List<Pet>> getPets() {
        var pets = repository.findAll();
        return pets.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> postPet(@RequestBody @Valid Pet pet) {
        return ResponseEntity.status(201).body(repository.save(pet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetPorId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            Pet petEncontrado = repository.findById(id).get();
            return ResponseEntity.status(200).body(petEncontrado);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    // DELETE por Id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetPorId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePetPorId(@RequestBody Pet pet) {
        if (repository.existsById(pet.getId())) {
            return ResponseEntity.status(200).body(repository.save(pet));
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
