package italo.santana.itau_desafio_cartoes.models.dtos;

import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;

import java.math.BigDecimal;

public record CartoeOfertadosResponseDTO(
        TiposDeCartoes tipoCartao,
        BigDecimal valorAnuidadeMensal,
        BigDecimal valorLimiteDisponivel,
        StatusCartao status
) {

}
