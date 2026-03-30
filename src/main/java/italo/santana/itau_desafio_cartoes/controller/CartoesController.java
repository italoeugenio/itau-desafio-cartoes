package italo.santana.itau_desafio_cartoes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cartões", description = "API para consultar cartões de crédito elegíveis")
public class CartoesController {

    @Autowired
    private CartoesService cartoesService;

    @Operation(
            summary = "Consultar cartões elegíveis para o cliente",
            description = "Retorna a lista de cartões que o cliente pode contratar de acordo com as regras de negócio"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Sucesso - Retorna uma lista de cartões elegíveis",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SolicitacaoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Nenhum cartão elegível encontrado para o cliente (sem conteúdo)"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Requisição inválida - parâmetros obrigatórios ausentes ou formato incorreto"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Regras de negócio não atendidas"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno inesperado"
            )
    })
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
