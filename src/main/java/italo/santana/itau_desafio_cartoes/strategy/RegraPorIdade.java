package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegraPorIdade implements ElegebilidadeRule {
    @Override
    public List<CartaoOfertadoModel> aplicar(ClienteRequestDTO cliente, List<CartaoOfertadoModel> cartao) {
        if (cliente.idade() > 18 && cliente.idade() < 25) {
            return cartao.stream()
                    .filter(c -> c.getTiposDeCartoes() == TiposDeCartoes.CARTAO_SEM_ANUIDADE)
                    .toList();
        }
        return cartao;
    }
}
