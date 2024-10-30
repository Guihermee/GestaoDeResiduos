package br.com.fiap.GestaoDeResiduos.repository;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.model.Coleta;
import br.com.fiap.GestaoDeResiduos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    //@Query("SELECT f FROM Funcionario f WHERE f.nm_Fun like :nm_Funcionario%")
    @Query("SELECT f FROM Funcionario f WHERE f.nomeFuncionario like :nm_Funcionario%")
    public Optional<Funcionario> findByNomeFuncionario(@Param("nm_Funcionario")String nomeFuncionario);

}
