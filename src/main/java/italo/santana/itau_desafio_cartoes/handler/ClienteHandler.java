package italo.santana.itau_desafio_cartoes.handler;

import italo.santana.itau_desafio_cartoes.exception.cliente.ClienteInvalidoException;
import italo.santana.itau_desafio_cartoes.models.dtos.DetalheErroDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.ErroResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ClienteHandler {

    @ExceptionHandler(ClienteInvalidoException.class)
    public ResponseEntity<ErroResponseDTO> clienteHandler(ClienteInvalidoException ex) {
        log.warn("Regra de negócio foi violada: {}", ex.getMessage());
        var detalhe = new DetalheErroDTO(
                "Ítau Ofertas Cartoes",
                "CLIENTE_INVALIDO",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErroResponseDTO(
                        "422",
                        "Regra de negócio não atendida.",
                        detalhe)
                );
    }
}
