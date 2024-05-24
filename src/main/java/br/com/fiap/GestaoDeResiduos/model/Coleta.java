package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "T_COLETA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COLETA")
    @SequenceGenerator(name = "SEQ_COLETA", initialValue = 1, allocationSize = 1)
    @Column(name = "ID_COLETA")
    private Long idColeta;

    @Column(name ="ST_COLETA")
    private Boolean stColeta;

    @Column(name = "NM_LOCALIZACAO")
    private String nmLocalizacao;

    @ManyToOne
    @JoinColumn(name = "caminhao")
    private Caminhao caminhao;

    @Override
    public String toString() {
        return "Coleta{" +
                "idColeta=" + this.idColeta +
                ", stColeta=" + this.stColeta +
                ", nmLocalizacao='" + this.nmLocalizacao + '\'' +
                ", caminhao=" + this.caminhao.getIdCaminhao() +
                '}';
    }


    //


    public Long getIdColeta() {
        return idColeta;
    }

    public void setIdColeta(Long idColeta) {
        this.idColeta = idColeta;
    }

    public Boolean getStColeta() {
        return stColeta;
    }

    public void setStColeta(Boolean stColeta) {
        this.stColeta = stColeta;
    }

    public String getNmLocalizacao() {
        return nmLocalizacao;
    }

    public void setNmLocalizacao(String nmLocalizacao) {
        this.nmLocalizacao = nmLocalizacao;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }
}
