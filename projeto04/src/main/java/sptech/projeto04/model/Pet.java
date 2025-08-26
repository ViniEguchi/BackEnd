package sptech.projeto04.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;

@Entity
public class Pet {
    @Id  // do package jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Integer id;

    @NotBlank
    private String nomePet;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nomeDono;

    private String especie;
    private String raca;
    private String emailDono;

    @Positive
    // @Negative
    // @PositiveOrZero
    // @NegativeOrZero
    private Double peso;

    @DecimalMin("0.1") // valor tem que ser entre aspas
    @DecimalMax("1.5") // valor tem que ser entre aspas
    private Double altura;

    @PastOrPresent
    // @Past
    private LocalDate nascimento;

    @Future
    // @FutureOrPresent
    private LocalDate validadeChip;

    @CPF // Não importo se tem ou não pontos e traços
    // @CNPJ
    // @TituloEleitoral
    private String cpfDono;

    public Integer getId() {
        return id;
    }

    public String getNomePet() {
        return nomePet;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public String getEmailDono() {
        return emailDono;
    }

    public Double getPeso() {
        return peso;
    }

    public Double getAltura() {
        return altura;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public LocalDate getValidadeChip() {
        return validadeChip;
    }

    public String getCpfDono() {
        return cpfDono;
    }
}
