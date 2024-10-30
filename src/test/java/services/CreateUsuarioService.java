package services;

import br.com.fiap.GestaoDeResiduos.model.UsuarioRole;
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
import model.UsuarioModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class CreateUsuarioService {

    String schemasPath = "src/test/resources/schemas/";
    JSONObject jsonSchema;
    private final ObjectMapper mapper = new ObjectMapper();

    final UsuarioModel user = new UsuarioModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation().create();

    public Response response;
    String baseUrl = "http://localhost:8080/";


    public void setFieldsDelivery(String field, String value) {
        Random random = new Random();

        switch (field) {
            case "idUsuario" -> user.setIdUsuario(Long.parseLong(value));
            case "nome" -> user.setNome(value);
            case "email" -> {
                int randomNumber = random.nextInt(10000) + 1; // Gera um número aleatório entre 1 e 10000
                String emailWithNumber = value + randomNumber;
                user.setEmail(emailWithNumber);
            }
            case "senha" -> user.setSenha(value);
            case "role" -> {
                switch (value) {
                    case "ADMIN" -> user.setRole(UsuarioRole.ADMIN);
                    case "USER" -> user.setRole(UsuarioRole.USER);
                    default -> {
                        List<String> userRoles = new ArrayList<>();
                        for (UsuarioRole role : UsuarioRole.values()) {
                            userRoles.add(role.toString());
                        }
                        throw new IllegalArgumentException("Valor inválido para usuarioRole, você quis dizer alguns desses? UsuarioRole: " + userRoles);
                    }
                }
            }
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    public void createDelivery(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(user);
        response = given()
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

    public void setContract(String contract) throws IOException, JSONException {
        switch (contract) {
            case "Cadastro bem-sucedido de usuario" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-bem-sucedido-de-usuario.json");
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
