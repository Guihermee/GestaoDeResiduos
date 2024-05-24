package br.com.fiap.GestaoDeResiduos.repository;

import br.com.fiap.GestaoDeResiduos.model.Aterro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AterroRepository extends JpaRepository<Aterro, Long> {

    @Query("SELECT a FROM Aterro a WHERE a.stCapacidade = true ORDER BY a.idAterro")
    public List<Aterro> findByStatusTrue();

}
