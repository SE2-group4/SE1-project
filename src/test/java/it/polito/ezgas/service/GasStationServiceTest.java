package it.polito.ezgas.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.GasStationServiceimpl;



public class GasStationServiceTest {

	@Autowired
	static GasStationService gasStationService;	

	static GasStation gs1;
	GasStation gs2;
	List<GasStation> gsList;
	List<GasStationDto> gsDtoList;
	
	
	
	boolean compareUserDto(UserDto u1, UserDto u2) {
		return true;
	}
	
	boolean compareGasStationDto(GasStationDto gs1, GasStationDto gs2) {
		return ( gs1.getGasStationId() == gs2.getGasStationId() && 
				 gs1.getGasStationName().compareTo(gs2.getGasStationName()) == 0 &&
				 gs1.getGasStationAddress().compareTo(gs2.getGasStationAddress()) == 0 &&
				 gs1.getHasDiesel() == gs2.getHasDiesel() &&
				 gs1.getHasSuper() == gs2.getHasSuper() &&
				 gs1.getHasSuperPlus() == gs2.getHasSuperPlus() &&
				 gs1.getHasGas() == gs2.getHasGas() &&
				 gs1.getHasMethane() == gs2.getHasMethane() &&
				 gs1.getCarSharing() == gs2.getCarSharing() &&
				 gs1.getLat() == gs2.getLat() &&
				 gs1.getLon() == gs2.getLon() &&
				 gs1.getDieselPrice() == gs2.getDieselPrice() &&
				 gs1.getSuperPrice() == gs2.getSuperPrice() &&
				 gs1.getSuperPlusPrice() == gs2.getSuperPlusPrice() &&
				 gs1.getGasPrice() == gs2.getGasPrice() &&
				 gs1.getMethanePrice() == gs2.getMethanePrice() &&
				 gs1.getReportUser() == gs2.getReportUser() &&
				 gs1.getReportTimestamp() == gs2.getReportTimestamp() &&
				 compareUserDto(gs1.getUserDto(), gs2.getUserDto()));
	}
	
	
	@BeforeAll
	static void theFirstAndOnlyOneTrueSetup() {
		GasStation gs1 = new GasStation("Gas station c", "Address c, 3", false, false, false, true, true, "", 31.5, -1, -1, -1, -1, 1.2, 0.96, 3, null, 0);
		List<GasStation> gsList = new ArrayList<GasStation>();
		gsList.add(gs1);
		GasStationRepository gasStationRepository = mock(GasStationRepository.class);
		UserRepository userRepository = mock(UserRepository.class);
		when(gasStationRepository.findByGasStationId(0)).thenReturn(gsList);
		
		
		gasStationService = new GasStationServiceimpl(gasStationRepository, userRepository);
	}
	
	@BeforeEach
	void setUp() throws Exception {
	
	}	
	
	@AfterEach
	void tearDown() throws Exception {

	}	
	
	
	
	@Nested
	@DisplayName("Test for getGasStationById")
	public class GetGasStationById{
		
		@BeforeEach
		void setUp() {
			// SAVE DI gs0, gs1, gs2 
		}
		@AfterEach
		void tearDown() throws Exception {
			//*** DELETE DI gs0, gs1, gs2 
		}	
		
