package it.polito.ezgas.controllertests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.sql.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserControllerTests {
	
	static UserDto user;
	
	@BeforeAll
	static public void setup() {
		user = new UserDto();
		user.setUserId(1);
		user.setUserName("admin");
		user.setPassword("admin");
		user.setEmail("admin@ezgas.com");
		user.setReputation(2);
		user.setAdmin(true);
	}
	
	@Test
	public void testGetAllUser() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userarray = mapper.readValue(jsonFromResponse, UserDto[].class);
		
		//assert(userarray.length == 6);
	}
	
	@Test
	public void testGetUserById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getUser/1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto userDto = mapper.readValue(jsonFromResponse, UserDto.class);
		
		assertNotNull(userDto);
		assertEquals(user.getAdmin(), userDto.getAdmin());
		assertEquals(user.getReputation(), userDto.getReputation());
        assertEquals(user.getUserName(), userDto.getUserName());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getEmail(), userDto.getEmail());
	}
	
	@Test
	public void testSaveUser() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://localhost:8080/user/saveUser");
		ObjectMapper mapper = new ObjectMapper();
		
	    //Converting the Object to JSONString
	    String jsonString = mapper.writeValueAsString(user);
	    StringEntity json = new StringEntity(jsonString);
	    request.setHeader("Content-Type", "application/json");
	    request.setEntity(json);
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(request);
	    
		assertEquals(response.getStatusLine().getStatusCode(), 200);
        
	}
	
	/*@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete("http://localhost:8080/user/deleteUser/3");
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(request);
	    
		assertEquals(response.getStatusLine().getStatusCode(), 200);
        
	}
	
	@Test
	public void testIncreaseReputation() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://localhost:8080/user/increaseUserReputation/2");
		
	    request.setHeader("Content-Type", "application/json");
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(request);
	    
	    String jsonFromResponse = EntityUtils.toString(response.getEntity());
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Integer reputation = mapper.readValue(jsonFromResponse, Integer.class);
	    
		assertEquals(1, reputation);
        
	}
	
	@Test
	public void testDecreaseReputation() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://localhost:8080/user/decreaseUserReputation/2");
		
	    request.setHeader("Content-Type", "application/json");
	    
	    HttpResponse response = HttpClientBuilder.create().build().execute(request);
	    
	    String jsonFromResponse = EntityUtils.toString(response.getEntity());
	    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Integer reputation = mapper.readValue(jsonFromResponse, Integer.class);
	    
		assertEquals(-1, reputation);
        
	}*/
}
