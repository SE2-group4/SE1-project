package it.polito.ezgas.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;

@RunWith(SpringRunner.class)
@DataJpaTest
class GasStationRepositoryTest {

	@Autowired
	private GasStationRepository gasStationRepository;
	
	private GasStation gasStation;
	private GasStation gasStation2;	
	private GasStationDto gasStationDto;
	private List<GasStation> gsList = new ArrayList<GasStation>();
	
	@BeforeEach
	public void setUp() throws Exception {
		
		/*user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(true);
		userDto = new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, true);*/
			

	}
	
	@Test
    public void testfindByGasStationId() {
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		gasStationDto = new GasStationDto(1, "Esso", "Via Filatoio Calcio Lombardy Italy", true, false, false, false, false, "Enjoy", 45.4955025, 9.8530837, 1.0, -1, -1, -1, -1, -1, null, 0);
		gasStationDto.setUserDto(null);
		gsList.add(gasStation);
		gsList.add(gasStation2);	

        Optional<GasStation> gsList2 = gasStationRepository.findByGasStationId(gasStation.getGasStationId());
        GasStation gs = gsList2.get();
        assertNotNull(gs);
        assertEquals(gs.getGasStationId(), gasStation.getGasStationId());
        assertEquals(gs.getGasStationName(), gasStation.getGasStationName());
        assertEquals(gs.getGasStationAddress(), gasStation.getGasStationAddress());
        assertEquals(gs.getHasDiesel(), gasStation.getHasDiesel());
        assertEquals(gs.getHasGas(), gasStation.getHasGas());
        assertEquals(gs.getHasMethane(), gasStation.getHasMethane());
        assertEquals(gs.getHasSuper(), gasStation.getHasSuper());
        assertEquals(gs.getHasSuperPlus(), gasStation.getHasSuperPlus());
        assertEquals(gs.getCarSharing(), gasStation.getCarSharing());
        assertEquals(gs.getDieselPrice(), gasStation.getDieselPrice());
        assertEquals(gs.getGasPrice(), gasStation.getGasPrice());
        assertEquals(gs.getMethanePrice(), gasStation.getMethanePrice());
        assertEquals(gs.getSuperPrice(), gasStation.getSuperPrice());
        assertEquals(gs.getSuperPlusPrice(), gasStation.getSuperPlusPrice());
        assertEquals(gs.getLat(), gasStation.getLat());
        assertEquals(gs.getLon(), gasStation.getLon());

	}

}
