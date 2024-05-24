package br.com.fiap.GestaoDeResiduos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadatroDto(
        Long idUsuario,

        @NotBlank(message = "Campo nome é obrigatório")
        String nome,

        @NotBlank(message = "Campo email é obrigatório")
        String email,

        @NotBlank(message = "Campo senha é obrigatório")
        @Size(min = 6, max = 25, message = "A senha deve conter de 6 a 25 caracteres")
        String senha
) {
}
