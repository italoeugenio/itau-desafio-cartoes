package italo.santana.itau_desafio_cartoes.models.dtos;


public record ErroResponseDTO(
        String codigo,
        String mensagem,
        DetalheErroDTO detalheErro
) {}