package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class RegraPorSalario implements ElegebilidadeRule {
    @Override
    public List<CartaoOfertadoModel> aplicar(ClienteRequestDTO cliente, List<CartaoOfertadoModel> cartao) {
        return cartao.stream()
                .filter(c -> cliente.rendaMensal().compareTo(c.getRendaMensalNecessaria()) >= 0)
                .toList();
    }


}
