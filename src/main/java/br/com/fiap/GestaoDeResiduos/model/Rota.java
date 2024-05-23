package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ROTA")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ROTA")
    @SequenceGenerator(name = "SEQ_ROTA", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_ROTA")
    private Long id;


}
