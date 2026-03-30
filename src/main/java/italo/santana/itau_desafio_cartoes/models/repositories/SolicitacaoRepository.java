package italo.santana.itau_desafio_cartoes.models.repositories;

import italo.santana.itau_desafio_cartoes.models.entities.SolicitacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolicitacaoRepository extends JpaRepository<SolicitacaoModel, UUID> {
}
