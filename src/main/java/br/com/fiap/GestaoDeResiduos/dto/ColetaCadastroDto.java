package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Caminhao;

public record ColetaCadastroDto (
        Long idColeta,
        Boolean stColeta,
        String nmLocalizacao,
        Caminhao caminhao
){
}
