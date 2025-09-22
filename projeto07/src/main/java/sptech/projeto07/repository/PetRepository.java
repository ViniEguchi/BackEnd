package sptech.projeto07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sptech.projeto07.dto.PetSimplesResponse;
import sptech.projeto07.model.Pet;

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

    // Dynamic Finders (até a linha 43)
    List<Pet> findByNomePetContainsIgnoreCase(String nomePet);

    List<Pet> findByNomeDonoIgnoreCase(String nomeDono);

    List<Pet> findByNomeDonoContainsIgnoreCaseOrNomePetContainsIgnoreCase(
            String nomeDono, String nomePet
    );

    @Query("""
            SELECT p.id AS id,
                   p.nomePet AS nome,
                   p.nomeDono AS dono,
                   p.raca.nome AS raca
            FROM Pet p
    """)
    List<PetSimplesResponse> findAllSimples();

    List<Pet> findTop4ByOrderByPesoDesc();

    List<Pet> findByAtivoTrue();

    List<Pet> findByAtivoFalse();

//    int countByEspecieIgnoreCase(String especie);

    // int deleteByRacaIgnoreCase(String raca);

//    boolean existsByEspecie(String especie);


    /*
Aqui usamos a anotação @Query, que nos permite usar
tanto SQL quanto JPQL (Java Persistence QueryLanguage), que é o padrão
CASO usemos o @Query para fazer um UPDATE ou DELETE é obrigatório
usar também as anotações @Modifying e @Transactional
OBS: NÃO é possível usar o @Query para fazer INSERT
     */
    @Query("UPDATE Pet p SET p.ativo = false WHERE p.id = ?1")
    @Modifying
    @Transactional // do package org.springframework.transaction.annotation
    int desativarPorId(Integer id);
}




