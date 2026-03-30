package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.mapper.SolicitacaoMapper;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.SolicitacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private SolicitacaoMapper solicitacaoMapper;

    public SolicitacaoModel saveSolicitacao(ClienteRequestDTO data){
        log.info("Criando solicitacão para o cliente: {}({})", data.nome(), data.cpf());
        SolicitacaoModel solicitacaoModel = solicitacaoMapper.dtoToModel(data);
        return solicitacaoRepository.save(solicitacaoModel);
    }
}
