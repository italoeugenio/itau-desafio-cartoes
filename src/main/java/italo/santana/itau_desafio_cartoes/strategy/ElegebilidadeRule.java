package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;

import java.util.List;

public interface ElegebilidadeRule {
    List<CartaoOfertadoModel> aplicar(ClienteRequestDTO cliente, List<CartaoOfertadoModel> cartao);
}
