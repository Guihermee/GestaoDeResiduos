package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CaminhaoModel;

import static io.restassured.RestAssured.given;

public class CreateCaminhaoService {
    final CaminhaoModel caminhao = new CaminhaoModel();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public Response response;
    String baseUrl = "http://localhost:8080/api";

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
}
