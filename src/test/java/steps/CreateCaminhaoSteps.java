package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CreateCaminhaoService;

import java.util.List;
import java.util.Map;

public class CreateCaminhaoSteps {
    CreateCaminhaoService service = new CreateCaminhaoService();

    @Dado("que eu tenha os seguintes dados do caminhão:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            service.setFieldsCaminhao(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de caminhões")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeCaminhões(String endpoint) {
        service.createCaminhao(endpoint);
    }

    @Então("o status code na resposta deve ser {int}")
    public void oStatusCodeNaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, service.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageModel errorMessageModel = service.gson.fromJson(
                service.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getMessage());
    }
}
