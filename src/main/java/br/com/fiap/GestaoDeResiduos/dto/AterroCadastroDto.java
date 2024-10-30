package br.com.fiap.GestaoDeResiduos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AterroCadastroDto(
        Long idAterro,

        @NotNull(message = "Quantidade atual é obrigatório!")
        Long qtdAtual,

        @NotNull(message = "Quantidade do aterro é obrigatório")
        Long qtdAterro,

        @NotBlank(message = "Nome localização é obrigatório")
        @Size(min = 6, max = 100, message = "Nome da localização é muito curto!")
        String nmLocalizacao,

        Boolean stCapacidade
) {
}
