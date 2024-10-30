package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Aterro;

public record AterroExibicaoDto(
     Long idAterro,
     Long qtdAtual,
     Long qtdAterro,
     String nmLocalizacao,
     Boolean stCapacidade
) {
    public AterroExibicaoDto(Aterro aterro) {
        this(
                aterro.getIdAterro(),
                aterro.getQtdAtual(),
                aterro.getQtdAterro(),
                aterro.getNmLocalizacao(),
                aterro.getStCapacidade()
        );
    }
}
