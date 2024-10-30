package br.com.fiap.GestaoDeResiduos.repository;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

    @Query("SELECT c FROM Caminhao c WHERE c.qtdAtual < c.vlCapacidade ORDER BY c.idCaminhao")
    public List<Caminhao> findByQtdAtualLessThanVlCapacidade();

}
