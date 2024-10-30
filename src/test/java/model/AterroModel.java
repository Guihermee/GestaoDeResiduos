package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class AterroModel {
    @Expose(serialize = false)
    private Long idAterro;
    @Expose
    private Long qtdAtual;
    @Expose
    private Long qtdAterro;
    @Expose
    private String nmLocalizacao;
    @Expose
    private Boolean stCapacidade;
}
