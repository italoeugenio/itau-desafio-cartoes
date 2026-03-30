package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegraPorUF implements ElegebilidadeRule {
    @Override
    public List<CartaoOfertadoModel> aplicar(ClienteRequestDTO cliente, List<CartaoOfertadoModel> cartoes) {
        if ("SP".equals(cliente.uf())) {
            if (cliente.idade() >= 25 && cliente.idade() < 30) {
                return cartoes;
            }
            return cartoes.stream()
                    .filter(c -> c.getTiposDeCartoes() != TiposDeCartoes.CARTAO_DE_PARCEIROS)
                    .collect(Collectors.toList());
        }

        return cartoes;
    }
}
