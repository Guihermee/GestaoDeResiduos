package br.com.fiap.GestaoDeResiduos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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
    private Long idFuncionario;

   // @Column(name = "ID_CAMINHAO")
    @ManyToOne
   // @JoinColumn(name = "ID_CAMINHAO")
    private Caminhao idCaminhao;

    @Column(name = "NM_FUNCIONARIO")
    private String nomeFuncionario;

    @Column(name = "NM_FUNCAO")
    private String nomeFuncao;

    @Column(name = "NM_DEPT")
    private String nomeDepartamento;

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Caminhao getIdCaminhao() {
        return idCaminhao;
    }

    public void setIdCaminhao(Caminhao idCaminhao) {
        this.idCaminhao = idCaminhao;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeFuncao() {
        return nomeFuncao;
    }

    public void setNomeFuncao(String nomeFuncao) {
        this.nomeFuncao = nomeFuncao;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(idFuncionario, that.idFuncionario) && Objects.equals(idCaminhao, that.idCaminhao) && Objects.equals(nomeFuncionario, that.nomeFuncionario) && Objects.equals(nomeFuncao, that.nomeFuncao) && Objects.equals(nomeDepartamento, that.nomeDepartamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFuncionario, idCaminhao, nomeFuncionario, nomeFuncao, nomeDepartamento);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", idCaminhao=" + idCaminhao +
                ", nomeFuncionario='" + nomeFuncionario + '\'' +
                ", nomeFuncao='" + nomeFuncao + '\'' +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                '}';
    }
}
