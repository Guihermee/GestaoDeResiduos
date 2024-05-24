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
    private Long idAterro;

    @Column(name = "QTD_ATUAL")
    private Long qtdAtual;

    @Column(name = "QTD_ATERRO")
    private Long qtdAterro;

    @Column(name = "NM_LOCALIZACAO")
    private String nmLocalizacao;

    @Column(name = "ST_CAPACIDADE")
    private Boolean stCapacidade;


    public Aterro(Long idAterro) {
        this.idAterro = idAterro;
    }

    public Long getIdAterro() {
        return idAterro;
    }

    public void setIdAterro(Long idAterro) {
        this.idAterro = idAterro;
    }

    public Boolean getStCapacidade() {
        return stCapacidade;
    }

    public void setStCapacidade(Boolean stCapacidade) {
        this.stCapacidade = stCapacidade;
    }

    public String getNmLocalizacao() {
        return nmLocalizacao;
    }

    public void setNmLocalizacao(String nmLocalizacao) {
        this.nmLocalizacao = nmLocalizacao;
    }

    public Long getQtdAterro() {
        return qtdAterro;
    }

    public void setQtdAterro(Long qtdAterro) {
        this.qtdAterro = qtdAterro;
    }

    public Long getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(Long qtdAtual) {
        this.qtdAtual = qtdAtual;
    }
}
