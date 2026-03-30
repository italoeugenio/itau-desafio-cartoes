package italo.santana.itau_desafio_cartoes.models.entities;


import italo.santana.itau_desafio_cartoes.enums.StatusCartao;
import italo.santana.itau_desafio_cartoes.enums.TiposDeCartoes;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "cartoesAvaliados")
@Table(name = "tb_cartoes_avaliados_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class CartaoAvaliadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private SolicitacaoModel idSolicitacaoModel;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipos_de_cartoes", nullable = false)
    private TiposDeCartoes tiposDeCartoes;

    @Column(precision = 10, scale = 2, name = "valor_anuidade_mensal" , nullable = false)
    private BigDecimal valorAnuidadeMensal;

    @Column(precision = 10, scale = 2, name = "limite_disponivel", nullable = false)
    private BigDecimal limiteDiponivel;

    @Column(precision = 10, scale = 2, name = "renda_mensal_necessaria", nullable = false)
    private BigDecimal rendaMensalNecessaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusCartao statusCartao;
}
