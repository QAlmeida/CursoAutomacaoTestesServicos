package testAPI;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ConsultaCEPTest {

	String url = "http://viacep.com.br/ws/";

	@Test
	public void validaStatusCode() {
		// viacep.com.br/ws/{{CEP}}/json/

		String cep = "40301230";
		String endpoint = cep.concat("/json/");

		RestAssured.baseURI = url; // Setando a URL

		Response response = get(endpoint);
		assertEquals(200, response.statusCode());

	}

	@Test
	public void validaDadosCEP() {

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		LinkedHashMap<String, String> header = new LinkedHashMap<>();
		header.put("cliente-id", "curso");
		header.put("Authorization", "Basic YWRtaW46YWRtaW4=");

		RestAssured.baseURI = url; // Setando a URL

		Response response = initRequest(ContentType.JSON, header)
				.when()
				.get(endpoint)
				.then()
				.statusCode(200)
				.extract()
				.response();
		
		JsonPath json = response.getBody().jsonPath();
		
		assertEquals("Rua Artur César Rios", json.get("logradouro"));
		assertEquals("Barbalho", json.get("bairro"));
		assertEquals("Salvador", json.get("localidade"));
	}
	
	@Test
	public void validaDadosCEPParam() {

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		LinkedHashMap<String, String> param = new LinkedHashMap<>();
		param.put("cliente-id", "curso");
		param.put("nome", "Iuri");

		RestAssured.baseURI = url; // Setando a URL

		Response response = initRequest(ContentType.JSON)
				.params(param)
				.when()
				.get(endpoint)
				.then()
				.statusCode(200)
				.extract()
				.response();
		
		JsonPath json = response.getBody().jsonPath();
		
		assertEquals("Rua Artur César Rios", json.get("logradouro"));
		assertEquals("Barbalho", json.get("bairro"));
		assertEquals("Salvador", json.get("localidade"));
	}
	
	public RequestSpecification initRequest(ContentType contentType, LinkedHashMap<String, String>header) {
		return RestAssured.given().relaxedHTTPSValidation().contentType(contentType).headers(header);
		
	}
	
	public RequestSpecification initRequest(ContentType contentType) {
		return RestAssured.given().relaxedHTTPSValidation().contentType(contentType);
		
	}
	
	public Response get(String endpoint) {
		return initRequest(ContentType.JSON).when().get(endpoint)
		.then().extract().response();
	}

}