		@Test
		public void existingId_returnCorrespondingGasStationDto()  {
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(0);
				assertTrue("Gas station retrieved is not the same that has been inserted", compareGasStationDto(gasStationDto, GasStationConverter.GasStationConvertToGasStationDto(gs1)));
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail("InvalidGasStationException unexpected");
			}
		}
		/*
		@Test
		public void nonExistingId_returnNull() {
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(999);
				assertNull("Gas station should be null", gasStationDto);
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail("InvalidGasStationException unexpected");
			}
		}*/
		/*
		@Test
		public void negativeId_InvalidGasStationExceptionThrown() {
			try {
				gasStationService.getGasStationById(-2);
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidGasStationException e) {
				
			}
		}*/
	}
	
	@Nested
	@DisplayName("Test for saveGasStation")
	public class SaveGasStation{
		
		@AfterEach
		void tearDown() throws Exception {
			//*** ELIMINARE DAL DB LE GASSTATION INSERITE ***
		}	
		
		@Test
		public void validGasStationDto_returnGasStationDto() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, false, false, true, "", 41.5, 23.7, 1.26, 1.67, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				GasStationDto result = gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				assertTrue("Gas station retrieved is not the same that has been inserted", compareGasStationDto(result, GasStationConverter.GasStationConvertToGasStationDto(gs2_1)));
				
			} catch (PriceException | GPSDataException e) {
				fail("Exception unexpected");
			}
		}
		
		@Test
		public void negativeDiesel_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, false, false, true, "", 41.5, 23.7, -7, 1.67, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, dieselPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void negativeSuper_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, false, false, true, "", 41.5, 23.7, 2, -1.67, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void negativeSuperPlusPrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", 41.5, 23.7, 1.2, 1.67, -5, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPlusPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void negativeGasPrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", 41.5, 23.7, 1.2, 1.67, 5, -21, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPlusPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void negativeMethanePrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", 41.5, 23.7, 1.2, 1.67, 2, -1, -0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, methanePrice is negative");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void invalidLatitude_GPSDataExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", 180 , 23.7, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			GasStation gs2_2 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", -180.1, 23.7, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("GPSDataException expected, latitude >= 180");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
			
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_2));
				fail("GPSDataException expected, latitude < -180");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
		}
		
		@Test
		public void invalidLongitude_GPSDataExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", 12 , 180, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			GasStation gs2_2 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true, "", -33.1, -180.1, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("GPSDataException expected, longitude >= 180");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
			
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_2));
				fail("GPSDataException expected, longitude < -180");
			} catch (PriceException | GPSDataException e) {
				if(! (e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
		}
	}
	
	@Nested
	@DisplayName("Test for getAllGasStations")
	public class GetAllGasStations{
		@Test
		public void _returnEmptyList() {
			
		}
		@Test
		public void _returnGasStationDtoList() {
			
		}
	}
	
	@Nested
	@DisplayName("Test for deleteGasStation")
	public class DeleteGasStation{
		
		@BeforeEach
		void setUp() {
			//*** INSERIRE g0, g1, g2 ***
		}
		
		@AfterEach
		void tearDown() {
			//*** ELIMINARE g0, g1, g2 ***
		}
		
		@Test
		public void existingId_returnTrue()  {
			try {
				boolean result = gasStationService.deleteGasStation(1);
				assertTrue("Deleting an existing gas station returned false", result);
			} catch (InvalidGasStationException e) {
				fail("InvalidGasStationException unexpected");
			}
			
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(1);
				assertNull("Gas station deleted has been retrieved, should be null", gasStationDto);
			} catch (InvalidGasStationException e) {
				fail("InvalidGasStationException unexpected when trying to retrieve gas station deleted (should return null)");
			}
		}
		
		@Test
		public void nonExistingId_returnFalse() {
			try {
				boolean result = gasStationService.deleteGasStation(50);
				assertFalse("Deleting a non existing gas station returned true", result);
			} catch (InvalidGasStationException e) {
				fail("InvalidGasStationException unexpected");
			}
		}
		
		@Test
		public void negativeId_InvalidGasStationExceptionThrown() {
			try {
				boolean result = gasStationService.deleteGasStation(-10);
				fail("Negative Id should throw an InvalidGasStationException");
			} catch (InvalidGasStationException e) {
				
			}
		}
	}
	
	@Nested
	@DisplayName("Test for getGasStationsByGasolineType")
	public class GetGasStationsByGasolineType{
		@Test
		public void diesel_returnListGasStationDtoSortedByDistance() {
			
		}
		@Test
		public void gasoline_returnListGasStationDtoSortedByDistance() {
			
		}
		@Test
		public void premiumGasoline_returnListGasStationDtoSortedByDistance() {
			
		}
		@Test
		public void LPG_returnListGasStationDtoSortedByDistance() {
			
		}
		@Test
		public void methane_returnListGasStationDtoSortedByDistance() {
			
		}
		@Test
		public void gasoline_returnEmptyList() {
			
		}
		@Test
		public void emptyString_InvalidGasTypeExceptionThrown() {
			
		}
		@Test
		public void null_InvalidGasTypeExceptionThrown() {
			
		}
		@Test
		public void randomInvalidString_InvalidGasTypeExceptionThrown() {
			
		}
	}
	
	
	@Nested
	@DisplayName("Test for getGasStationsByProximity")
	public class GetGasStationsByProximity{
		@Test
		public void invalidLatValidLon_GPSDataExceptionThrown() {
			
		}
		@Test
		public void validLatInvalidLon_GPSDataExceptionThrown() {
			
		}
		@Test
		public void invalidLatInvalidLon_GPSDataExceptionThrown() {
			
		}
		@Test
		public void validLatValidLon_returnListGasStationDtoSortedByDistance() {
			
		}
	}
	
	@Nested
	@DisplayName("Test for getGasStationsWithCoordinates")
	public class GetGasStationsWithCoordinates{
		//***** DA COMPLETARE *****
	}
	
	@Nested
	@DisplayName("Test for getGasStationsWithoutCoordinates")
	public class GetGasStationsWithoutCoordinates{
		//***** DA COMPLETARE *****
	}
	
	@Nested
	@DisplayName("Test for setReport")
	public class SetReport{
		//***** DA COMPLETARE *****
	}
	
	@Nested
	@DisplayName("Test for getGasStationByCarSharing")
	public class GetGasStationByCarSharing{
		//***** DA COMPLETARE *****
	}
	
}