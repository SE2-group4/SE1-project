package it.polito.ezgas.service;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
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

	static GasStationRepository gasStationRepository;
	static UserRepository userRepository;
	static int N = 10;
	static int carA = 3, carB = 5;
	static int diesel = 1, sup = 2, supPlus = 3, gas = 4, methane = 5;

	List<GasStation> gasStationList;
	List<GasStationDto> gasStationDtoList;
	List<User> userList;
	List<UserDto> userDtoList;

	boolean compareUserDto(UserDto u1, UserDto u2) {
		if (u1 == null && u2 == null)
			return true;
		if (u1 == null || u2 == null)
			return false;

		return (u1.getAdmin() == u2.getAdmin() && u1.getEmail().compareTo(u2.getEmail()) == 0
				&& u1.getPassword().compareTo(u2.getPassword()) == 0 && u1.getReputation() == u2.getReputation()
				&& u1.getUserId() == u2.getUserId() && u1.getUserName().compareTo(u2.getUserName()) == 0);
	}

	boolean compareGasStationDto(GasStationDto gs1, GasStationDto gs2) {
		return (gs1.getGasStationId() == gs2.getGasStationId()
				&& gs1.getGasStationName().compareTo(gs2.getGasStationName()) == 0
				&& gs1.getGasStationAddress().compareTo(gs2.getGasStationAddress()) == 0
				&& gs1.getHasDiesel() == gs2.getHasDiesel() && gs1.getHasSuper() == gs2.getHasSuper()
				&& gs1.getHasSuperPlus() == gs2.getHasSuperPlus() && gs1.getHasGas() == gs2.getHasGas()
				&& gs1.getHasMethane() == gs2.getHasMethane() && gs1.getCarSharing() == gs2.getCarSharing()
				&& gs1.getLat() == gs2.getLat() && gs1.getLon() == gs2.getLon()
				&& gs1.getDieselPrice() == gs2.getDieselPrice() && gs1.getSuperPrice() == gs2.getSuperPrice()
				&& gs1.getSuperPlusPrice() == gs2.getSuperPlusPrice() && gs1.getGasPrice() == gs2.getGasPrice()
				&& gs1.getMethanePrice() == gs2.getMethanePrice() && gs1.getReportUser() == gs2.getReportUser()
				&& gs1.getReportTimestamp() == gs2.getReportTimestamp()
				&& compareUserDto(gs1.getUserDto(), gs2.getUserDto()));
	}

	@BeforeAll
	static void initializeTest() {
		gasStationRepository = mock(GasStationRepository.class);
		userRepository = mock(UserRepository.class);
		gasStationService = new GasStationServiceimpl(gasStationRepository, userRepository);
	}

	/***
	 * Create an array of N gasStation Lat = 0.001 * i, Lon = 0.001 * i carSharing,
	 * hasGasolinetypes and gasolinePrices change in base of i%x (x = diesel, sup,
	 * supPlus, methane, carA, carB)
	 * 
	 * @return GasStation[]
	 */
	static GasStation[] initializeGasStations() {
		GasStation[] gasStations = new GasStation[N];
		Random r = new Random();

		for (int i = 1; i < N + 1; i++) {
			gasStations[i - 1] = new GasStation("gs" + i, "address " + i, i % diesel == 0, i % sup == 0,
					i % supPlus == 0, i % gas == 0, i % methane == 0, "", i * 1e-3, i * 1e-3,
					i % 1 == 0 ? r.nextDouble() + 1 : -1, i % 2 == 0 ? r.nextDouble() + 1 : -1,
					i % 3 == 0 ? r.nextDouble() + 1 : -1, i % 4 == 0 ? r.nextDouble() + 1 : -1,
					i % 5 == 0 ? r.nextDouble() + 1 : -1, 0, null, 0);
			if (i % carA == 0)
				gasStations[i - 1].setCarSharing("CarA");
			if (i % carB == 0)
				gasStations[i - 1].setCarSharing("CarB");
			gasStations[i - 1].setGasStationId(i);
		}

		return gasStations;
	}

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Nested
	@DisplayName("Test for getGasStationById")
	public class GetGasStationById {
		GasStation gs1 = new GasStation();
		GasStation gs2 = new GasStation();
		User u = new User();

		@BeforeEach
		void setUp() {
			gs1 = new GasStation("Gas station c", "Address c, 3", false, false, false, true, true, "", 31.5, -1, -1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs1.setGasStationId(5);
			gs2 = new GasStation("Gas station d", "Address d, 3", false, false, false, true, true, "", 31.5, -1, -1, -1,
					-1, 1.2, 0.96, 3, new Date().toString(), 0);
			gs2.setGasStationId(6);
			u = new User("user", "password", "user@mail.com", 1);
			u.setUserId(0);
			gs2.setUser(u);

			Optional<GasStation> gs1Opt = Optional.ofNullable(gs1);
			Optional<GasStation> gs2Opt = Optional.ofNullable(gs2);
			Optional<GasStation> gsEmpty = Optional.empty();

			initializeTest(); // re-create all mocks
			when(gasStationRepository.findByGasStationId(5)).thenReturn(gs1Opt);
			when(gasStationRepository.findByGasStationId(6)).thenReturn(gs2Opt);
			when(gasStationRepository.findByGasStationId(999)).thenReturn(gsEmpty);
			when(gasStationRepository.findByGasStationId(-1)).thenReturn(gsEmpty);
		}

		@Test
		public void existingId_returnCorrespondingGasStationDto() {
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(5);
				assertTrue(
						compareGasStationDto(gasStationDto, GasStationConverter.GasStationConvertToGasStationDto(gs1)),
						"Gas station retrieved is not the same that has been inserted");
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail("InvalidGasStationException unexpected");
			}
		}

		@Test
		public void nonExistingId_returnNull() {
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(999);
				assertNull(gasStationDto, "Gas station should be null");
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail("InvalidGasStationException unexpected");
			}
		}

		@Test
		public void negativeId_InvalidGasStationExceptionThrown() {
			try {
				gasStationService.getGasStationById(-1);
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidGasStationException e) {

			}
		}

		@Test
		public void existingId_returnCorrespondingGasStationDtoWithUserAndTimestampInserted() {
			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(6);
				assertTrue(
						compareGasStationDto(gasStationDto, GasStationConverter.GasStationConvertToGasStationDto(gs2)),
						"Gas station retrieved is not the same that has been inserted");
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail("InvalidGasStationException unexpected");
			}
		}
	}

	@Nested
	@DisplayName("Test for saveGasStation")
	public class SaveGasStation {

		@AfterEach
		void tearDown() throws Exception {
			// *** ELIMINARE DAL DB LE GASSTATION INSERITE ***
		}

		@Test
		public void validGasStationDto_returnGasStationDto() {
			GasStation gs2_1 = new GasStation("Gas station c", "Address 3", false, false, false, false, false, "", 31.5,
					35, -1, -1, -1, -1, -1, -1, null, 0.0);
			GasStationDto result = new GasStationDto();

			initializeTest(); // re-create all mocks
			when(gasStationRepository.save(Mockito.any(GasStation.class))).thenReturn(gs2_1);
			try {
				GasStationDto gDto = GasStationConverter.GasStationConvertToGasStationDto((gs2_1));
				result = gasStationService.saveGasStation(gDto);
				assertTrue(compareGasStationDto(result, GasStationConverter.GasStationConvertToGasStationDto((gs2_1))),
						"Gas station retrieved is not the same that has been inserted");

			} catch (PriceException | GPSDataException e) {
				fail("Exception unexpected");
			}
		}

		@Test
		public void negativeDiesel_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, false, false, true, "",
					41.5, 23.7, -7, 1.67, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, dieselPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void negativeSuper_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, false, false, true, "",
					41.5, 23.7, 2, -1.67, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void negativeSuperPlusPrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"", 41.5, 23.7, 1.2, 1.67, -5, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPlusPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void negativeGasPrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"", 41.5, 23.7, 1.2, 1.67, 5, -21, 0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, superPlusPrice is negative");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void negativeMethanePrice_PriceExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"", 41.5, 23.7, 1.2, 1.67, 2, -1, -0.99, 1, "07-05-2020 18:47:52", 0);
			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("PriceException expected, methanePrice is negative");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof PriceException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void invalidLatitude_GPSDataExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"", 180.1, 23.7, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			GasStation gs2_2 = new GasStation("Gas station 2_2", "Address 2_1, 2_1", true, false, false, false, true,
					"", -180.1, 23.7, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);

			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("GPSDataException expected, latitude >= 180");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}

			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_2));
				fail("GPSDataException expected, latitude < -180");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
		}

		@Test
		public void invalidLongitude_GPSDataExceptionThrown() {
			GasStation gs2_1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"", 12, 180.1, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);
			GasStation gs2_2 = new GasStation("Gas station 2_2", "Address 2_1, 2_1", true, false, false, false, true,
					"", 33.1, -180.1, 1.2, -1, -1, -1, 0.99, 1, "07-05-2020 18:47:52", 0);

			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_1));
				fail("GPSDataException expected, longitude >= 180");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}

			try {
				gasStationService.saveGasStation(GasStationConverter.GasStationConvertToGasStationDto(gs2_2));
				fail("GPSDataException expected, longitude < -180");
			} catch (PriceException | GPSDataException e) {
				if (!(e instanceof GPSDataException)) {
					fail("Wrong exception type");
				}
			}
		}
	}

	@Nested
	@DisplayName("Test for getAllGasStations")
	public class GetAllGasStations {
		GasStation gs1 = new GasStation();
		GasStation gs2 = new GasStation();
		List<GasStation> gsListEmpty = new ArrayList<>();

		@BeforeEach
		void setUp2() {
			gs1 = new GasStation("Gas station c", "Address c, 3", false, false, false, true, true, "", 31.5, -1, -1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs1.setGasStationId(5);
			gs2 = new GasStation("Gas station d", "Address d, 4", false, false, false, true, true, "", 31.5, -1, -1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs2.setGasStationId(1);

			List<GasStation> gsList = new ArrayList<>();

			gsList.add(gs1);
			gsList.add(gs2);

			initializeTest(); // re-create all mocks
			when(gasStationRepository.findAll()).thenReturn(gsList);
		}

		@Test
		public void _returnEmptyList() {
			when(gasStationRepository.findAll()).thenReturn(gsListEmpty);
			assertTrue(gsListEmpty.isEmpty(), "List of gas station retrieved is not empty");
		}

		@Test
		public void _returnGasStationDtoList() {
			List<GasStationDto> gasStationDto = gasStationService.getAllGasStations();
			assertTrue(
					compareGasStationDto(gasStationDto.get(0),
							GasStationConverter.GasStationConvertToGasStationDto(gs1)),
					"Gas station retrieved is not the same that has been inserted");
			assertTrue(
					compareGasStationDto(gasStationDto.get(1),
							GasStationConverter.GasStationConvertToGasStationDto(gs2)),
					"Gas station retrieved is not the same that has been inserted");
		}
	}

	@Nested
	@DisplayName("Test for deleteGasStation")
	public class DeleteGasStation {
		GasStation g1;

		@BeforeEach
		void setUp() {
			this.g1 = new GasStation("Gas station c", "Address c, 3", false, false, false, true, true, "", 31.5, -1, -1,
					-1, -1, 1.2, 0.96, 3, null, 0);
			this.g1.setGasStationId(1);

			initializeTest(); // re-create all mocks
			when(gasStationRepository.findByGasStationId(anyInt())).thenAnswer(new Answer<Optional<GasStation>>() {
				public Optional<GasStation> answer(InvocationOnMock invocation) throws IllegalArgumentException {
					Object[] args = invocation.getArguments();
					Integer param = (Integer) args[0];

					if (param == g1.getGasStationId())
						return Optional.empty(); // as it has been removed
					else if (param >= 0)
						return Optional.empty();
					else
						throw new IllegalArgumentException("Invalid GasStation id");
				}
			});
		}

		@Test
		public void existingId_returnTrue() {
			try {
				Boolean result = gasStationService.deleteGasStation(this.g1.getGasStationId());
				assertNotNull(result);
				assertTrue(result, "Deleting an existing gas station returned false");
			} catch (InvalidGasStationException e) {
				fail("InvalidGasStationException unexpected");
			}

			try {
				GasStationDto gasStationDto = gasStationService.getGasStationById(this.g1.getGasStationId());
				assertNull(gasStationDto, "Gas station deleted has been retrieved, should be null");
			} catch (InvalidGasStationException e) {
				fail("InvalidGasStationException unexpected when trying to retrieve gas station deleted (should return null)");
			}
		}

		@Test
		public void nonExistingId_returnFalse() {
			try {
				Boolean result = gasStationService.deleteGasStation(999);
				assertNotNull(result);
				// assertFalse(result, "Deleting a non existing gas station returned true");
				assertTrue(result, "Deleting a non existing gas station returned false"); // using white box approach
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
	public class GetGasStationsByGasolineType {
		GasStation[] gasStations;
		List<GasStation> allList;
		List<GasStation> gasStationFilteredByGasolineType;
		List<GasStationDto> expectedList;

		@BeforeEach
		void setUp() {
			gasStations = initializeGasStations();
			allList = Arrays.asList(gasStations);
		}

		List<GasStation> initGasStationByGasoline(int gasolinetype) {
			List<GasStation> gasStationList = new ArrayList<GasStation>();
			for (int i = 1; i < N + 1; i++) {
				if (i % gasolinetype == 0) {
					gasStationList.add(gasStations[i - 1]);
				}
			}
			gasStationList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			return gasStationList;
		}

		@Test
		public void diesel_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			gasStationFilteredByGasolineType = initGasStationByGasoline(diesel);
			expectedList = gasStationFilteredByGasolineType.stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			when(gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("Diesel");

			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}

		}

		@Test
		public void super_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			gasStationFilteredByGasolineType = initGasStationByGasoline(sup);
			expectedList = gasStationFilteredByGasolineType.stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			when(gasStationRepository.findByHasSuperTrueOrderBySuperPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("Super");

			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void superPlus_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			gasStationFilteredByGasolineType = initGasStationByGasoline(supPlus);
			expectedList = gasStationFilteredByGasolineType.stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			when(gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("SuperPlus");

			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void gas_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			gasStationFilteredByGasolineType = initGasStationByGasoline(gas);
			expectedList = gasStationFilteredByGasolineType.stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			when(gasStationRepository.findByHasGasTrueOrderByGasPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("Gas");

			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void methane_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			gasStationFilteredByGasolineType = initGasStationByGasoline(methane);
			expectedList = gasStationFilteredByGasolineType.stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			when(gasStationRepository.findByHasMethaneTrueOrderByMethanePriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("Methane");
			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void diesel_returnEmptyList() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			when(gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsByGasolineType("Diesel");
			} catch (InvalidGasTypeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Return list should be empty");
		}

		@Test
		public void emptyString_InvalidGasTypeExceptionThrown() {
			List<GasStationDto> returnList = null;
			try {
				returnList = gasStationService.getGasStationsByGasolineType("");
				fail("InvalidGasTypeException expected");
			} catch (InvalidGasTypeException e) {
			}
		}

		@Test
		public void null_InvalidGasTypeExceptionThrown() {
			List<GasStationDto> returnList = null;
			try {
				returnList = gasStationService.getGasStationsByGasolineType(null);
				fail("InvalidGasTypeException expected, null argument");
			} catch (InvalidGasTypeException e) {
			}
			
			try {
				returnList = gasStationService.getGasStationsByGasolineType("null");
				fail("InvalidGasTypeException expected, \"null\" argument");
			} catch (InvalidGasTypeException e) {
			}
		}

		@Test
		public void invalidString_InvalidGasTypeExceptionThrown() {
			List<GasStationDto> returnList = null;
			try {
				returnList = gasStationService.getGasStationsByGasolineType("aaa");
				fail("InvalidGasTypeException expected, non existing gasoline type argument");
			} catch (InvalidGasTypeException e) {
			}
		}
	}

	@Nested
	@DisplayName("Test for getGasStationsByProximity")
	public class GetGasStationsByProximity {

		@Test
		public void invalidLatValidLon_GPSDataExceptionThrown() {
			try {
				gasStationService.getGasStationsByProximity(-91, 50);
				fail("GPSDataException should have been thrown (lat = -91)");
			} catch (GPSDataException e) {
			}
			try {
				gasStationService.getGasStationsByProximity(91, 50);
				fail("GPSDataException should have been thrown (lat = 91)");
			} catch (GPSDataException e) {
			}
		}

		@Test
		public void validLatInvalidLon_GPSDataExceptionThrown() {
			try {
				gasStationService.getGasStationsByProximity(0, -181);
				fail("GPSDataException should have been thrown (lon = -181)");
			} catch (GPSDataException e) {
			}
			try {
				gasStationService.getGasStationsByProximity(0, 181);
				fail("GPSDataException should have been thrown (lat = 181)");
			} catch (GPSDataException e) {
			}
		}

		@Test
		public void invalidLatInvalidLon_GPSDataExceptionThrown() {
			try {
				gasStationService.getGasStationsByProximity(-91, -181);
				fail("GPSDataException should have been thrown (lat = -91, lon = -181)");
			} catch (GPSDataException e) {
			}
			try {
				gasStationService.getGasStationsByProximity(91, 181);
				fail("GPSDataException should have been thrown (lat = 91, lon = 181)");
			} catch (GPSDataException e) {
			}
			try {
				gasStationService.getGasStationsByProximity(-91, 181);
				fail("GPSDataException should have been thrown (lat = 91, lon = -181)");
			} catch (GPSDataException e) {
			}
			try {
				gasStationService.getGasStationsByProximity(91, -181);
				fail("GPSDataException should have been thrown (lat = 91, lon = -181)");
			} catch (GPSDataException e) {
			}
		}

		@Test
		public void validLatValidLon_returnListGasStationDtoSortedByDistance() {
			// ***** SET UP *****
			GasStation gs1, gs2, gs3, gs4, gs5, gs6;
			List<GasStation> allList;
			List<GasStationDto> expectedList;
			gs1 = new GasStation("Gas station a", "Address a", false, false, false, true, true, "", 0.001, 0.001, 1, -1,
					-1, 1.2, 0.96, 3, new Date().toString(), 0);
			gs1.setGasStationId(1);
			gs1.setUser(new User("AAA", "AAA", "aaa@ezgas.com", 2));
			gs2 = new GasStation("Gas station b", "Address b", false, false, false, true, true, "", 0.002, 0.002, 1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs2.setGasStationId(2);
			gs3 = new GasStation("Gas station c", "Address c", false, false, false, true, true, "", 0.003, 0.003, 1, -1,
					-1, 1.2, 0.96, 3, new Date().toString(), 0);
			gs3.setGasStationId(3);
			gs3.setUser(new User("CCC", "CCC", "ccc@ezgas.com", -1));
			gs4 = new GasStation("Gas station d", "Address d", false, false, false, true, true, "", 0.004, 0.004, 1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs4.setGasStationId(4);
			gs5 = new GasStation("Gas station e", "Address e", false, false, false, true, true, "", 0.01, 0.01, 1, -1,
					-1, 1.2, 0.96, 3, null, 0);
			gs5.setGasStationId(5);
			gs6 = new GasStation("Gas station f", "Address f", false, false, false, true, true, "", 1, 0, 1, -1, -1,
					1.2, 0.96, 3, null, 0);
			gs6.setGasStationId(6);

			allList = Arrays.asList(gs4, gs3, gs2, gs1, gs5, gs6);
			expectedList = Arrays.asList(gs1, gs2, gs3, gs4).stream()
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			when(gasStationRepository.findAll()).thenReturn(allList);

			// ***** TEST *****
			List<GasStationDto> returnList = new ArrayList<GasStationDto>();
			try {
				returnList = gasStationService.getGasStationsByProximity(0, 0);
			} catch (GPSDataException e) {
				e.printStackTrace();
				fail("No exception should be thrown");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void validLatValidLon_returnListEmptyList() {
			// ***** SET UP *****
			when(gasStationRepository.findAll()).thenReturn(new ArrayList<GasStation>());

			// ***** TEST *****
			List<GasStationDto> returnList = new ArrayList<GasStationDto>();
			try {
				returnList = gasStationService.getGasStationsByProximity(0, 0);
			} catch (GPSDataException e) {
				e.printStackTrace();
				fail("No exception should be thrown");
			}

			assertTrue(returnList.size() == 0, "Wrong number of gasStation returned");
		}

	}

	@Nested
	@DisplayName("Test for getGasStationsWithoutCoordinates")
	public class GetGasStationsWithoutCoordinates {
		GasStation[] gasStations;
		List<GasStation> allList;
		List<GasStation> gasStationFilteredByGasolineType;
		List<GasStationDto> expectedList;

		@BeforeEach
		void setUp() {
			gasStations = initializeGasStations();
			allList = Arrays.asList(gasStations);
		}

		@Test
		public void dieselCarcompanyA_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % diesel == 0) {
					gasStationFilteredByGasolineType.add(gasStations[i - 1]);
					if (i % carA == 0)
						expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
				}
			}

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			when(gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsWithoutCoordinates("Diesel", "CarA");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void superCarcompanyB_returnListGasStationDtoSortedByPrice() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % sup == 0) {
					gasStationFilteredByGasolineType.add(gasStations[i - 1]);
					if (i % carB == 0)
						expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
				}
			}

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			initializeTest(); // re-create all mocks
			when(gasStationRepository.findByHasSuperTrueOrderBySuperPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsWithoutCoordinates("Super", "CarB");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(), "Wrong number of gasStation returned");
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void dieselNonExistingCarCompany_returnEmptyList() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();

			for (int i = 1; i < N + 1; i++) {
				if (i % diesel == 0)
					gasStationFilteredByGasolineType.add(gasStations[i - 1]);
			}

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));

			initializeTest(); // re-create all mocks
			when(gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsWithoutCoordinates("Super", "CarC");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("No exception expected");
			}
			assertTrue(returnList.isEmpty(), "Wrong number of gasStation returned");
		}
	}

	@Nested
	@DisplayName("Test for getGasStationsWithCoordinates")
	public class GetGasStationsWithCoordinates {
		GasStation[] gasStations;
		List<GasStation> allList;
		List<GasStation> gasStationFilteredByGasolineType;
		List<GasStationDto> expectedList;

		@BeforeEach
		void setUp() {
			gasStations = initializeGasStations();
			allList = Arrays.asList(gasStations);
		}

		@Test
		public void wrongLat_throwGPSDataException() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(-91, 160, "Diesel", "CarA");
				fail("GPSDataException expected");
			} catch (GPSDataException e) {
			} catch (Exception e) {
				e.printStackTrace();
				fail("Wrong exception, GPSDataException expected");
			}
		}

		@Test
		public void emptyGasolinetype_throwGPSDataException() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(-23, 160, "", "CarA");
				fail("GPSDataException expected");
			} catch (InvalidGasTypeException e) {
			} catch (Exception e) {
				e.printStackTrace();
				fail("Wrong exception, GPSDataException expected");
			}
		}

		@Test
		public void validLatValidLonDieselCarA_returnGasStationListSortedByDistance() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % diesel == 0) {
					gasStationFilteredByGasolineType.add(gasStations[i - 1]);
					if (i % carA == 0)
						expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
				}
			}

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList = expectedList.stream().filter(gs -> gs.getGasStationId() <= 6).collect(Collectors.toList());
			;

			when(gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(0, 0, "Diesel", "CarA");
			} catch (Exception e) {
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(),
					"Wrong number of gasStation returned:" + returnList.size() + " instead of " + expectedList.size());
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void validLatValidLonNullCarA_returnGasStationListSortedByDistance() {
			List<GasStationDto> returnList = null;
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % carA == 0)
					expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
			}

			expectedList = expectedList.stream().filter(gs -> gs.getGasStationId() <= 6).collect(Collectors.toList());

			when(gasStationRepository.findAll()).thenReturn(Arrays.asList(gasStations));

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(0, 0, "null", "CarA");
			} catch (Exception e) {
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(),
					"Wrong number of gasStation returned:" + returnList.size() + " instead of " + expectedList.size());
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void validLatValidLonNullNull_returnGasStationListSortedByDistance() {
			List<GasStationDto> returnList = null;
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % carA == 0)
					expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
			}

			expectedList = Arrays.asList(gasStations).stream().filter(gs -> gs.getGasStationId() <= 6)
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());

			when(gasStationRepository.findAll()).thenReturn(Arrays.asList(gasStations));

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(0, 0, "Select gasoline type", "null");
			} catch (Exception e) {
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(),
					"Wrong number of gasStation returned:" + returnList.size() + " instead of " + expectedList.size());
			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

		@Test
		public void validLatValidLonMethaneNull_returnGasStationListSortedByDistance() {
			List<GasStationDto> returnList = null;
			gasStationFilteredByGasolineType = new ArrayList<GasStation>();
			expectedList = new ArrayList<GasStationDto>();

			for (int i = 1; i < N + 1; i++) {
				if (i % diesel == 0) {
					gasStationFilteredByGasolineType.add(gasStations[i - 1]);
					expectedList.add(GasStationConverter.GasStationConvertToGasStationDto(gasStations[i - 1]));
				}
			}

			gasStationFilteredByGasolineType.sort((a, b) -> Double.compare(a.getDieselPrice(), b.getDieselPrice()));
			expectedList = expectedList.stream().filter(gs -> gs.getGasStationId() <= 6).collect(Collectors.toList());

			when(gasStationRepository.findByHasMethaneTrueOrderByMethanePriceDesc())
					.thenReturn(gasStationFilteredByGasolineType);

			try {
				returnList = gasStationService.getGasStationsWithCoordinates(0, 0, "Methane", "null");
			} catch (Exception e) {
				e.printStackTrace();
				fail("No exception expected");
			}

			assertTrue(expectedList.size() == returnList.size(),
					"Wrong number of gasStation returned:" + returnList.size() + " instead of " + expectedList.size());

			for (int i = 0; i < expectedList.size(); i++) {
				if (!compareGasStationDto(expectedList.get(i), returnList.get(i)))
					fail("Returned wrong list");
			}
		}

	}

	@Nested
	@DisplayName("Test for setReport")
	public class SetReport {
		GasStation g1;
		User u1;
		List<GasStation> gList;
		List<User> uList;
		
		@BeforeEach
		public void setUp() {

			this.u1 = new User("Giacomo", "giacomo", "giacomo.poretti@agg.it", 4);
			this.u1.setUserId(4);
			
			this.g1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, true, true, true, true,
					"Enjoy", 41.5, 23.7, 1.2, 1.67, 2, 2, -0.99, 1, "07-05-2020 18:47:52", 0);
			this.g1.setGasStationId(1);
			this.g1.setReportUser(this.u1.getUserId());
			this.g1.setUser(this.u1);
			
			this.uList = new ArrayList<User>();
			this.uList.add(this.u1);
			
			initializeTest(); // re-create mocks
			when(gasStationRepository.save(any(GasStation.class))).thenAnswer(new Answer<GasStation>() {
				public GasStation answer(InvocationOnMock invocation) {
				     Object[] args = invocation.getArguments();
					return (GasStation) (args[0]);
				}
			});
			when(gasStationRepository.findByGasStationId(anyInt())).thenAnswer(new Answer<Optional<GasStation>>() {
				@Override
				public Optional<GasStation> answer(InvocationOnMock invocation) throws IllegalArgumentException {
					Integer param = (Integer) invocation.getArguments()[0];
					
					//  if(param == g1.getGasStationId()) // it doesn't work... I hate Integer - int problems...
					if(param.equals(g1.getGasStationId()))
						return Optional.of(g1);
					if(param < 0)
						throw new IllegalArgumentException();
					else
						return Optional.empty();
				}				
			});
			when(userRepository.findByUserId(anyInt())).thenAnswer(new Answer<List<User>>() {
				@Override
				public List<User> answer(InvocationOnMock invocation) throws IllegalArgumentException {
					Integer param = (Integer) invocation.getArguments()[0];
					
					if(param == u1.getUserId())
						return uList;
					if(param < 0)
						throw new IllegalArgumentException();
					else
						return new ArrayList<User>();
				}				
			});
		}
		
		@Test
		public void invalidUserId_ShouldThrowException() {
			try {
				gasStationService.setReport(this.g1.getGasStationId(), 1, 1, 1, 1, 1, new Integer(-84));
				fail();
			} catch (InvalidGasStationException e) {
				fail();
			} catch (PriceException e) {
				fail();
			} catch (InvalidUserException e) {
				// good!
			}		
		}
		
		@Test
		public void invalidGasStationId_ShouldThrowException() {
			try {
				gasStationService.setReport(new Integer(-84), 1, 1, 1, 1, 1, this.u1.getUserId());
				fail();
			} catch (InvalidGasStationException e) {
				// good!
			} catch (PriceException e) {
				fail();
			} catch (InvalidUserException e) {
				fail();
			}
		}
		
		@Test
		public void notExistingUser_ShouldThrowException() {
			try {
				gasStationService.setReport(this.g1.getGasStationId(), 1, 1, 1, 1, 1, new Integer(999));
				fail();
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail();
			} catch (PriceException e) {
				e.printStackTrace();
				fail();
			} catch (InvalidUserException e) {
				// good!
			}
		}
		
		@Test
		public void notExistingGasStation_ShouldThrowException() {	
			try {
				gasStationService.setReport(new Integer(999), 1, 1, 1, 1, 1, this.u1.getUserId());
				fail();
			} catch (InvalidGasStationException e) {
				// good!
			} catch (PriceException e) {
				fail();
			} catch (InvalidUserException e) {
				fail();
			}
			
		}
		
		@Test
		public void invalidGasTypePrice_ShouldThrowException() {
			try {
				gasStationService.setReport(this.g1.getGasStationId(), 1, 1, -44.0, 1, 1, this.u1.getUserId());
				fail();
			} catch (InvalidGasStationException e) {
				e.printStackTrace();
				fail();
			} catch (PriceException e) {
				// good!
			} catch (InvalidUserException e) {
				e.printStackTrace();
				fail();
			}
		}
		
		@Test
		public void correctParams_ShouldSetNewReport() {
			try {
				gasStationService.setReport(this.g1.getGasStationId(), 1, 1, 1, 1, 1, this.u1.getUserId());
			} catch (InvalidGasStationException e) {
				fail();
			} catch (PriceException e) {
				fail();
			} catch (InvalidUserException e) {
				fail();
			}		
		}		
	}

	@Nested
	@DisplayName("Test for getGasStationByCarSharing")
	public class GetGasStationByCarSharing {

		private List<GasStation> gList1;
		private List<User> uList;

		@BeforeEach
		public void setUp() {
			this.gList1 = new ArrayList<GasStation>();
			this.uList = new ArrayList<User>();

			initializeTest(); // re-create all mocks

			User u1 = new User("Giacomo", "giacomo", "giacomo.poretti@agg.it", 4);
			u1.setUserId(4);
			u1.setAdmin(true);
			this.uList.add(u1);
			when(userRepository.findByUserId(4)).thenReturn(uList);

			GasStation g1 = new GasStation("Gas station 2_1", "Address 2_1, 2_1", true, false, false, false, true,
					"Enjoy", 41.5, 23.7, 1.2, 1.67, 2, -1, -0.99, 1, "07-05-2020 18:47:52", 0);
			g1.setGasStationId(1);
			g1.setReportUser(u1.getUserId());
			g1.setUser(u1);
			this.gList1.add(g1);

			GasStation g2 = new GasStation("Gas station 2_2", "Address 2_2, 2_2", true, false, false, false, true,
					"Enjoy", 41.5, 23.7, 1.2, 1.67, 2, -1, -0.99, 1, "07-05-2020 18:47:52", 0);
			g2.setGasStationId(2);
			g2.setReportUser(u1.getUserId());
			g2.setUser(u1);
			this.gList1.add(g2);

			when(gasStationRepository.findAll()).thenReturn(gList1);
			when(userRepository.findByUserId(any())).thenReturn(uList);
			when(userRepository.findAll()).thenReturn(uList);
		}

		@Test
		public void validCarSharing_ShouldReturnGasStationList() {
			List<GasStationDto> res = gasStationService.getGasStationByCarSharing("Enjoy");
			assertNotNull(res);
			assertEquals(2, res.size());
			res = res.stream().sorted((GasStationDto a, GasStationDto b) -> (a.getGasStationId() - b.getGasStationId()))
					.collect(Collectors.toList());

			assertTrue(compareGasStationDto(GasStationConverter.GasStationConvertToGasStationDto(this.gList1.get(0)),
					res.get(0)));
			assertTrue(compareGasStationDto(GasStationConverter.GasStationConvertToGasStationDto(this.gList1.get(1)),
					res.get(1)));
		}

		@Test
		public void wrongCarSharing_ShouldReturnEmptyGasStationList() {
			List<GasStationDto> res = gasStationService.getGasStationByCarSharing("Not a car sharing service");
			assertNotNull(res);
			assertEquals(0, res.size());
		}
	}

}