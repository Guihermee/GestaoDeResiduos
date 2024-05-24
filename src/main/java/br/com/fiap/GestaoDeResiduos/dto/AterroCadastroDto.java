package br.com.fiap.GestaoDeResiduos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AterroCadastroDto(
        Long idAterro,

        @NotNull(message = "Quantidade atual é obrigatório!")
        Long qtdAtual,

        @NotNull(message = "Quantidade do aterro é obrigatório")
        Long qtdAterro,

        @NotBlank(message = "Nome localização é obrigatório")
        String nmLocalizacao,

        Boolean stCapacidade
) {
}
