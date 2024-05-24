package br.com.fiap.GestaoDeResiduos.dto;

import br.com.fiap.GestaoDeResiduos.model.Usuario;

public record UsuarioExibicaoDto(
        Long idUsuario,
        String nome,
        String email
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
