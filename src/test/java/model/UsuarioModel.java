package model;

import br.com.fiap.GestaoDeResiduos.model.UsuarioRole;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {
    @Expose(serialize = false)
    private Long idUsuario;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose
    private String senha;
    @Expose
    private UsuarioRole role;
}
