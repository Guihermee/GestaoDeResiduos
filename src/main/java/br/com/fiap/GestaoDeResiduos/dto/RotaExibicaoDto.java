package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Aterro;
import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.model.Rota;

public record RotaExibicaoDto(
        Long idRota,
        Caminhao idCaminhao,
        Aterro idAterro
) {
    public  RotaExibicaoDto(Rota rota){
        this(
                rota.getIdRota(),
                rota.getIdCaminhao(),
                rota.getIdAterro()
        );
    }
}
