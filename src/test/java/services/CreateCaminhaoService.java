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
import model.CaminhaoModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CreateCaminhaoService {
    final CaminhaoModel caminhao = new CaminhaoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080/api";

    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    public void setFieldsCaminhao(String field, String value) {
        switch (field) {
            case "qtdAtual" -> caminhao.setQtdAtual(Integer.parseInt(value));
            case "vlCapacidade" -> caminhao.setVlCapacidade(Integer.parseInt(value));
            case "nmLocalizacao" -> caminhao.setNmLocalizacao(value);
            default -> throw new IllegalStateException("Unexpected field: " + field); // Adicionado ':' após "field"
        }
    }

    public void createCaminhao(String endPoint) {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnZXN0YW9EZVJlc2lkdW9zIiwic3ViIjoibWFyY2VsaW5lQGhvdG1haWwuY29tIiwiZXhwIjoxNzYxODU4ODQ4fQ.IN8mThEMJKqI6sNoT5rbXAflrUi8M8PEgEuP7CGgcjo";
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(caminhao);
        response = given()
                .headers("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException {
        try {
            String content = Files.readString(Paths.get(filePath));
            JSONTokener tokener = new JSONTokener(content);
            return new JSONObject(tokener);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void setContract(String contract) throws IOException {
        switch (contract) {
            case "Cadastro bem-sucedido de caminhão" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-caminhao.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema() throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}
