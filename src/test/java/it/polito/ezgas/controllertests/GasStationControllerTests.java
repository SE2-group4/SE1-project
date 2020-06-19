package it.polito.ezgas.controllertests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;

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

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.PriceReportDto;
import it.polito.ezgas.dto.UserDto;

public class GasStationControllerTests {

	static GasStationDto gsDto;

	@BeforeAll
	static public void setup() throws SQLException {

		gsDto = new GasStationDto();
		gsDto.setGasStationId(1);
		gsDto.setCarSharing("Enjoy");
		gsDto.setDieselPrice(0.);
		gsDto.setGasPrice(null);
		gsDto.setPremiumDieselPrice(null);
		gsDto.setGasStationAddress( "Via Rocciamelone Caselle Torinese Piemont Italy");
		gsDto.setGasStationName("Station1");
		gsDto.setHasDiesel(true);
		gsDto.setHasGas(false);
		gsDto.setHasMethane(false);
		gsDto.setHasPremiumDiesel(false);
		gsDto.setHasSuper(true);
		gsDto.setHasSuperPlus(false);
		gsDto.setLat(45.1635676);
		gsDto.setLon(7.6647799);
		gsDto.setMethanePrice(null);
		gsDto.setReportDependability(0);
		gsDto.setReportTimestamp(null);
		gsDto.setReportUser(-1);
		gsDto.setSuperPlusPrice(null);
		gsDto.setSuperPrice(0.);
		gsDto.setUserDto(null);
	}

	@Test
	public void testGetAllGasStation() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getAllGasStations");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gs_array = mapper.readValue(jsonFromResponse, GasStationDto[].class);

