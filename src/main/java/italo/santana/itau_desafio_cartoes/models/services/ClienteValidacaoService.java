package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.exception.cliente.ClienteInvalidoException;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.util.ClienteValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ClienteValidacaoService {

    public void validar(ClienteRequestDTO dto) {
        validarCpf(dto.cpf());
        validarUf(dto.uf());
        validarIdadeComDataDeNascimento(dto.idade(), dto.dataNascimento());
    }

    private void validarCpf(String cpf) {
        if (!ClienteValidator.isValidCpf(cpf)) {
            throw new ClienteInvalidoException("CPF INVÁLIDO");
        }
    }

    private void validarUf(String uf) {
        if (!ClienteValidator.isValidUf(uf)) {
            throw new ClienteInvalidoException("UF INVÁLIDO");
        }
    }

    private void validarIdadeComDataDeNascimento(int idade, LocalDate dataNascimento) {
        if (!ClienteValidator.isIdadeCompativelComDataNascimento(idade, dataNascimento)) {
            throw new ClienteInvalidoException("Idade incompatível com a data de nascimento.");
        }
    }
}