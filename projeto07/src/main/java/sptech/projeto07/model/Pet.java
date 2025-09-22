package sptech.projeto07.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pet {

    /*
    Id do package jakarta.persistence
    Indica qual atributo é o ID (chave primária)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
//    @NotNull(message = "Onde já se viu sem ID!")
//    @NotNull
    private Integer id;

    @NotBlank
    private String nomePet;

    @NotBlank
    @Size(min = 3, max = 20)
    private String nomeDono;

    @ManyToOne
    private Especie especie;

    @ManyToOne
    private Raca raca;

    private String emailDono;

    @Positive
//    @Negative
//    @PositiveOrZero
//    @NegativeOrZero
    @DecimalMax("95.0") // valor tem que ser entre aspas
    private Double peso;

    @DecimalMin("0.01") // valor tem que ser entre aspas
    @DecimalMax("1.5") // valor tem que ser entre aspas
    private Double altura;

    @PastOrPresent
    // @Past
    private LocalDate nascimento; // aceita formato ISO

    @Future
    // @FutureOrPresent
    private LocalDate validadeChip;

//    @CNPJ
//    @TituloEleitoral
    @CPF // NÃO IMPORTA se tem ou não pontos e traços
    private String cpfDono;

    // valicação com uso de Expressões Regulares (Regex)
    @Pattern(
        regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
        message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX"
    )
    private String telefoneDono;

    private boolean ativo = true;
}
