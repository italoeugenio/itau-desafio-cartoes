package italo.santana.itau_desafio_cartoes.strategy;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertado;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegraPorUF implements ElegebilidadeRule {
    @Override
    public List<CartaoOfertado> aplicar(ClienteRequestDTO cliente, List<CartaoOfertado> cartoes) {
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
