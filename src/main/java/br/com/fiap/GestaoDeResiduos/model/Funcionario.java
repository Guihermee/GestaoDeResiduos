package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "T_FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FUNCIONARIO")
    @SequenceGenerator(name = "SEQ_FUNCIONARIO", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;
}
