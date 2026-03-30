package italo.santana.itau_desafio_cartoes.models.repositories;

import italo.santana.itau_desafio_cartoes.models.entities.CartaoOfertadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartaoOfertadoRepository extends JpaRepository<CartaoOfertadoModel, UUID> {
    List<CartaoOfertadoModel> findByIsAtivoTrue();
}
