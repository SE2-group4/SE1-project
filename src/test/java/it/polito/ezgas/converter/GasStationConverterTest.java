package it.polito.ezgas.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

public class GasStationConverterTest {
	
	static GasStationDto gasStationDto;
	static GasStation gasStation;
	//static User user;
	//static UserDto userDto;

	@BeforeAll
	static void setUp() throws Exception {
		
		User user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(true);
		UserDto userDto = new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, true);
		gasStation = new GasStation();
		gasStation.setGasStationId(1);
		gasStation.setGasStationName("Esso");
		gasStation.setGasStationAddress("Via Filatoio Calcio Lombardy Italy");
		gasStation.setHasDiesel(true);
		gasStation.setHasGas(false);
		gasStation.setHasSuper(false);
		gasStation.setHasSuperPlus(false);
		gasStation.setHasMethane(false);
		gasStation.setCarSharing("Enjoy");
		gasStation.setLat(45.4955025);
		gasStation.setLon(9.8530837);
		gasStation.setDieselPrice(1.0);
		gasStation.setMethanePrice(-1.0);
		gasStation.setGasPrice(-1.0);
		gasStation.setSuperPrice(-1.0);
		gasStation.setSuperPlusPrice(-1.0);
		gasStation.setReportUser(-1);
		gasStation.setReportTimestamp("2020/05/21");
		gasStation.setReportDependability(0);
		gasStation.setUser(user);
		gasStationDto = new GasStationDto(1, "Esso", "Via Filatoio Calcio Lombardy Italy", true, false, false, false, false, false, "Enjoy", 45.4955025, 9.8530837, 1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1, "2020/05/21", 0);
		gasStationDto.setUserDto(userDto);

	}
	
	@Test
	void testGasStationConvertToGasStationDto() {
		Optional<GasStationDto> gasStationDto2 = Optional.ofNullable(GasStationConverter.GasStationConvertToGasStationDto(gasStation));
		assertNotNull(gasStation);
		assertTrue(gasStation.getGasStationId() == gasStationDto2.get().getGasStationId());
		assertTrue(gasStation.getGasStationName().equals(gasStationDto2.get().getGasStationName()));
		assertTrue(gasStation.getGasStationAddress().equals(gasStationDto2.get().getGasStationAddress()));
		assertTrue(gasStation.getHasDiesel() == gasStationDto2.get().getHasDiesel());
		assertTrue(gasStation.getHasGas() == gasStationDto2.get().getHasGas());
		assertTrue(gasStation.getHasSuper() == gasStationDto2.get().getHasSuper());
		assertTrue(gasStation.getHasSuperPlus() == gasStationDto2.get().getHasSuperPlus());
		assertTrue(gasStation.getHasMethane() == gasStationDto2.get().getHasMethane());
		assertTrue(gasStation.getHasPremiumDiesel() == gasStationDto2.get().getHasPremiumDiesel());
		assertTrue(gasStation.getCarSharing().equals(gasStationDto2.get().getCarSharing()));
		assertTrue(gasStation.getLat() == gasStationDto2.get().getLat());
		assertTrue(gasStation.getLon() == gasStationDto2.get().getLon());
		assertTrue(gasStation.getDieselPrice() == gasStationDto2.get().getDieselPrice());
		assertTrue(gasStation.getMethanePrice() == gasStationDto2.get().getMethanePrice());
		assertTrue(gasStation.getGasPrice() == gasStationDto2.get().getGasPrice());
		assertTrue(gasStation.getSuperPrice() == gasStationDto2.get().getSuperPrice());
		assertTrue(gasStation.getSuperPlusPrice() == gasStationDto2.get().getSuperPlusPrice());
		assertTrue(gasStation.getReportUser() == gasStationDto2.get().getReportUser());
		assertTrue(gasStation.getReportTimestamp().equals(gasStationDto2.get().getReportTimestamp()));
		assertTrue(gasStation.getReportDependability() == gasStationDto2.get().getReportDependability());	
		assertTrue(gasStation.getUser().getUserId().equals(UserConverter.userDtoConvertToUser(gasStationDto2.get().getUserDto()).getUserId()));
		
	}

	@Test
	void testGasStationDtoConvertToGasStation() {
		GasStation gasStation2 = GasStationConverter.GasStationDtoConvertToGasStation(gasStationDto);
		assertTrue(gasStationDto.getGasStationId() == gasStation2.getGasStationId());
		assertTrue(gasStationDto.getGasStationName().equals(gasStation2.getGasStationName()));
		assertTrue(gasStationDto.getGasStationAddress().equals(gasStation2.getGasStationAddress()));
		assertTrue(gasStationDto.getHasDiesel() == gasStation2.getHasDiesel());
		assertTrue(gasStationDto.getHasGas() == gasStation2.getHasGas());
		assertTrue(gasStationDto.getHasSuper() == gasStation2.getHasSuper());
		assertTrue(gasStationDto.getHasSuperPlus() == gasStation2.getHasSuperPlus());
		assertTrue(gasStationDto.getHasMethane() == gasStation2.getHasMethane());
		assertTrue(gasStationDto.getHasPremiumDiesel() == gasStation2.getHasPremiumDiesel());
		assertTrue(gasStationDto.getCarSharing().equals(gasStation2.getCarSharing()));
		assertTrue(gasStationDto.getLat() == gasStation2.getLat());
		assertTrue(gasStationDto.getLon() == gasStation2.getLon());
		assertTrue(gasStationDto.getDieselPrice() == gasStation2.getDieselPrice());
		assertTrue(gasStationDto.getMethanePrice() == gasStation2.getMethanePrice());
		assertTrue(gasStationDto.getGasPrice() == gasStation2.getGasPrice());
		assertTrue(gasStationDto.getSuperPrice() == gasStation2.getSuperPrice());
		assertTrue(gasStationDto.getSuperPlusPrice() == gasStation2.getSuperPlusPrice());
		assertTrue(gasStationDto.getReportUser() == gasStation2.getReportUser());
		assertTrue(gasStationDto.getReportTimestamp().equals(gasStation2.getReportTimestamp()));
		assertTrue(gasStationDto.getReportDependability() == gasStation2.getReportDependability());
		assertTrue(gasStationDto.getUserDto().getUserId().equals(UserConverter.userConvertToUserDto(gasStation2.getUser()).getUserId()));

	}

}
