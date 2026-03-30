package italo.santana.itau_desafio_cartoes.controller;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.SolicitacaoResponseDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.services.CartoesService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("cartoes")
public class CartoesController {

    @Autowired
    private CartoesService cartoesService;

    @PostMapping("")
    public ResponseEntity<SolicitacaoResponseDTO> solicitarCartoes(@Valid @RequestBody ClienteRequestDTO data){
        log.info("Recebida requisição POST /cartoes para o cliente: {}", data.nome());

        var response = cartoesService.solicitarCartoes(data);

        if (response.cartoeOfertados().isEmpty()){
            log.info("Nenhum cartão aprovado para o cliente: {}. Retornando status 204.", data.nome());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        log.info("Solicitação concluída com sucesso para o cliente: {}. Retornando status 200 com {} cartões.", data.nome(), response.cartoeOfertados().size());
        return ResponseEntity.ok(response);
    }
}
