package italo.santana.itau_desafio_cartoes.mapper;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SolicitacaoMapper {

    public SolicitacaoModel dtoToModel(ClienteRequestDTO data) {
        return new SolicitacaoModel(
                null,
                data.nome(),
                data.cpf(),
                data.idade(),
                data.dataNascimento(),
                data.uf(),
                data.rendaMensal(),
                data.email(),
                data.telefoneWhatsapp(),
                LocalDateTime.now()
        );
    }
}
