package sptech.projeto11.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto11.model.Raca;

public interface RacaRespository extends
        JpaRepository<Raca, String> {
}
