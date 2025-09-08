package sptech.projeto06.model;

import jakarta.persistence.*;

@Entity
public class Regras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer minimoPets;
    private Integer maximoPets;

    public Integer getId() {
        return id;
    }

    public Integer getMinimoPets() {
        return minimoPets;
    }

    public Integer getMaximoPets() {
        return maximoPets;
    }
}
