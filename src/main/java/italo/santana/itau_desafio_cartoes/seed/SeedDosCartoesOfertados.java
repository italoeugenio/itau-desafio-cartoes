package italo.santana.itau_desafio_cartoes.seed;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoAvaliadoRepository;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoOfertadoRepository;
import italo.santana.itau_desafio_cartoes.models.repositories.SolicitacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SeedDosCartoesOfertados implements CommandLineRunner {

    private final CartaoOfertadoRepository cartaoOfertadoRepository;
    private final CartaoAvaliadoRepository cartaoAvaliadoRepository;
    private final SolicitacaoRepository solicitacaoRepository;

    public SeedDosCartoesOfertados(CartaoOfertadoRepository cartaoOfertadoRepository, CartaoAvaliadoRepository cartaoAvaliadoRepository, SolicitacaoRepository solicitacaoRepository) {
        this.cartaoOfertadoRepository = cartaoOfertadoRepository;
        this.cartaoAvaliadoRepository = cartaoAvaliadoRepository;
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        cartaoOfertadoRepository.deleteAll();
        cartaoAvaliadoRepository.deleteAll();
        solicitacaoRepository.deleteAll();


        List<CartaoOfertadoModel> cartoes = List.of(
                buildCartao(TiposDeCartoes.CARTAO_SEM_ANUIDADE, new BigDecimal("0.00")),
                buildCartao(TiposDeCartoes.CARTAO_COM_CASHBACK, new BigDecimal("20.00")),
                buildCartao(TiposDeCartoes.CARTAO_DE_PARCEIROS, new BigDecimal("10.00"))
        );

        cartaoOfertadoRepository.saveAll(cartoes);
    }

    private CartaoOfertadoModel buildCartao(TiposDeCartoes tipo, BigDecimal anuidade) {
        return CartaoOfertadoModel.builder()
                .tiposDeCartoes(tipo)
                .valorAnuidadeMensal(anuidade)
                .limiteDiponivel(BigDecimal.valueOf(tipo.getLimiteDisponivel()).setScale(2))
                .rendaMensalNecessaria(BigDecimal.valueOf(tipo.getRendaMensalMinima()).setScale(2))
                .isAtivo(true)
                .build();
    }
}
