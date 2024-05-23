package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CAMINHAO")
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAMINHAO")
    @SequenceGenerator(name = "SEQ_CAMINHAO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_CAMINHAO")
    private Long id;

    @Column(name = "QTD_ATUAL")
    private Long qtdAtual;

    @Column(name = "VL_CAPACIDADE")
    private Long vlCapacidade;

    @Column(name = "NM_LOCALIZACAO")
    private String nmLocalizacao;
}
