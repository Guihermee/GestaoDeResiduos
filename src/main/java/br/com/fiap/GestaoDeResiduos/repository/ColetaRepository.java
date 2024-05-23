package br.com.fiap.GestaoDeResiduos.repository;

import br.com.fiap.GestaoDeResiduos.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {

    @Query("SELECT c FROM Coleta c WHERE c.nmLocalizacao= :nmLocalizacao")
    Optional<Coleta> findByNomeLocalizacao(@Param("nmLocalizacao")String nmLocalizacao);

}
