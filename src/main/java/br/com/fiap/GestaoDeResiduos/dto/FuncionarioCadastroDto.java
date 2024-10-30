package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioCadastroDto(
        Long idFuncionario,

        @NotNull(message = "O id do caminhão é obrigatório!")
        Caminhao idCaminhao,

        @NotNull(message = "O nome do funcionario é obrigatório!")
        String nomeFuncionario,

        @NotNull(message = "O nome da função é obrigatória!")
        String nomeFuncao,

        @NotNull(message = "O nome do departamento é obrigatório!")
        String nomeDepartamento


) {
}
