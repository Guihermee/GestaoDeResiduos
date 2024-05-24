package br.com.fiap.GestaoDeResiduos.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_CAMINHAO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Caminhao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAMINHAO")
    @SequenceGenerator(name = "SEQ_CAMINHAO", sequenceName = "SEQ_CAMINHAO", allocationSize = 1)
    @Column(name = "id_caminhao")
    private Long idCaminhao;

    @Column(name = "qtd_atual")
    private Integer qtdAtual;

    @Column(name = "vl_capacidade")
    private Integer vlCapacidade;

    @Column(name = "nm_localizacao")
    private String nmLocalizacao;


    //

    public Caminhao(Long idCaminhao) {
        this.idCaminhao = idCaminhao;
    }

    public Long getIdCaminhao() {
        return idCaminhao;
    }

    public void setIdCaminhao(Long idCaminhao) {
        this.idCaminhao = idCaminhao;
    }

    public Integer getQtdAtual() {
        return qtdAtual;
    }

    public void setQtdAtual(Integer qtdAtual) {
        this.qtdAtual = qtdAtual;
    }

    public Integer getVlCapacidade() {
        return vlCapacidade;
    }

    public void setVlCapacidade(Integer vlCapacidade) {
        this.vlCapacidade = vlCapacidade;
    }

    public String getNmLocalizacao() {
        return nmLocalizacao;
    }

    public void setNmLocalizacao(String nmLocalizacao) {
        this.nmLocalizacao = nmLocalizacao;
    }


}
