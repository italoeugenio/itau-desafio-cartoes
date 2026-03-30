package italo.santana.itau_desafio_cartoes.models.dtos;

public record DetalheErroDTO(
        String app,
        String tipoErro,
        String mensagemInterna
) {}
