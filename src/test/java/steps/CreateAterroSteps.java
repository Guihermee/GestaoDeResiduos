package steps;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageNmLocalizacaoModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;
import services.CreateAterroService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @E("O corpo de resposta de erro da API de aterro deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDaAPIDeAterroDeveRetornarAMensagem(String messageError) {
        ErrorMessageNmLocalizacaoModel errorMessageModel = service.gson.fromJson(
                service.response.jsonPath().prettify(), ErrorMessageNmLocalizacaoModel.class
        );
        Assert.assertEquals(messageError, errorMessageModel.getNmLocalizacao());
    }

    @E("que o arquivo de contrato de aterro esperado é o {string}")
    public void queOArquivoDeContratoDeAterroEsperadoÉO(String contract) throws IOException {
        service.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato de aterro selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws JSONException, IOException {
        Set<ValidationMessage> validationMessages = service.validateResponseAgainstSchema();
        Assert.assertTrue("Contrato Inválido. Erros encontrados" + validationMessages, validationMessages.isEmpty());
    }
}
