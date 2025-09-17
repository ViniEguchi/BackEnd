package sptech.projeto07.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor // Cria um construtor com todos os atributos
@NoArgsConstructor // Cria um construtor sem nenhum atributo
@Getter // Cria os getters para todos os atributos
@Setter // Cria os setters para todos os atributos

/*
@Data cria os getters, setters, toString, equals e hashCode
Ao invés de usar @Data é melhor criar um Record
 */
@Data
@ToString // gera um toString() com todos os atributos
@EqualsAndHashCode // gera os métodos equals() e hashCode() com todos os atributos
@Builder // Cria um builder para a classe segue o padrão do projeto builder
@Entity
public class Raca {
    @Id
    private String codigo;

    // @Getter // cria o getter apenas para o atributo nome
    @NotBlank
    private String nome;
}
