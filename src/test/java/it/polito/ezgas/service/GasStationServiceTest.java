package it.polito.ezgas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;


public class GasStationServiceTest {

	@Autowired
	GasStationService gasStationService;	

	@Nested
	@DisplayName("Test for getGasStationById")
	public class GetGasStationById{
		@Test
		public void existingId_returnCorrespondingGasStationDto()  {
		
		}
		
		@Test
		public void nonExistingId_returnNull() {
		
		}
		
		@Test
		public void negativeId_InvalidGasStationExceptionThrown() {
		
		}
	}
	
	@Nested
	@DisplayName("Test for saveGasStation")
	public class SaveGasStation{
		@Test
		public void validGasStationDto_returnGasStationDto() {
		
		}
		
		@Test
		public void negativeGasolinePrice_PriceExceptionThrown() {
		
		}
		
		@Test
		public void negativePremiumGasolinePrice_PriceExceptionThrown() {
		
		}
		
		@Test
		public void negativeLPGPrice_PriceExceptionThrown() {
		
		}
		
		@Test
		public void negativeMethanePrice_PriceExceptionThrown() {
		
		}
		
		@Test
		public void invalidLatitude_GPSDataExceptionThrown() {
		
		}
		
		@Test
		public void invalidLongitude_GPSDataExceptionThrown() {
		
		}
	}
	
	@Nested
	@DisplayName("Test for getAllGasStations")
	public class GetAllGasStations{
		
		public void _returnEmptyList() {
			
		}
		
		public void _returnListGasStationDtoSortedByDistance() {
			
		}
	}
	
	@Nested
	@DisplayName("Test for deleteGasStation")
	public class DeleteGasStation{
		
		@Test
		public void existingId_returnTrue()  {
		
		}
		
		@Test
		public void nonExistingId_returnFalse() {
		
		}
		
		@Test
		public void negativeId_InvalidGasStationExceptionThrown() {
		
		}
	}
	
	@Nested
	@DisplayName("Test for getGasStationsByGasolineType")
	public class GetGasStationsByGasolineType{
		
		public void diesel_returnListGasStationDtoSortedByDistance() {
			
		}
		
		public void gasoline_returnListGasStationDtoSortedByDistance() {
			
		}
		
		public void premiumGasoline_returnListGasStationDtoSortedByDistance() {
			
		}

		public void LPG_returnListGasStationDtoSortedByDistance() {
			
		}
		
		public void methane_returnListGasStationDtoSortedByDistance() {
			
		}
		
		public void gasoline_returnEmptyList() {
			
		}
		
		public void emptyString_InvalidGasTypeExceptionThrown() {
			
		}
		
		public void null_InvalidGasTypeExceptionThrown() {
			
		}
		
		public void randomInvalidString_InvalidGasTypeExceptionThrown() {
			
		}
	}
	
	
	@Nested
	@DisplayName("Test for getGasStationsByProximity")
	public class GetGasStationsByProximity{
		
		public void invalidLatValidLon_GPSDataExceptionThrown() {
			
		}
		
		public void validLatInvalidLon_GPSDataExceptionThrown() {
			
		}
		
		public void invalidLatInvalidLon_GPSDataExceptionThrown() {
			
		}
		
		public void validLatValidLon_returnListGasStationDtoSortedByDistance() {
			
		}
	}
	
	@Nested
	@DisplayName("Test for getGasStationsWithCoordinates")
	public class GetGasStationsWithCoordinates{
		/***** DA COMPLETARE *****/
	}
	
	@Nested
	@DisplayName("Test for getGasStationsWithoutCoordinates")
	public class GetGasStationsWithoutCoordinates{
		/***** DA COMPLETARE *****/
	}
	
	@Nested
	@DisplayName("Test for setReport")
	public class SetReport{
		/***** DA COMPLETARE *****/
	}
	
	@Nested
	@DisplayName("Test for getGasStationByCarSharing")
	public class GetGasStationByCarSharing{
		/***** DA COMPLETARE *****/
	}
}
