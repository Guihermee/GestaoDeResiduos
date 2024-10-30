package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.model.Funcionario;
import jakarta.persistence.Column;

public record FuncionarioExibicaoDto(
        Long idFuncionario,

        Caminhao idCaminhao,

        String nomeFuncionario,

        String nomeFuncao,

        String nomeDepartamento
) {
    public FuncionarioExibicaoDto(Funcionario funcionario){
        this(
                funcionario.getIdFuncionario(),
                funcionario.getIdCaminhao(),
                funcionario.getNomeFuncionario(),
                funcionario.getNomeFuncao(),
                funcionario.getNomeDepartamento()
        );
    }
}

