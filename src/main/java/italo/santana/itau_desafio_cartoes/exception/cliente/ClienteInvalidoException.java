package italo.santana.itau_desafio_cartoes.exception.cliente;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClienteInvalidoException extends RuntimeException{
    public ClienteInvalidoException(String message){
        super(message);
    }
}
