package testAPI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import entities.Category;
import entities.Pet;
import entities.Tag;
import utils.RestUtils;

public class CadastroPetTest {

	String url = "https://petstore.swagger.io/v2/";
	String endpoint = "pet";

	@Test
	public void validaStatusCode() {
		RestUtils.setUrl(url);
		RestUtils.setEndpoint(endpoint);

		String json = "{\"category\": { \"id\": 0, \"name\": \"Theozinho\"},\"name\": \"Theozinho\",\"photoUrls\": [ \"string\"],\"tags\": [ {\"id\": 0,\"name\": \"string\"}],\"status\": \"available\"}";

		RestUtils.post(json);
		
		assertEquals(200, RestUtils.getStatusCode());

	}
	
	@Test
	public void validaStatusCodeMap() {
		RestUtils.setUrl(url);
		RestUtils.setEndpoint(endpoint);
		
//		LinkedHashMap<String, Object> json = new LinkedHashMap<>();
//		LinkedHashMap<String, Object> category = new LinkedHashMap<>();
//		List<String> listPhoto = new ArrayList<String>();
//		LinkedHashMap<String, Object> tag = new  LinkedHashMap<>();
//		List<LinkedHashMap<String, Object>> listTags = new ArrayList<LinkedHashMap<String, Object>>();
//		
//		category.put("id", 0);
//		category.put("name", "teste api");
//		
//		json.put("category", category);
//		json.put("name", "Theo");
//		
//		listPhoto.add("string");
//		json.put("photUrls", listPhoto);
//		
//		tag.put("id", 0);
//		tag.put("name", "tag");
//		listTags.add(tag);
//		json.put("tags", listTags);
//		
//		json.put("status", "available");
		
		Category category = new Category(0, "teste api");
		List<Object> listPhoto = new ArrayList<Object>();
		listPhoto.add("string");
		Tag tag = new Tag(0, "tag");
		List<LinkedHashMap<String, Object>> tags = new ArrayList<LinkedHashMap<String, Object>>();
		tags.add(tag.get());
		Pet pet = new Pet(category, "Theo", listPhoto, tags, "available") ;
		
		RestUtils.post(pet.get());
		assertEquals(200, RestUtils.getStatusCode());
		
		assertEquals("teste api", RestUtils.getValue("category.name"));
		assertEquals("Theo", RestUtils.getValue("name"));
		assertEquals("tag", RestUtils.getValue("tags[0].name"));
		
		assertNotEquals("", RestUtils.getValue("id"));
		assertNotEquals(null, RestUtils.getValue("id"));
		
	}

}
