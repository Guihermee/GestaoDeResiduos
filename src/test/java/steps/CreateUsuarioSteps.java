package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import jakarta.validation.Valid;
import model.ErrorMessageSenhaModel;
import org.json.JSONException;
import org.junit.Assert;
import services.CreateUsuarioService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateUsuarioSteps {
    CreateUsuarioService createUsuarioService = new CreateUsuarioService();

    @Dado("que eu tenha os seguintes dados da entrada:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            createUsuarioService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisicao para o endpoint {string} de cadastro de usuarios")
    public void euEnviarARequisicaoParaOEndpointDeCadastroDeUsuarios(String endpoint) {
        createUsuarioService.createDelivery(endpoint);
    }

    @Então("o status da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, createUsuarioService.response.statusCode());
    }

    @E("o corpo de resposta de erro da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaApiDeveRetornarAMensagem(String message) {
        ErrorMessageSenhaModel errorMessageSenhaModel = createUsuarioService.gson.fromJson(
                createUsuarioService.response.jsonPath().prettify(), ErrorMessageSenhaModel.class);
        Assert.assertEquals(message, errorMessageSenhaModel.getSenha());
    }

    @E("que o arquivo de contrato esperado é o {string}")
    public void queOArquivoDeContratoEsperadoÉO(String contract) throws IOException, JSONException {
        createUsuarioService.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = createUsuarioService.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}
