package it.polito.ezgas.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private GasStationRepository gasStationRepository;
	
	private GasStation gasStation;
	private GasStation gasStation2;	
	private List<GasStation> gsList = new ArrayList<GasStation>();
	
	@Test
    public void testFindByEmailAndPassword() {

        User user = new User("admin", "password", "admin@gmail.com", 1);
        user.setAdmin(true);
        //user.setUserId(50);
        userRepository.save(user);
        List<User> uList = userRepository.findByEmailAndPassword("admin@gmail.com", "password");
        assertNotNull(user);
        assertEquals(1, uList.size());
        User user2 = uList.get(0);
        assertNotNull(user2);
        assertEquals(user.getUserName(), user2.getUserName());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getEmail(), user2.getEmail());
    }
	
	@Test
    public void testFindByUserId() {

		User user3 = new User("admin3", "password3", "admin3@gmail.com", 1);
        user3.setAdmin(true);
        userRepository.save(user3);
        List<User> uList = userRepository.findByUserId(user3.getUserId());
        assertNotNull(user3);
        assertEquals(1, uList.size());      
        User user4 = uList.get(0);
        assertNotNull(user4);
        assertEquals(user3.getAdmin(), user4.getAdmin());
        assertEquals(user3.getUserId(), user4.getUserId());
        assertEquals(user3.getUserName(), user4.getUserName());
        assertEquals(user3.getPassword(), user4.getPassword());
        assertEquals(user3.getEmail(), user4.getEmail());
        assertEquals(user3.getReputation(), user4.getReputation());
    }
	
	@Test
    public void testfindByGasStationId() {
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		gasStation.setUser(null);
		gasStation.setGasStationId(1);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
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
