package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.exception.cliente.ClienteInvalidoException;
import italo.santana.itau_desafio_cartoes.mapper.ClienteMapper;
import italo.santana.itau_desafio_cartoes.models.domain.Cliente;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ClienteValidacaoServiceTest {

    private ClienteMapper clienteMapper;
    private ClienteValidacaoService clienteValidacaoService;

    private Cliente clienteValido() {
        return Cliente.builder()
                .nome("Cliente Teste")
                .cpf("123.456.789-09")
                .idade(22)
                .dataNascimento(LocalDate.of(2003, 12, 20))
                .uf("SP")
                .rendaMensal(new BigDecimal("4000.00"))
                .email("cliente@teste.com")
                .telefoneWhatsapp("11999992020")
                .build();
    }

    @BeforeEach
    void setup(){
        clienteValidacaoService = new ClienteValidacaoService();
        clienteMapper = new ClienteMapper();
    }

    // ─── Caso geral ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Não deve lançar exceção caso todos os parâmetros sejam válidos")
    void validarCasoTodosParametrosValidos() {
        Cliente cliente = this.clienteValido();
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        assertDoesNotThrow(() -> clienteValidacaoService.validar(data));
    }

    // ─── CPF ───────────────────────────────────────────────────────────────────
    @Test
    @DisplayName("Deve lançar ClienteInvalidoException caso o CPF seja inválido")
    void validarCasoCpfInvalido() {
        Cliente cliente = this.clienteValido();
        cliente.setCpf("894321ND9WEQIOF");
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        ClienteInvalidoException exception = assertThrows(ClienteInvalidoException.class, () -> clienteValidacaoService.validar(data));
        assertThat(exception.getMessage()).isEqualTo("CPF INVÁLIDO");
    }

    // ─── UF ────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Deve lançar ClienteInvalidoException caso a UF seja inválida")
    void validarCasoUfInvalida() {
        Cliente cliente = this.clienteValido();
        cliente.setUf("SPP");
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        ClienteInvalidoException exception = assertThrows(ClienteInvalidoException.class, () -> clienteValidacaoService.validar(data));
        assertThat(exception.getMessage()).isEqualTo("UF INVÁLIDO");
    }

    // ─── Idade x Data de Nascimento ────────────────────────────────────────────

    @Test
    @DisplayName("Deve lançar ClienteInvalidoException caso a idade não corresponda à data de nascimento")
    void validarCasoIdadeIncompativelComDataNascimento() {
        // Diz ter 22 anos mas nasceu em 1990 — inconsistente
        Cliente cliente = this.clienteValido();
        cliente.setIdade(22);
        cliente.setDataNascimento(LocalDate.of(1990, 10, 20));
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        ClienteInvalidoException exception = assertThrows(ClienteInvalidoException.class, () -> clienteValidacaoService.validar(data));
        assertThat(exception.getMessage()).isEqualTo("Idade incompatível com a data de nascimento.");
    }

    // ─── Idade mínima ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("Deve lançar ClienteInvalidoException caso o cliente seja menor de 18 anos")
    void validarCasoIdadeMinimaInvalida() {
        Cliente cliente = clienteValido();
        cliente.setIdade(17);
        cliente.setDataNascimento(LocalDate.of(2008,12,23));
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        ClienteInvalidoException exception = assertThrows((ClienteInvalidoException.class), () -> clienteValidacaoService.validar(data));

        assertThat(exception.getMessage()).isEqualTo("Cliente menor de 18 não é permitido");
    }

    // ─── Renda ─────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Deve lançar ClienteInvalidoException caso a renda seja negativa")
    void validarCasoRendaNegativa() {
        Cliente cliente = clienteValido();
        cliente.setRendaMensal(new BigDecimal("-1000"));
        ClienteRequestDTO data = clienteMapper.modelToDtoRequest(cliente);

        ClienteInvalidoException exception = assertThrows((ClienteInvalidoException.class), () -> clienteValidacaoService.validar(data));

        assertThat(exception.getMessage()).isEqualTo("Renda não pode ser negativa");
    }
}