		assert(gs_array.length != 0);
	}

	@Test
	public void testGetGasStationById() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStation/1");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto gs = mapper.readValue(jsonFromResponse, GasStationDto.class);

		assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gsDto.getGasStationId());
        assertEquals(gs.getGasStationName(), gsDto.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gsDto.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gsDto.getHasDiesel());
        assertEquals(gs.getHasGas(), gsDto.getHasGas());
        assertEquals(gs.getHasMethane(), gsDto.getHasMethane());
        assertEquals(gs.getHasSuper(), gsDto.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gsDto.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gsDto.getCarSharing());
        assertEquals(gs.getDieselPrice(), gsDto.getDieselPrice());
        assertEquals(gs.getGasPrice(), gsDto.getGasPrice());
        assertEquals(gs.getMethanePrice(), gsDto.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gsDto.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gsDto.getSuperPlusPrice());
        assertEquals(gs.getLat(), gsDto.getLat());
        assertEquals(gs.getLon(), gsDto.getLon());

	}

	@Test
	public void testSaveGasStation() throws ClientProtocolException, IOException {
		HttpPost request = new HttpPost("http://localhost:8080/gasstation/saveGasStation");
		ObjectMapper mapper = new ObjectMapper();

	    //Converting the Object to JSONString
	    String jsonString = mapper.writeValueAsString(gsDto);
	    StringEntity json = new StringEntity(jsonString);
	    request.setHeader("Content-Type", "application/json");
	    request.setEntity(json);

	    HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(response.getStatusLine().getStatusCode(), 200);

	}

	@Test
	public void testDeleteGasStation() throws ClientProtocolException, IOException {
		HttpDelete request = new HttpDelete("http://localhost:8080/gasstation/deleteGasStation/3");

	    HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(response.getStatusLine().getStatusCode(), 200);

	}

	@Test
	public void testGetGasStationByGasolineType() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByGasolineType/Super");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gs_array = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		GasStationDto gs = gs_array[0];

		assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gsDto.getGasStationId());
        assertEquals(gs.getGasStationName(), gsDto.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gsDto.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gsDto.getHasDiesel());
        assertEquals(gs.getHasGas(), gsDto.getHasGas());
        assertEquals(gs.getHasMethane(), gsDto.getHasMethane());
        assertEquals(gs.getHasSuper(), gsDto.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gsDto.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gsDto.getCarSharing());
        assertEquals(gs.getDieselPrice(), gsDto.getDieselPrice());
        assertEquals(gs.getPremiumDieselPrice(), gsDto.getPremiumDieselPrice());
        assertEquals(gs.getGasPrice(), gsDto.getGasPrice());
        assertEquals(gs.getMethanePrice(), gsDto.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gsDto.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gsDto.getSuperPlusPrice());
        assertEquals(gs.getLat(), gsDto.getLat());
        assertEquals(gs.getLon(), gsDto.getLon());
	}

	@Test
	public void testGetGasStationByProximity() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/searchGasStationByProximity/45.1635676/7.6647799/1");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		System.out.println(jsonFromResponse);

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gs_array = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		GasStationDto gs = gs_array[0];

		assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gsDto.getGasStationId());
        assertEquals(gs.getGasStationName(), gsDto.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gsDto.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gsDto.getHasDiesel());
        assertEquals(gs.getHasGas(), gsDto.getHasGas());
        assertEquals(gs.getHasMethane(), gsDto.getHasMethane());
        assertEquals(gs.getHasSuper(), gsDto.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gsDto.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gsDto.getCarSharing());
        assertEquals(gs.getDieselPrice(), gsDto.getDieselPrice());
        assertEquals(gs.getGasPrice(), gsDto.getGasPrice());
        assertEquals(gs.getMethanePrice(), gsDto.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gsDto.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gsDto.getSuperPlusPrice());
        assertEquals(gs.getLat(), gsDto.getLat());
        assertEquals(gs.getLon(), gsDto.getLon());
	}

	@Test
	public void testGetGasStationWithCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithCoordinates/45.1635676/7.6647799/1/Diesel/Enjoy");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		System.out.println(jsonFromResponse);

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gs_array = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		GasStationDto gs = gs_array[0];

		assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gsDto.getGasStationId());
        assertEquals(gs.getGasStationName(), gsDto.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gsDto.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gsDto.getHasDiesel());
        assertEquals(gs.getHasGas(), gsDto.getHasGas());
        assertEquals(gs.getHasMethane(), gsDto.getHasMethane());
        assertEquals(gs.getHasSuper(), gsDto.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gsDto.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gsDto.getCarSharing());
        assertEquals(gs.getDieselPrice(), gsDto.getDieselPrice());
        assertEquals(gs.getGasPrice(), gsDto.getGasPrice());
        assertEquals(gs.getMethanePrice(), gsDto.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gsDto.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gsDto.getSuperPlusPrice());
        assertEquals(gs.getLat(), gsDto.getLat());
        assertEquals(gs.getLon(), gsDto.getLon());
	}

	/*@Test
	public void testGetGasStationWithoutCoordinates() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/gasstation/getGasStationsWithoutCoordinates/Diesel/Enjoy");

		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		String jsonFromResponse = EntityUtils.toString(response.getEntity());

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		GasStationDto[] gs_array = mapper.readValue(jsonFromResponse, GasStationDto[].class);
		GasStationDto gs = gs_array[0];

		assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gsDto.getGasStationId());
        assertEquals(gs.getGasStationName(), gsDto.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gsDto.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gsDto.getHasDiesel());
        assertEquals(gs.getHasGas(), gsDto.getHasGas());
        assertEquals(gs.getHasMethane(), gsDto.getHasMethane());
        assertEquals(gs.getHasSuper(), gsDto.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gsDto.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gsDto.getCarSharing());
        assertEquals(gs.getDieselPrice(), gsDto.getDieselPrice());
        assertEquals(gs.getGasPrice(), gsDto.getGasPrice());
        assertEquals(gs.getMethanePrice(), gsDto.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gsDto.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gsDto.getSuperPlusPrice());
        assertEquals(gs.getLat(), gsDto.getLat());
        assertEquals(gs.getLon(), gsDto.getLon());
	}*/

	@Test
	public void testSetGasStationReport() throws ClientProtocolException, IOException {
		PriceReportDto priceDto = new PriceReportDto(1, 2., null, null, 3., 3., 3., null);
		
		ObjectMapper mapper = new ObjectMapper();

	    //Converting the Object to JSONString
	    String jsonString = mapper.writeValueAsString(priceDto);
	    StringEntity json = new StringEntity(jsonString);
	    
	    
		HttpPost request = new HttpPost("http://localhost:8080/gasstation/setGasStationReport/");
		request.setHeader("Content-Type", "application/json");
	    request.setEntity(json);

	    HttpResponse response = HttpClientBuilder.create().build().execute(request);

		assertEquals(response.getStatusLine().getStatusCode(), 200);

	}
}
