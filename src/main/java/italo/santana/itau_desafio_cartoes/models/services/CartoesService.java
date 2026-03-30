package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.mapper.CartaoAvaliadoMapper;
import italo.santana.itau_desafio_cartoes.mapper.ClienteMapper;
import italo.santana.itau_desafio_cartoes.models.dtos.CartoesOfertadosResponseDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.SolicitacaoResponseDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoAvaliadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoOfertadoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CartoesService {
    @Autowired
    private CartaoOfertadoRepository cartaoOfertadoRepository;

    @Autowired
    private CartaoAvaliadoService cartaoAvaliadoService;

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private ElegibilidadeService elegibilidadeService;

    @Autowired
    private ClienteValidacaoService clienteValidacaoService;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private CartaoAvaliadoMapper cartaoAvaliadoMapper;

    @Transactional
    public SolicitacaoResponseDTO solicitarCartoes(ClienteRequestDTO data){
        log.info("Iniciando processamento de solicitação para: {}({})", data.nome(), data.cpf());
        clienteValidacaoService.validar(data);

        //Salva no banco os cartoes com status de aprovado e negado para o cliente
        SolicitacaoModel solicitacaoModel = solicitacaoService.saveSolicitacao(data);
        List<CartaoOfertadoModel> cartoeDisponiveis = cartaoOfertadoRepository.findByIsAtivoTrue();
        List<CartaoOfertadoModel> cartoesAprovados = elegibilidadeService.aplicarFiltro(data);
        List<CartaoOfertadoModel> cartoesNegados =  cartoeDisponiveis.stream()
                .filter(c -> !cartoesAprovados.contains(c))
                .toList();
        List<CartaoAvaliadoModel> cartoesAvaliados = cartaoAvaliadoService.saveCartoesAvaliados(cartoesAprovados,cartoesNegados, solicitacaoModel);


        //Converte para o retorno da api
        List<CartoesOfertadosResponseDTO> cartoesResponse = cartoesAvaliados.stream()
                .filter(c -> c.getStatusCartao() == StatusCartao.APROVADO)
                .map(c -> cartaoAvaliadoMapper.modelTodto(c))
                .toList();

        SolicitacaoResponseDTO response = new SolicitacaoResponseDTO(
                solicitacaoModel.getId(), solicitacaoModel.getDataSolicitacao(), clienteMapper.dtoToModel(data),cartoesResponse
        );

        return response;
    }

}
