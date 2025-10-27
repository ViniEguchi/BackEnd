package sptech.projeto10.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AtualizacaoDonoEmailTelefoneRequest(
        @NotBlank @Size(min = 4, max = 40) String dono,
        @Email String email,

        @Pattern(
                regexp = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})",
                message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX"
        ) String telefone
) {
}
