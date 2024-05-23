package br.com.fiap.GestaoDeResiduos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CaminhaoCadastroDto(
        Long idCaminhao,

        @NotNull(message = "Campo quantidadeAtual é obrigatório!")
        Integer qtdAtual,

        @NotNull(message = "Campo valorCapacidade é obrigatório")
        //@Size(max = 150, message = "Tamanho máximo do caminhão é de 150")
        Integer vlCapacidade,

        @NotBlank(message = "Campo nmLocalizacao é obrigatório")
        String nmLocalizacao
) {
}
