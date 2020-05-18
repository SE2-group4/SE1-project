package it.polito.ezgas.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GasStationServiceimplTest {
	
	static GasStationServiceimpl gasStationService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		gasStationService = new GasStationServiceimpl();
	}

	@Nested
	@DisplayName("Test for checkCoordinates")
	public class CheckCoordinates{
		
		@Test
		public void latUnderMinus90_returnFalse() {
			assertFalse(gasStationService.checkCoordinates(-90.1, -180.1));
			assertFalse(gasStationService.checkCoordinates(-90.01, 55));
			assertFalse(gasStationService.checkCoordinates(-90.001, 180.1));
		}
		
		@Test
		public void latOver90_returnFalse() {
			assertFalse(gasStationService.checkCoordinates(90.1, -180.01));
			assertFalse(gasStationService.checkCoordinates(90.01, 0));
			assertFalse(gasStationService.checkCoordinates(90.001, 180.01));
		}
		
		@Test
		public void latValidLonUnderMinus180_returnFalse() {
			assertFalse(gasStationService.checkCoordinates(0, -180.001));
		}
		
		@Test
		public void latValidLonOver180_returnFalse() {
			assertFalse(gasStationService.checkCoordinates(0, 180.001));
		}
		
		@Test
		public void latValidLonValid_returnTrue() {
			assertTrue(gasStationService.checkCoordinates(50.2, 41));
			assertTrue(gasStationService.checkCoordinates(-90, 0));
			assertTrue(gasStationService.checkCoordinates(90, 0));
			assertTrue(gasStationService.checkCoordinates(0, -180));
			assertTrue(gasStationService.checkCoordinates(0, 180));
		}
		
		
	}

}
