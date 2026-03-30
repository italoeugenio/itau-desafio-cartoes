package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.mapper.CartaoAvaliadoMapper;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoAvaliadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoAvaliadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaoAvaliadoService {

    @Autowired
    private CartaoAvaliadoRepository cartaoAvaliadoRepository;

    @Autowired
    private CartaoAvaliadoMapper cartaoAvaliadoMapper;

    public List<CartaoAvaliadoModel> saveCartoesAvaliados(List<CartaoOfertadoModel> cartoesAprovados, List<CartaoOfertadoModel> cartoesNegados, SolicitacaoModel solicitacaoModel) {

        List<CartaoAvaliadoModel> resultado = new ArrayList<>();

        cartoesAprovados.forEach(cartao -> resultado.add(
                cartaoAvaliadoMapper.cartaoOfertadoToCartaoAvaliado(cartao, solicitacaoModel, StatusCartao.APROVADO)
        ));

        cartoesNegados.forEach(cartao -> resultado.add(
                cartaoAvaliadoMapper.cartaoOfertadoToCartaoAvaliado(cartao, solicitacaoModel, StatusCartao.NEGADO)
        ));

        return cartaoAvaliadoRepository.saveAll(resultado);
    }
}
