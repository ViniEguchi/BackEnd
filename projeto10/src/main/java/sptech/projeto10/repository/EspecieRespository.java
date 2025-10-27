package sptech.projeto10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto10.model.Especie;

public interface EspecieRespository extends
        JpaRepository<Especie, String> {
}
