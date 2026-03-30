package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.exception.cliente.ClienteInvalidoException;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.util.ClienteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
public class ClienteValidacaoService {

    public void validar(ClienteRequestDTO data) {
        log.info("Iniciando validacão do cliente: {}({})", data.nome(),data.cpf());
        validarCpf(data.cpf());
        validarUf(data.uf());
        validarIdadeComDataDeNascimento(data.idade(), data.dataNascimento());
        validarIdadeMinimaParaCartao(data.idade());
        validarRenda(data.rendaMensal());
    }

    private void validarCpf(String cpf) {
        if (!ClienteValidator.isValidCpf(cpf)) {
            log.warn("CPF INVÁLIDO: {}", cpf);
            throw new ClienteInvalidoException("CPF INVÁLIDO");
        }
    }

    private void validarUf(String uf) {
        if (!ClienteValidator.isValidUf(uf)) {
            log.warn("UF INVÁLIDO: {}", uf);
            throw new ClienteInvalidoException("UF INVÁLIDO");
        }
    }

    private void validarIdadeComDataDeNascimento(int idade, LocalDate dataNascimento) {
        if (!ClienteValidator.isIdadeCompativelComDataNascimento(idade, dataNascimento)) {
            log.warn("Idade {} incompatível com a data de nascimento: {}. ", idade, dataNascimento);
            throw new ClienteInvalidoException("Idade incompatível com a data de nascimento.");
        }
    }

    private void validarIdadeMinimaParaCartao(int idade){
        if(idade < 18){
            log.warn("O cliente precisa ter 18 anos ou mais. Idade de {} não é permitida", idade);
            throw new ClienteInvalidoException("Cliente menor de 18 não é permitido");
        }
    }

    private void validarRenda(BigDecimal renda){
        if(renda.compareTo(BigDecimal.ZERO) < 0){
            log.warn("A renda de {} não pode ser negativa", renda);
            throw new ClienteInvalidoException("Renda não pode ser negativa");
        }
    }
}