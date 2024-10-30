package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CreateCaminhaoService;

import java.util.List;
import java.util.Map;

public class CreateCaminhaoSteps {
    CreateCaminhaoService service = new CreateCaminhaoService();

    @Dado("que eu tenha os seguintes dados da entrega:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows){
            service.setFieldsCaminhao(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de caminhões")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeCaminhões(String endPoint) {
        service.createCaminhao(endPoint);
    }

    @Então("o status code na resposta deve ser {int}")
    public void oStatusCodeNaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, service.response.statusCode());
    }
}
