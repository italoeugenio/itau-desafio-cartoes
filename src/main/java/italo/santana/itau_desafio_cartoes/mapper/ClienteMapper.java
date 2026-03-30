package italo.santana.itau_desafio_cartoes.mapper;

import italo.santana.itau_desafio_cartoes.models.domain.Cliente;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteReponseDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente dtoToModel(ClienteRequestDTO data) {
        return Cliente.builder()
                .nome(data.nome())
                .cpf(data.cpf())
                .idade(data.idade())
                .dataNascimento(data.dataNascimento())
                .uf(data.uf())
                .rendaMensal(data.rendaMensal())
                .email(data.email())
                .telefoneWhatsapp(data.telefoneWhatsapp())
                .build();

    }


    public ClienteReponseDTO modelToDto(Cliente cliente){
        return new ClienteReponseDTO(
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getIdade(),
                cliente.getDataNascimento(),
                cliente.getUf(),
                cliente.getRendaMensal(),
                cliente.getEmail(),
                cliente.getTelefoneWhatsapp()
        );
    }
}
