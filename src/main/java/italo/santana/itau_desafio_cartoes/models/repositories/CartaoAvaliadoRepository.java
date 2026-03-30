package italo.santana.itau_desafio_cartoes.models.repositories;

import italo.santana.itau_desafio_cartoes.models.entities.CartaoAvaliadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaoAvaliadoRepository extends JpaRepository<CartaoAvaliadoModel, UUID> {
}
