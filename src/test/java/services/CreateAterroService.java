package services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AterroModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CreateAterroService {
    final AterroModel aterro = new AterroModel();
    String baseUrl = "http://localhost:8080/";

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;

    // JSON
    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Preenche todos os parâmetros para a "entrega" (requisição), ou seja, ele constrói o objeto AterroModel.
     * Ele não retorna nada pois ele faz mudanças na variável estática da classe, então é aconselhavél executar um
     * Serviço por teste.
     *
     * @param field Campo que será preenchido (CHAVE)
     * @param value Valor que vai ser preenchido no campo (VALOR)
     */
    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "qtdAtual" -> aterro.setQtdAtual(Long.parseLong(value));
            case "qtdAterro" -> aterro.setQtdAterro(Long.parseLong(value));
            case "nmLocalizacao" -> aterro.setNmLocalizacao(value);
            case "stCapacidade" -> aterro.setStCapacidade(Boolean.parseBoolean(value));
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    /**
     * Faz a "entrega" (Requisição), com um Bearer previamente feito (dura 1 ano, não é muito aconselhável isso, o certo
     * seria fazer um login, pegar a resposta da API, e usar esse token para se autenticar. Mas estamos com pouco tempo
     * então peço compreensão da parte de quem estiver lendo, esse código não vai dar erro até a data 30/10/2025)
     * Com o Bearer feito, criamos o JSON para ser enviado pelo body com o Gson, então fazendo o post e guardamos a
     * resposta em uma variável estática.
     *
     * @param endpoint
     */
    public void createDelivery(String endpoint) {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnZXN0YW9EZVJlc2lkdW9zIiwic3ViIjoieHBndWlAb3V0bG9vay5jb20iLCJleHAiOjE3NjE4NjQ2ODF9.-Ve0sPDtLiE0rsz7uxSnehYbqUalUOWcjlJXIgDniUg";
        String url = baseUrl + endpoint;
        String bodyToSend = gson.toJson(aterro);
        response = given()
                .headers(
                        "Authorization",
                        "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    /**
     * Função responsável por carregar um aquivo JSON do diretório do projeto.
     *
     * @param filePath Caminho do arquivo que deseja carregar.
     * @return O arquivo JSON caso exista.
     * @throws IOException pode gerar exceção caso o caminho passado esteja incorreto. Double check THE PATH MY BOY
     */
    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try {
            String content = Files.readString(Paths.get(filePath));
            JSONTokener tokener = new JSONTokener(content);
            return new JSONObject(tokener);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Com base no contrato (vem lá do Feature esse cara) que tiver no parâmetro, escolhe o caminho correto para ele.
     *
     * @param contract contrato a qual vai ser buscado o arquivo JSON com o esquema correto para o contrato
     * @throws IOException pode gerar a exceção caso o contrato não esteja no Case Switch
     */
    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de aterro" ->
                    jsonSchema = loadJsonFromFile(schemasPath + "create-bem-sucedido-de-aterro.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    /**
     * Compara dois Schemas um com o outro, com as váriaveis estáticas atuais do Service.
     */
    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }

}
