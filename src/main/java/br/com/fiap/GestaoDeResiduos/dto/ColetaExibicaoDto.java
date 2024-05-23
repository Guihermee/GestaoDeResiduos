package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;
import br.com.fiap.GestaoDeResiduos.model.Coleta;

public record ColetaExibicaoDto(
        Long idColeta,
        Boolean stColeta,
        String nmLocalizacao,
        Caminhao caminhao
) {
    public ColetaExibicaoDto (Coleta coleta){
        this(
                coleta.getIdColeta(),
                coleta.getStColeta(),
                coleta.getNmLocalizacao(),
                coleta.getCaminhao()
        );
    }

    public Long getIdCaminhao(){
        return caminhao.getIdCaminhao();
    }

}
