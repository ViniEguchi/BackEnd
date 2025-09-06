package sptech.projeto05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sptech.projeto05.model.Pet;

import java.util.List;

/*
Aqui criamos a interface PetRepository que estende
a interface JpaRepository do Spring Data JPA.
Essa interface já tem uma implementação pronta
para fazer as operações de CRUD no banco de dados para a entidade Pet.

<Pet, Integer> indica que o repositório é para a entidade Pet
e que o tipo do ID (chave primária) é Integer
(note que o campo anotado com @Id em Pet é do tipo Integer).
 */
public interface PetRepository
        extends JpaRepository<Pet, Integer> {

    /*
        IgnoreCase ignora maiusculos e minusculos
        Contains pesquisa por parte do nome
        ContainsIgnoreCase pesquisa por parte e ignora maiusculos e minusculos
     */

    List<Pet> findByNomePetContainsIgnoreCase(String nomePet);

    List<Pet> findByNomeDonoIgnoreCase(String nomeDono);

    List<Pet> findByNomeDonoContainsIgnoreCaseOrNomePetContainsIgnoreCase(String nomeDono, String nomePet);

    int countByEspecieIgnoreCase(String especie);

    int deleteByRacaIgnoreCase(String raca);

    boolean existsByEspecie(String especie);

    @Query("UPDATE Pet p SET p.ativo = false WHERE p.id = ?1")
    @Modifying
    @Transactional
    int desativarPorId(Integer id);
}
