package italo.santana.itau_desafio_cartoes.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "solicitacao")
@Table(name = "solicicatcao_de_cartoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name="uf", nullable = false)
    private String uf;

    @Column(precision = 10, scale = 2,name = "renda_mensal", nullable = false)
    private BigDecimal rendaMensal;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone_whatsapp", nullable = false)
    private String telefoneWhatsapp;

    @Column(name = "data_solicitacao", nullable = false)
    private LocalDateTime dataSolicitacao = LocalDateTime.now();

}
