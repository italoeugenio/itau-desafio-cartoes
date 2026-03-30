package italo.santana.itau_desafio_cartoes.models.services;

import italo.santana.itau_desafio_cartoes.models.dtos.ClienteRequestDTO;
import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import italo.santana.itau_desafio_cartoes.models.repositories.CartaoOfertadoRepository;
import italo.santana.itau_desafio_cartoes.strategy.ElegebilidadeRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElegibilidadeService {
    private final List<ElegebilidadeRule> condicionais;
    private final CartaoOfertadoRepository cartaoOfertadoRepository;

    public ElegibilidadeService(List<ElegebilidadeRule> condicionais, CartaoOfertadoRepository cartaoOfertadoRepository) {
        this.condicionais = condicionais;
        this.cartaoOfertadoRepository = cartaoOfertadoRepository;
    }


    public List<CartaoOfertadoModel> aplicarFiltro(ClienteRequestDTO cliente){
        var cartao = cartaoOfertadoRepository.findByIsAtivoTrue();

        for(ElegebilidadeRule condicao : condicionais){
            cartao = condicao.aplicar(cliente, cartao);
        }

        return cartao;
    }
}
