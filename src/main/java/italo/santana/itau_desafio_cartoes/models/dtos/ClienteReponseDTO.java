package italo.santana.itau_desafio_cartoes.models.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ClienteReponseDTO(
        String nome,
        String cpf,
        Integer idade,
        LocalDate dataNascimento,
        String uf,
        BigDecimal rendaMensal,
        String email,
        String telefoneWhatsapp
){
}
