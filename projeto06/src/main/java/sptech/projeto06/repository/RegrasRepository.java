package sptech.projeto06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.projeto06.model.Regras;

public interface RegrasRepository extends JpaRepository<Regras, Integer> {

    Regras FindTop1By();
}
