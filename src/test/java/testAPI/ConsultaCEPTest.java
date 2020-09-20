package testAPI;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ConsultaCEPTest {

	String url = "http://viacep.com.br/ws/";

	@Test
	public void validaStatusCode() {
		// viacep.com.br/ws/{{CEP}}/json/

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		
		RestAssured.baseURI = url; // Setando a URL

		RestAssured.given().relaxedHTTPSValidation().contentType(ContentType.JSON).given().get(endpoint).then()
				.statusCode(200);

	}

}
