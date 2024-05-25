package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Aterro;
import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import jakarta.validation.constraints.NotNull;

public record RotaCadastroDto(
        Long idRota,

        @NotNull(message = "Campo idCaminhao é obrigatório")
        Caminhao idCaminhao,

        @NotNull(message = "Campo idAterro é obrigatório")
        Aterro idAterro
) {
}
