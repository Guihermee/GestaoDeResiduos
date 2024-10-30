package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.junit.Assert;
import services.CreateAterroService;

import java.util.List;
import java.util.Map;

public class CreateAterroSteps {
    CreateAterroService service = new CreateAterroService();

    @Dado("que eu tenha os seguintes dados do Aterro:")
    public void queEuTenhaOsSeguintesDadosDoAterro(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            service.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de Aterro")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeAterro(String endpoint) {
        service.createDelivery(endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, service.response.statusCode());
    }

    @E("O corpo de resposta de erro da API deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaAPIDeveRetornarAMensagem(String messageError) {
        ErrorMessageModel errorMessageModel = service.gson.fromJson(
                service.response.jsonPath().prettify(), ErrorMessageModel.class
        );
        Assert.assertEquals(messageError, errorMessageModel.getMessage());
    }
}
