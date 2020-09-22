package testAPI;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;

import org.junit.Test;


import utils.RestUtils;

public class ConsultaCEPTest {

	String url = "http://viacep.com.br/ws/";

	@Test
	public void validaStatusCode() {
		// viacep.com.br/ws/{{CEP}}/json/

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		RestUtils.setEndpoint(endpoint);

		RestUtils.setUrl(url);

		 RestUtils.get();
		assertEquals(200, RestUtils.getStatusCode());

	}

	@Test
	public void validaDadosCEP() {

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		RestUtils.setEndpoint(endpoint);
		LinkedHashMap<String, String> header = new LinkedHashMap<>();
		header.put("cliente-id", "curso");
		header.put("Authorization", "Basic YWRtaW46YWRtaW4=");

		RestUtils.setUrl(url); // Setando a URL

		RestUtils.get( header);
		
		//JsonPath json = RestUtils.getResponse().getBody().jsonPath();
		assertEquals(200,RestUtils.getStatusCode());
		assertEquals("Rua Artur César Rios",  RestUtils.getValue("logradouro"));
		assertEquals("Barbalho",  RestUtils.getValue("bairro"));
		assertEquals("Salvador",  RestUtils.getValue("localidade"));
	}
	
	@Test
	public void validaDadosCEPParam() {

		String cep = "40301230";
		String endpoint = cep.concat("/json/");
		RestUtils.setEndpoint(endpoint);
		LinkedHashMap<String, String> param = new LinkedHashMap<>();
		param.put("cliente-id", "curso");
		param.put("nome", "Iuri");

		RestUtils.setUrl(url); // Setando a URL

		RestUtils.getParams( param);
		
		//JsonPath json = RestUtils.getResponse().getBody().jsonPath();
		assertEquals(200,RestUtils.getStatusCode());
		assertEquals("Rua Artur César Rios", RestUtils.getValue("logradouro"));
		assertEquals("Barbalho",  RestUtils.getValue("bairro"));
		assertEquals("Salvador",  RestUtils.getValue("localidade"));
	}
	
	//public RequestSpecification initRequest(ContentType contentType, LinkedHashMap<String, String>header) {
		//return RestAssured.given().relaxedHTTPSValidation().contentType(contentType).headers(header);
		
	//}
	

	


}
