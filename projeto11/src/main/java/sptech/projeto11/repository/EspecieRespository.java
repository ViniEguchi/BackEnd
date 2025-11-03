package sptech.projeto11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto11.model.Especie;

public interface EspecieRespository extends
        JpaRepository<Especie, String> {
}
