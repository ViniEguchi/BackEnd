package sptech.projeto04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto04.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
