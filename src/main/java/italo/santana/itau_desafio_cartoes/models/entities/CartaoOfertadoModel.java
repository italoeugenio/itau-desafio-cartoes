package italo.santana.itau_desafio_cartoes.models.entities;

import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "cartaoOfertado")
@Table(name = "cartoes_ofertados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
@ToString
public class CartaoOfertadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipos_de_cartoes", nullable = false, unique = true)
    private TiposDeCartoes tiposDeCartoes;

    @Column(precision = 10, scale = 2, name = "valor_anuidade_mensal" , nullable = false)
    private BigDecimal valorAnuidadeMensal;

    @Column(precision = 10, scale = 2, name = "limite_disponivel", nullable = false)
    private BigDecimal limiteDiponivel;

    @Column(precision = 10, scale = 2, name = "renda_mensal_necessaria", nullable = false)
    private BigDecimal rendaMensalNecessaria;

    @Column(name = "is_ativo", nullable = true)
    private boolean isAtivo = true;
}
