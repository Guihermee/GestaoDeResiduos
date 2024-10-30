package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.AterroModel;
import net.bytebuddy.implementation.bytecode.Throw;

import static io.restassured.RestAssured.given;

public class CreateAterroService {
    final AterroModel aterro = new AterroModel();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080/";

    public void setFieldsDelivery(String field, String value) {
        switch (field) {
            case "qtdAtual" -> aterro.setQtdAtual(Long.parseLong(value));
            case "qtdAterro" -> aterro.setQtdAterro(Long.parseLong(value));
            case "nmLocalizacao" -> aterro.setNmLocalizacao(value);
            case "stCapacidade" -> aterro.setStCapacidade(Boolean.parseBoolean(value));
            default -> throw new IllegalArgumentException("Invalid field: " + field);
        }
    }

    public void createDelivery(String endpoint) {
        String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnZXN0YW9EZVJlc2lkdW9zIiwic3ViIjoieHBndWlAb3V0bG9vay5jb20iLCJleHAiOjE3MzAyNTcxMzV9.9Gjl5XMs6_t9PTJN_nO5eTTLR-pJZBilfZ8A6_DXtIY";
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

}
