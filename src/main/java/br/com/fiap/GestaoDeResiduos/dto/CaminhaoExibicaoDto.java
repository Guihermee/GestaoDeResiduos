package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;

public record CaminhaoExibicaoDto(
        Long idCaminhao,
        Integer qtdAtual,
        Integer vlCapacidade,
        String nmLocalizacao
) {
    public CaminhaoExibicaoDto(Caminhao caminhao) {
        this(
                caminhao.getIdCaminhao(),
                caminhao.getQtdAtual(),
                caminhao.getVlCapacidade(),
                caminhao.getNmLocalizacao()
        );
    }
}
