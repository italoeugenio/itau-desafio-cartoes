package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.mapper.CartaoAvaliadoMapper;
import italo.santana.itau_desafio_cartoes.mapper.ClienteMapper;
import italo.santana.itau_desafio_cartoes.models.dtos.CartoeOfertadosResponseDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.SolicitacaoResponseDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoAvaliadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoOfertadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
        List<CartoeOfertadosResponseDTO> cartoesResponse = cartoesAvaliados.stream()
                .filter(c -> c.getStatusCartao() == StatusCartao.APROVADO)
                .map(c -> cartaoAvaliadoMapper.modelTodto(c))
                .toList();

        SolicitacaoResponseDTO response = new SolicitacaoResponseDTO(
                solicitacaoModel.getId(), solicitacaoModel.getDataSolicitacao(), clienteMapper.dtoToModel(data),cartoesResponse
        );

        return response;
    }

}
