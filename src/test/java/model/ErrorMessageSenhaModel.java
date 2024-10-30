package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErrorMessageSenhaModel {
    @Expose
    private String senha;
}