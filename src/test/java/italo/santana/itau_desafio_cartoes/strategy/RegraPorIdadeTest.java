package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.mapper.ClienteMapper;
import italo.santana.itau_desafio_cartoes.models.domain.Cliente;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegraPorIdadeTest {

    private RegraPorIdade regraPorIdade;
    private ClienteMapper clienteMapper;

    private Cliente clienteValido() {
        return Cliente.builder()
                .nome("Cliente Teste")
                .cpf("123.456.789-09")
                .idade(19)
                .dataNascimento(LocalDate.of(2003, 12, 20))
                .uf("SP")
                .rendaMensal(new BigDecimal("4000.00"))
                .email("cliente@teste.com")
                .telefoneWhatsapp("11999992020")
                .build();
    }

    List<CartaoOfertadoModel> cartoes = List.of(
            createCartao(TiposDeCartoes.CARTAO_SEM_ANUIDADE, new BigDecimal("0.00")),
            createCartao(TiposDeCartoes.CARTAO_COM_CASHBACK, new BigDecimal("20.00")),
            createCartao(TiposDeCartoes.CARTAO_DE_PARCEIROS, new BigDecimal("10.00"))
    );

    private CartaoOfertadoModel createCartao(TiposDeCartoes tipo, BigDecimal anuidade) {
        return CartaoOfertadoModel.builder()
                .id(UUID.fromString("3f1c9a6e-8d2b-4f7a-9c51-2e6b8d4f1a90")) //UUID válido aleatório para testes
                .tiposDeCartoes(tipo)
                .valorAnuidadeMensal(anuidade)
                .limiteDiponivel(BigDecimal.valueOf(tipo.getLimiteDisponivel()).setScale(2))
                .rendaMensalNecessaria(BigDecimal.valueOf(tipo.getRendaMensalMinima()).setScale(2))
                .isAtivo(true)
                .build();
    }

    @BeforeEach()
    void setup() {
        regraPorIdade = new RegraPorIdade();
        clienteMapper = new ClienteMapper();
    }

    @Test
    @DisplayName("Clientes de 19-24 anos. Devem ser ofertados somente o cartão “Cartão de crédito sem anuidade” ")
    void aplicarCaso1() {
        Cliente cliente = clienteValido();
        var cartoeAvaliados = regraPorIdade.aplicar(clienteMapper.modelToDtoRequest(cliente), this.cartoes);

        assertThat(cartoeAvaliados).isEqualTo(List.of(
                createCartao(TiposDeCartoes.CARTAO_SEM_ANUIDADE, new BigDecimal("0.00"))
        ));
    }

    @Test
    @DisplayName("Clientes que não tem entre 19-24")
    void aplicarCaso2() {
        Cliente cliente = clienteValido();
        cliente.setIdade(18);
        var cartoeAvaliados = regraPorIdade.aplicar(clienteMapper.modelToDtoRequest(cliente), this.cartoes);

        assertThat(cartoeAvaliados).isEqualTo(List.of(
                createCartao(TiposDeCartoes.CARTAO_SEM_ANUIDADE, new BigDecimal("0.00")),
                createCartao(TiposDeCartoes.CARTAO_COM_CASHBACK, new BigDecimal("20.00")),
                createCartao(TiposDeCartoes.CARTAO_DE_PARCEIROS, new BigDecimal("10.00"))
        ));
    }
}