package italo.santana.itau_desafio_cartoes.handler;

import italo.santana.itau_desafio_cartoes.models.dtos.DetalheErroDTO;
import italo.santana.itau_desafio_cartoes.models.dtos.ErroResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validacoesNaoAtendidas = new HashMap<>();

        for(FieldError fieldError: ex.getFieldErrors()){
            validacoesNaoAtendidas.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        var mensagem = validacoesNaoAtendidas.values().toString().replaceAll("[\\[\\]\\{\\}]", "");

        DetalheErroDTO detalheErro = new DetalheErroDTO(
                "Itau cartões ",
                "VALIDACAO_FALHOU",
                mensagem
        );

        ErroResponseDTO erroResponse = new ErroResponseDTO(
                "400",
                "Algum critério de validação não foi atendido",
                detalheErro
        );
        log.warn("Erro na validacao da entrada: {}", mensagem);

        return new ResponseEntity<>(erroResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponseDTO> handleGenericException(Exception ex) {
        log.warn("Ocorreu algum problema inesperado na aplicação");

        DetalheErroDTO detalheErro = new DetalheErroDTO(
                "Itau cartões",
                "SERVICO_INDISPONIVEL",
                "Tivemos um problema, mas fique tranquilo que nosso time já foi avisado."
        );

        ErroResponseDTO erroResponse = new ErroResponseDTO(
                "500",
                "Um erro inesperado ocorreu.",
                detalheErro
        );

        return new ResponseEntity<>(erroResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
