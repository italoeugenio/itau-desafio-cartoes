package italo.santana.itau_desafio_cartoes.models.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ClienteRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "CPF é obrigatóio")
        String cpf,

        @NotNull(message = "Idade é obrigatória")
        @Min(value = 0, message = "Idade não pode ser negativa")
        @Max(value = 150, message = "Idade inválida")
        Integer idade,

        @NotNull(message = "Data de nascimento é obrigatória")
        @PastOrPresent(message = "Data de nascimento tem que ser válida")
        LocalDate dataNascimento,

        @NotBlank(message = "UF é obrigatório")
        String uf,

        @NotNull(message = "Renda mensal é obrigaório")
        @Positive(message = "Renda mensal não deve ser negativa")
        BigDecimal rendaMensal,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        @Size(max = 255, message = "Email não pode passar de 255 caracteres")
        String email,

        @NotBlank(message = "Telefone obrigatório")
        @Pattern(regexp = "^[0-9]{11}$", message = "Telefone deve ter 11 dígitos")
        String telefoneWhatsapp
) {
}
