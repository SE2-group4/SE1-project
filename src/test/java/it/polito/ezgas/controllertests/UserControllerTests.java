package it.polito.ezgas.controllertests;


import java.io.IOException;
import java.sql.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.polito.ezgas.dto.UserDto;

public class UserControllerTests {
	
	@BeforeAll
	static public void setUp() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		
		PreparedStatement st = null;
		int result_from_query;
				
		try {
			st = conn.prepareStatement("SELECT COUNT(*) FROM USER");
			st.execute();
			result_from_query = ((Number) st.getResultSet().getObject(1)).intValue();
		} 
		
		catch(SQLException e) {
			System.out.println(e.getMessage());
			conn.close();
		}
		
		finally {
			if(st!=null) {
				st.close();
			}
		}
	}
	
	@Test
	public void testGetAllUser() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/user/getAllUsers");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserDto[] userarray = mapper.readValue(jsonFromResponse, UserDto[].class);
		
		assert(userarray.length == 6);
	}
}
