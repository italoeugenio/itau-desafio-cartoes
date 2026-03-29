package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertado;

import java.util.List;

public interface ElegebilidadeRule {
    List<CartaoOfertado> aplicar(ClienteRequestDTO cliente, List<CartaoOfertado> cartao);
}
