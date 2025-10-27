package sptech.projeto10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto10.model.Raca;

public interface RacaRespository extends
        JpaRepository<Raca, String> {
}
