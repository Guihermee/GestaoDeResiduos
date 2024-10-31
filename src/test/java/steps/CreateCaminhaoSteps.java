package steps;

import com.networknt.schema.ValidationMessage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import model.ErrorMessageModel;
import org.json.JSONException;
import org.junit.Assert;
import services.CreateCaminhaoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateCaminhaoSteps {
    CreateCaminhaoService service = new CreateCaminhaoService();

    @Dado("que eu tenha os seguintes dados do caminhão:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
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

    @E("o corpo de resposta de erro do caminhão da api deve retornar a mensagem {string}")
    public void oCorpoDeRespostaDeErroDoCaminhãoDaApiDeveRetornarAMensagem(String message) {
        String test = service.response.jsonPath().prettify();
        ErrorMessageModel errorMessageModel = service.gson.fromJson(
                service.response.jsonPath().prettify(), ErrorMessageModel.class);
        Assert.assertEquals(message, errorMessageModel.getNmLocalizacao());
    }


    @E("que o arquivo de contrato de caminhao esperado é o {string}")
    public void queOArquivoDeContratoDeCaminhaoEsperadoÉO(String contract) throws IOException {
        service.setContract(contract);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato de caminhao selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoDeCaminhaoSelecionado() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = service.validateResponseAgainstSchema();
        Assert.assertTrue("O contrato está inválido. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }
}