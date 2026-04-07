package italo.santana.itau_desafio_cartoes.models.repositories;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class CartaoOfertadoRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CartaoOfertadoRepository cartaoOfertadoRepository;

    @Test
    @DisplayName("Deveria retornar uma lista com pelo menos 1 cartão")
    void findByIsAtivoTrueCase1() {
        List<CartaoOfertadoModel> cartoes = List.of(
                createCartao(TiposDeCartoes.CARTAO_SEM_ANUIDADE, new BigDecimal("0.00")),
                createCartao(TiposDeCartoes.CARTAO_COM_CASHBACK, new BigDecimal("20.00")),
                createCartao(TiposDeCartoes.CARTAO_DE_PARCEIROS, new BigDecimal("10.00"))
        );

        var resultado = cartaoOfertadoRepository.findByIsAtivoTrue();

        assertThat(!resultado.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Deveria retornar uma lista vazia")
    void findByIsAtivoTrueCase2() {
        var resultado = cartaoOfertadoRepository.findByIsAtivoTrue();
        assertThat(resultado.isEmpty()).isTrue();

    }

    private CartaoOfertadoModel createCartao(TiposDeCartoes tipo, BigDecimal anuidade){
       CartaoOfertadoModel newCartao = CartaoOfertadoModel.builder()
                .tiposDeCartoes(tipo)
                .valorAnuidadeMensal(anuidade)
                .limiteDiponivel(BigDecimal.valueOf(tipo.getLimiteDisponivel()).setScale(2))
                .rendaMensalNecessaria(BigDecimal.valueOf(tipo.getRendaMensalMinima()).setScale(2))
                .isAtivo(true)
                .build();
       this.entityManager.persist(newCartao);
       return newCartao;
    }
}