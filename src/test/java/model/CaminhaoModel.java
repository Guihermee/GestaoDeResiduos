package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class CaminhaoModel {

    @Expose(serialize = false)
    private Long idCaminhao;
    @Expose
    private Integer qtdAtual;
    @Expose
    private Integer vlCapacidade;
    @Expose
    private String nmLocalizacao;

}
