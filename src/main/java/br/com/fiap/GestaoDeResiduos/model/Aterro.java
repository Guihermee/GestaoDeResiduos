package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ATERRO")
public class Aterro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATERRO")
    @SequenceGenerator(name = "SEQ_ATERRO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_ATERRO")
    private Long id;

    @Column(name = "QTD_ATUAL")
    private Long qtdAtual;

    @Column(name = "QTD_ATERRO")
    private Long qtdAterro;

    @Column(name = "NM_LOCALIZACAO")
    private String nmLocalizacao;

    @Column(name = "ST_CAPACIDADE")
    private Boolean stCapacidade;
}
