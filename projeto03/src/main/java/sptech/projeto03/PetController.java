package sptech.projeto03;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pets")
public class PetController {
    private List<Pet> pets = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Pet>> getPets(@RequestParam(required = false) String nome) {
        if (nome != null) {
            var encontrados = pets.stream().filter(pet -> pet.getNomePet().contains(nome) || pet.getNomeDono().contains(nome)).toList();

            return ResponseEntity.status(200).body(encontrados);
        }

        if (pets.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pets);
    }

    @PostMapping
    public ResponseEntity<Pet> cadastrarPet(@RequestBody Pet novoPet) {
        for (Pet pet : pets) {
            if (pet.getId().equals(novoPet.getId())) {
                return ResponseEntity.status(400).build();
            }
        }

        pets.add(novoPet);
        return ResponseEntity.status(201).body(novoPet);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePet(@RequestParam int id) {
        var lista_id = pets.stream().filter(pet -> pet.getId().equals(id)).toList();

        if (lista_id.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        pets.remove(lista_id.get(0));
        return ResponseEntity.status(200).build();
    }
}
