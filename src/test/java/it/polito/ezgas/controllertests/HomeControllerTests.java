package it.polito.ezgas.controllertests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;

public class HomeControllerTests {

	static IdPw id_pw;
	
	@BeforeAll
	static public void setup() {
		
		id_pw = new IdPw();
		id_pw.setUser("admin@ezgas.com");
		id_pw.setPw("admin");
	
	}
	
	@Test
	public void testLogin() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://localhost:8080/user/login");
		ObjectMapper mapper = new ObjectMapper();
		
	    //Converting the Object to JSONString
	    String jsonString = mapper.writeValueAsString(id_pw);
	    System.out.println(jsonString);

	    StringEntity json = new StringEntity(jsonString);
	    System.out.println(json);

	    request.setHeader("Content-Type", "application/json");
	    request.setEntity(json);	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(request);
	    
		assertEquals(response.getStatusLine().getStatusCode(), 200);

	    String jsonFromResponse = EntityUtils.toString(response.getEntity());
	    System.out.println(jsonFromResponse);
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		LoginDto loginDto = mapper.readValue(jsonFromResponse, LoginDto.class);
		
	    assertEquals(loginDto.getUserName(), "admin");
	    assertEquals(loginDto.getEmail(), id_pw.getUser());
	    assertEquals(loginDto.getReputation(), 2);
	    assertEquals(loginDto.getUserId(), 1);
	        
	}
}
