package italo.santana.itau_desafio_cartoes.models.dtos;

import italo.santana.itau_desafio_cartoes.models.domain.Cliente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SolicitacaoResponseDTO(
        UUID numeroSolicitacao,
        LocalDateTime dataSolicitacao,
        Cliente cliente,
        List<CartoeOfertadosResponseDTO> cartoeOfertados
) {
}
