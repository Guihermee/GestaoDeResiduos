package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COLETA")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLETA")
    @SequenceGenerator(name = "SEQ_COLETA", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_COLETA")
    private Long id;
}
