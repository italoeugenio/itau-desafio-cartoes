package italo.santana.itau_desafio_cartoes.mapper;

import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.models.dtos.CartoesOfertadosResponseDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoAvaliadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import org.springframework.stereotype.Component;

@Component
public class CartaoAvaliadoMapper {
    public CartaoAvaliadoModel cartaoOfertadoToCartaoAvaliado(CartaoOfertadoModel cartaoOfertadoModel, SolicitacaoModel solicitacaoModel, StatusCartao status){
        return new CartaoAvaliadoModel(
                null,
                solicitacaoModel,
                cartaoOfertadoModel.getTiposDeCartoes(),
                cartaoOfertadoModel.getValorAnuidadeMensal(),
                cartaoOfertadoModel.getLimiteDiponivel(),
                cartaoOfertadoModel.getRendaMensalNecessaria(),
                status
        );
    }

    public CartoesOfertadosResponseDTO modelTodto(CartaoAvaliadoModel cartaoAvaliado) {
        return new CartoesOfertadosResponseDTO(
                cartaoAvaliado.getTiposDeCartoes(),
                cartaoAvaliado.getValorAnuidadeMensal(),
                cartaoAvaliado.getLimiteDiponivel(),
                cartaoAvaliado.getStatusCartao()
        );
    }
}
