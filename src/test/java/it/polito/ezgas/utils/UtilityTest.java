package it.polito.ezgas.utils;

//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UtilityTest {

	@Nested
	@DisplayName("Test for checkCoordinates")
	public class CheckCoordinates{
		
		@Test
		public void latUnderMinus90_returnFalse() {
			assertFalse(Utility.checkCoordinates(-90.1, -180.1));
			assertFalse(Utility.checkCoordinates(-90.01, 55));
			assertFalse(Utility.checkCoordinates(-90.001, 180.1));
		}
		
		@Test
		public void latOver90_returnFalse() {
			assertFalse(Utility.checkCoordinates(90.1, -180.01));
			assertFalse(Utility.checkCoordinates(90.01, 0));
			assertFalse(Utility.checkCoordinates(90.001, 180.01));
		}
		
		@Test
		public void latValidLonUnderMinus180_returnFalse() {
			assertFalse(Utility.checkCoordinates(0, -180.001));
		}
		
		@Test
		public void latValidLonOver180_returnFalse() {
			assertFalse(Utility.checkCoordinates(0, 180.001));
		}
		
		@Test
		public void latValidLonValid_returnTrue() {
			assertTrue(Utility.checkCoordinates(50.2, 41));
			assertTrue(Utility.checkCoordinates(-90, 0));
			assertTrue(Utility.checkCoordinates(90, 0));
			assertTrue(Utility.checkCoordinates(0, -180));
			assertTrue(Utility.checkCoordinates(0, 180));
		}
	}
	
	@Nested
	@DisplayName("Test for calculateDistanceInMeters")
	public class CalculateDistanceInMeters{
		@Test
		public void lat1Invalid_returnMinus1() {
			assertEquals(-1, Utility.calculateDistanceInMeters(-90.001, -50, 0, 5));
			assertEquals(-1, Utility.calculateDistanceInMeters(-10000, 55, 27, 100));
			assertEquals(-1, Utility.calculateDistanceInMeters(90.1, 179, 53, 11));
			assertEquals(-1, Utility.calculateDistanceInMeters(10000, 31, 22, 11));
		}

		@Test
		public void lon1Invalid_returnMinus1() {
			assertEquals(-1, Utility.calculateDistanceInMeters(2.1, -180.001, 0, 5));
			assertEquals(-1, Utility.calculateDistanceInMeters(32, -10000, 27, 100));
			assertEquals(-1, Utility.calculateDistanceInMeters(-12, 180.001, 53, 11));
			assertEquals(-1, Utility.calculateDistanceInMeters(10, 10000, 22, 11));
		}
		
		@Test
		public void lat2Invalid_returnMinus1() {
			assertEquals(-1, Utility.calculateDistanceInMeters(22, -50, -90.001, 5));
			assertEquals(-1, Utility.calculateDistanceInMeters(10, 55, -10000, 100));
			assertEquals(-1, Utility.calculateDistanceInMeters(-23.1, 32, 90.001, 11));
			assertEquals(-1, Utility.calculateDistanceInMeters(79, 180, 10000, 11));
		}
		
		@Test
		public void lon2Invalid_returnMinus1() {
			assertEquals(-1, Utility.calculateDistanceInMeters(-3.1, -50, 0, -180.001));
			assertEquals(-1, Utility.calculateDistanceInMeters(-10, 55, 27, -10000));
			assertEquals(-1, Utility.calculateDistanceInMeters(90, 179, 53, 180.001));
			assertEquals(-1, Utility.calculateDistanceInMeters(-90, 31, 22, 10000));
		}
		
		@Test
		public void correctCoordinates_returnDistance() {
			assertEquals(84.3, Utility.calculateDistanceInMeters(40.709, 30, 40.709, 30.001), 0.1);
			assertEquals(0, Utility.calculateDistanceInMeters(0, 0, 0, 0), 0.1);
			assertEquals(314.5, Utility.calculateDistanceInMeters(0.001, -0.001, -0.001, 0.001), 0.1);
			assertEquals(9923e3, Utility.calculateDistanceInMeters(45, 80, 44, -80), 1e3);
		}
	}
	
	@Nested
	@DisplayName("Test for trustCalculation")
	public class TrustCalculation{
		
		@Test
		public void invalidUserRep_ShouldReturn0() {
			Date correctDate = new Date();
			assertEquals(0, Utility.trustCalculation(-99, correctDate.toString()));
			assertEquals(0, Utility.trustCalculation(99, correctDate.toString()));
		}
		
		@Test
		public void invalidTimestamp_ShouldReturn0() {
			assertEquals(0, Utility.trustCalculation(3, "not a string parseable as a date"));
		}
		
		@Test
		public void timestampLessThen7DaysAgo_ShouldReturnMoreThan0() {
			Date correctDate = new Date();
			correctDate.setTime(correctDate.getTime() - 1000 /* ms/s */ * 60 /* s/min */ * 60 /* min/h */ * 24 /* h/day */ * 14); // 14 days ago
			int userRep = 3;
			double exprectedTrust = 50 * ((double)userRep +5) /10 + 0;
			
			double aDayTrust = 50 * ((double)userRep +5) /10 + 50 * (1 - (/* today - 1 day ago = 1 day */ 1.0)/7);
			double halfOfADayTrust = 50 * ((double)userRep +5) /10 + 50 * (1 - (/* today - 0.5 day ago = 0.5 day */ 0.5)/7);
			double precision = Math.abs(aDayTrust - halfOfADayTrust); // precision: half of a day
			assertEquals(exprectedTrust, Utility.trustCalculation(userRep, correctDate.toString()), precision);
		}
		
		@Test
		public void timestampLessEqualThanTodayMoreEqualThen7DaysAgo_ShouldReturnMoreThan0() {
			Date correctDate = new Date();
			correctDate.setTime(correctDate.getTime() - (1000 /* ms/s */ * 60 /* s/min */ * 60 /* min/h */ * 24 /* h/day */ * 4)); // 4 days ago
			int userRep = 3;
			
			double exprectedTrust = 50 * ((double)userRep +5) /10 + 50 * (1 - (/* today - 4 days ago = 4 days */ 4.0)/7);
			
			double aDayTrust = 50 * ((double)userRep +5) /10 + 50 * (1 - (/* today - 1 day ago = 1 day */ 1.0)/7);
			double halfOfADayTrust = 50 * ((double)userRep +5) /10 + 50 * (1 - (/* today - 0.5 day ago = 0.5 day */ 0.5)/7);
			double precision = Math.abs(aDayTrust - halfOfADayTrust); // precision: half of a day
			assertEquals(exprectedTrust, Utility.trustCalculation(userRep, correctDate.toString()), precision); 
			
			// System.out.println(exprectedTrust + " " + Utility.trustCalculation(userRep, correctDate.toString()) + " "+ precision);
		}
		
		@Test
		public void  timestampMoreThenToday_ShouldReturn0() {
			Date correctDate = new Date();
			correctDate.setTime(correctDate.getTime() + (1000 /* ms/s */ * 60 /* s/min */ * 60 /* min/h */ * 24 /* h/day */ * 4)); // 4 days in the future
			int userRep = 3;
			
			assertEquals(0, Utility.trustCalculation(userRep, correctDate.toString())); 
		}
	}

}
