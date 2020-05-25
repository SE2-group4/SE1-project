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

import it.polito.ezgas.entity.GasStation;


@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationRepositoryTest {
	
	@Autowired
	private GasStationRepository gasStationRepository;
	
	private GasStation gasStation;
	private GasStation gasStation2;	
	private List<GasStation> gsList = new ArrayList<GasStation>();
	
		
	@Test
    public void testFindByGasStationId() {
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		
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
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}

	@Test
    public void testFindByHasMethaneTrueOrderByMethanePriceAsc() {
		
		GasStation gs;
		GasStation gs2;
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.0, 1.0, 1.0, 1.0, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.5, 1.5, 1.5, 1.5, 1.5, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
        List<GasStation> gsList2 = gasStationRepository.findByHasMethaneTrueOrderByMethanePriceAsc();  
        
        gs = gsList2.get(0);
        gs2 = gsList2.get(1);
        
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
        assertNotNull(gs2);
        assertEquals(gs2.getGasStationId(), gasStation2.getGasStationId());
        assertEquals(gs2.getGasStationName(), gasStation2.getGasStationName());
        assertEquals(gs2.getGasStationAddress(), gasStation2.getGasStationAddress());
        assertEquals(gs2.getHasDiesel(), gasStation2.getHasDiesel());
        assertEquals(gs2.getHasGas(), gasStation2.getHasGas());
        assertEquals(gs2.getHasMethane(), gasStation2.getHasMethane());
        assertEquals(gs2.getHasSuper(), gasStation2.getHasSuper());
        assertEquals(gs2.getHasSuperPlus(), gasStation2.getHasSuperPlus());
        assertEquals(gs2.getCarSharing(), gasStation2.getCarSharing());
        assertEquals(gs2.getDieselPrice(), gasStation2.getDieselPrice());
        assertEquals(gs2.getGasPrice(), gasStation2.getGasPrice());
        assertEquals(gs2.getMethanePrice(), gasStation2.getMethanePrice());
        assertEquals(gs2.getSuperPrice(), gasStation2.getSuperPrice());
        assertEquals(gs2.getSuperPlusPrice(), gasStation2.getSuperPlusPrice());
        assertEquals(gs2.getLat(), gasStation2.getLat());
        assertEquals(gs2.getLon(), gasStation2.getLon());
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}
	
	@Test
    public void testFindByHasDieselTrueOrderByDieselPriceAsc() {
		
		GasStation gs;
		GasStation gs2;
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
	
        List<GasStation> gsList2 = gasStationRepository.findByHasDieselTrueOrderByDieselPriceAsc();
        
        gs = gsList2.get(0);
        gs2 = gsList2.get(1);
        
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
        assertNotNull(gs2);
        assertEquals(gs2.getGasStationId(), gasStation2.getGasStationId());
        assertEquals(gs2.getGasStationName(), gasStation2.getGasStationName());
        assertEquals(gs2.getGasStationAddress(), gasStation2.getGasStationAddress());
        assertEquals(gs2.getHasDiesel(), gasStation2.getHasDiesel());
        assertEquals(gs2.getHasGas(), gasStation2.getHasGas());
        assertEquals(gs2.getHasMethane(), gasStation2.getHasMethane());
        assertEquals(gs2.getHasSuper(), gasStation2.getHasSuper());
        assertEquals(gs2.getHasSuperPlus(), gasStation2.getHasSuperPlus());
        assertEquals(gs2.getCarSharing(), gasStation2.getCarSharing());
        assertEquals(gs2.getDieselPrice(), gasStation2.getDieselPrice());
        assertEquals(gs2.getGasPrice(), gasStation2.getGasPrice());
        assertEquals(gs2.getMethanePrice(), gasStation2.getMethanePrice());
        assertEquals(gs2.getSuperPrice(), gasStation2.getSuperPrice());
        assertEquals(gs2.getSuperPlusPrice(), gasStation2.getSuperPlusPrice());
        assertEquals(gs2.getLat(), gasStation2.getLat());
        assertEquals(gs2.getLon(), gasStation2.getLon());
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}
	
	@Test
    public void testFindByHasSuperTrueOrderBySuperPriceAsc() {
		
		GasStation gs;
		GasStation gs2;
		
		gasStation = new GasStation("Q8", 			 "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
        List<GasStation> gsList2 = gasStationRepository.findByHasSuperTrueOrderBySuperPriceAsc();
        
        gs = gsList2.get(0);
        gs2 = gsList2.get(1);
        
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
        assertNotNull(gs2);
        assertEquals(gs2.getGasStationId(), gasStation2.getGasStationId());
        assertEquals(gs2.getGasStationName(), gasStation2.getGasStationName());
        assertEquals(gs2.getGasStationAddress(), gasStation2.getGasStationAddress());
        assertEquals(gs2.getHasDiesel(), gasStation2.getHasDiesel());
        assertEquals(gs2.getHasGas(), gasStation2.getHasGas());
        assertEquals(gs2.getHasMethane(), gasStation2.getHasMethane());
        assertEquals(gs2.getHasSuper(), gasStation2.getHasSuper());
        assertEquals(gs2.getHasSuperPlus(), gasStation2.getHasSuperPlus());
        assertEquals(gs2.getCarSharing(), gasStation2.getCarSharing());
        assertEquals(gs2.getDieselPrice(), gasStation2.getDieselPrice());
        assertEquals(gs2.getGasPrice(), gasStation2.getGasPrice());
        assertEquals(gs2.getMethanePrice(), gasStation2.getMethanePrice());
        assertEquals(gs2.getSuperPrice(), gasStation2.getSuperPrice());
        assertEquals(gs2.getSuperPlusPrice(), gasStation2.getSuperPlusPrice());
        assertEquals(gs2.getLat(), gasStation2.getLat());
        assertEquals(gs2.getLon(), gasStation2.getLon());
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}

	@Test
    public void testFindByHasSuperPlusTrueOrderBySuperPlusPriceAsc() {
		
		GasStation gs;
		GasStation gs2;
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.0, 1.0, 1.0, 1.0, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.5, 1.5, 1.5, 1.5, 1.5, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
        List<GasStation> gsList2 = gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPriceAsc();
        
        gs = gsList2.get(0);
        gs2 = gsList2.get(1);
        
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
        assertNotNull(gs2);
        assertEquals(gs2.getGasStationId(), gasStation2.getGasStationId());
        assertEquals(gs2.getGasStationName(), gasStation2.getGasStationName());
        assertEquals(gs2.getGasStationAddress(), gasStation2.getGasStationAddress());
        assertEquals(gs2.getHasDiesel(), gasStation2.getHasDiesel());
        assertEquals(gs2.getHasGas(), gasStation2.getHasGas());
        assertEquals(gs2.getHasMethane(), gasStation2.getHasMethane());
        assertEquals(gs2.getHasSuper(), gasStation2.getHasSuper());
        assertEquals(gs2.getHasSuperPlus(), gasStation2.getHasSuperPlus());
        assertEquals(gs2.getCarSharing(), gasStation2.getCarSharing());
        assertEquals(gs2.getDieselPrice(), gasStation2.getDieselPrice());
        assertEquals(gs2.getGasPrice(), gasStation2.getGasPrice());
        assertEquals(gs2.getMethanePrice(), gasStation2.getMethanePrice());
        assertEquals(gs2.getSuperPrice(), gasStation2.getSuperPrice());
        assertEquals(gs2.getSuperPlusPrice(), gasStation2.getSuperPlusPrice());
        assertEquals(gs2.getLat(), gasStation2.getLat());
        assertEquals(gs2.getLon(), gasStation2.getLon());
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}
	
	@Test
    public void testFindByHasGasTrueOrderByGasPriceAsc() {
		
		GasStation gs;
		GasStation gs2;
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go",
									45.4955025, 9.8530837, 1.0, 1.0, 1.0, 1.0, 1.0, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy",
									45.4955025, 9.8530837, 1.5, 1.5, 1.5, 1.5, 1.5, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
		System.out.println(gasStation.getGasStationId());
		
		
        List<GasStation> gsList2 = gasStationRepository.findByHasGasTrueOrderByGasPriceAsc();
        
        gs = gsList2.get(0);
        gs2 = gsList2.get(1);
        System.out.println(gsList2);
        
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
        assertNotNull(gs2);
        assertEquals(gs2.getGasStationId(), gasStation2.getGasStationId());
        assertEquals(gs2.getGasStationName(), gasStation2.getGasStationName());
        assertEquals(gs2.getGasStationAddress(), gasStation2.getGasStationAddress());
        assertEquals(gs2.getHasDiesel(), gasStation2.getHasDiesel());
        assertEquals(gs2.getHasGas(), gasStation2.getHasGas());
        assertEquals(gs2.getHasMethane(), gasStation2.getHasMethane());
        assertEquals(gs2.getHasSuper(), gasStation2.getHasSuper());
        assertEquals(gs2.getHasSuperPlus(), gasStation2.getHasSuperPlus());
        assertEquals(gs2.getCarSharing(), gasStation2.getCarSharing());
        assertEquals(gs2.getDieselPrice(), gasStation2.getDieselPrice());
        assertEquals(gs2.getGasPrice(), gasStation2.getGasPrice());
        assertEquals(gs2.getMethanePrice(), gasStation2.getMethanePrice());
        assertEquals(gs2.getSuperPrice(), gasStation2.getSuperPrice());
        assertEquals(gs2.getSuperPlusPrice(), gasStation2.getSuperPlusPrice());
        assertEquals(gs2.getLat(), gasStation2.getLat());
        assertEquals(gs2.getLon(), gasStation2.getLon());
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}
	
	@Test
    public void testFindByCarSharing() {
		
		gasStation = new GasStation("Q8", "Via Calcio Lombardy Italy", true, true, true, true, true, "Car2Go", 45.4955025, 9.8530837, 1.0, 1.2, 1.9, 1.1, 1.4, -1, null, 0);
		gasStation2 = new GasStation("Esso", "Via Filatoio Calcio Lombardy Italy", true, true, true, true, true, "Enjoy", 45.4955025, 9.8530837, 1.0, 1.5, 1.5, 1.7, 1.3, -1, null, 0);
		
		gsList.add(gasStation);
		gsList.add(gasStation2);	
		
		
		gasStationRepository.save(gasStation);
		gasStationRepository.save(gasStation2);
		
        List<GasStation> gsList2 = gasStationRepository.findByCarSharing(gasStation.getCarSharing());
        GasStation gs = gsList2.get(0);
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
        
        gasStationRepository.delete(gasStation);
		gasStationRepository.delete(gasStation2);

	}
	
}