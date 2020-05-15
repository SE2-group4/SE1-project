package it.polito.ezgas.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GasStationTest {
	private double delta = 0.001;
	private GasStation gasStation;
	private User user;

	@BeforeEach
	void setUp() throws Exception {
		this.gasStation = new GasStation("Red Rocket", "Andale Main Street, 42", true, true, false, false, true,
				"appreciate", 42.42, 21.21, 1.5, 1.6, -1, -1, 1.3, 1, "2020-05-12 14:22:31", 2.0);
		this.gasStation.setGasStationId(0);
		this.user = new User("Three ", "Dog", "threedog@fall.out", 3);
		this.user.setAdmin(false);
		this.gasStation.setUser(this.user);
	}

	@Test
	void getGasStationId__returnGasStationId() {
		assertTrue(this.gasStation.getGasStationId() == 0);
	}

	@Test
	void setGasStationId__modifyGasStationId() {
		Integer newId = 42;
		this.gasStation.setGasStationId(newId);
		assertEquals(this.gasStation.getGasStationId(), newId);
	}

	@Test
	void getGasStationName__returnGasStationName() {
		assertTrue(this.gasStation.getGasStationName().equals("Red Rocket"));
	}

	@Test
	void setGasStationName__modifyGasStationName() {
		String newValue = "Blue Rocket";
		this.gasStation.setGasStationName(newValue);
		assertEquals(newValue, this.gasStation.getGasStationName());
	}

	@Test
	void getGasStationAddress__returnGasStationAddress() {
		assertTrue(this.gasStation.getGasStationAddress().equals("Andale Main Street, 42"));
	}

	@Test
	void setGasStationAddress__modifyGasStationAddress() {
		String newValue = "Andale Main Street, 21";
		this.gasStation.setGasStationAddress(newValue);
		assertEquals(newValue, this.gasStation.getGasStationAddress());
	}

	@Test
	void getReportDependability__returnReportDependability() {
		assertEquals(2.0, this.gasStation.getReportDependability(), this.delta);
	}

	@Test
	void setReportDependability__modifyReportDependability() {
		double newValue = 3.0;
		this.gasStation.setReportDependability(newValue);
		assertEquals(newValue, this.gasStation.getReportDependability(), this.delta);
	}

	@Test
	void getReportUSer__returnReportUser() {
		assertTrue(this.gasStation.getReportUser() == 1);
	}

	@Test
	void setReportUSer__modifyReportUser() {
		Integer newValue = 2;
		this.gasStation.setReportUser(newValue);
		assertEquals(newValue, this.gasStation.getReportUser());
	}

	@Test
	void getReportTimestamp__returnReportTimestamp() {
		assertTrue(this.gasStation.getReportTimestamp().equals("2020-05-12 14:22:31"));
	}

	@Test
	void setReportTimestamp__modifyReportTimestamp() {
		String newValue = "2019-02-12 15:22:31";
		this.gasStation.setReportTimestamp(newValue);
		assertEquals(newValue, this.gasStation.getReportTimestamp());
	}

	@Test
	void getHasDiesel__returnHasDiesel() {
		assertTrue(this.gasStation.getHasDiesel() == true);
	}

	@Test
	void setHasDiesel__modifyHasDiesel() {
		boolean newValue = false;
		this.gasStation.setHasDiesel(newValue);
		assertEquals(newValue, this.gasStation.getHasDiesel());
	}

	@Test
	void getHasSuper__returnHasSuper() {
		assertTrue(this.gasStation.getHasSuper() == true);
	}

	@Test
	void setHasSuper__modifyHasSuper() {
		boolean newValue = false;
		this.gasStation.setHasSuper(newValue);
		assertEquals(newValue, this.gasStation.getHasSuper());
	}

	@Test
	void getHasSuperPlus__returnHasSuperPlus() {
		assertTrue(this.gasStation.getHasSuperPlus() == false);
	}

	@Test
	void setHasSuperPlus__modifyHasSuperPlus() {
		boolean newValue = true;
		this.gasStation.setHasSuperPlus(newValue);
		assertEquals(newValue, this.gasStation.getHasSuperPlus());
	}

	@Test
	void getHasGas__returnHasGas() {
		assertTrue(this.gasStation.getHasGas() == false);
	}

	@Test
	void setHasGas__modifyHasGas() {
		boolean newValue = true;
		this.gasStation.setHasGas(newValue);
		assertEquals(newValue, this.gasStation.getHasGas());
	}

	@Test
	void getHasMethane__returnHasMethane() {
		assertTrue(this.gasStation.getHasMethane() == true);
	}

	@Test
	void setHasMethane__modifyHasMethane() {
		boolean newValue = false;
		this.gasStation.setHasMethane(newValue);
		assertEquals(newValue, this.gasStation.getHasMethane());
	}

	@Test
	void getLat__returnLat() {
		assertEquals(42.42, this.gasStation.getLat(), this.delta);
	}

	@Test
	void setLat__modifyLat() {
		double newValue = 33.33;
		this.gasStation.setLat(newValue);
		assertEquals(newValue, this.gasStation.getLat(), this.delta);
	}

	@Test
	void getLon__returnLon() {
		assertEquals(21.21, this.gasStation.getLon(), this.delta);
	}

	@Test
	void setLon__modifyLon() {
		double newValue = 15.15;
		this.gasStation.setLon(newValue);
		assertEquals(newValue, this.gasStation.getLon(), this.delta);
	}

	@Test
	void getDieselPrice__returnDieselPrice() {
		assertEquals(1.5, this.gasStation.getDieselPrice(), this.delta);
	}

	@Test
	void setDieselPrice__modifyDieselPrice() {
		double newValue = 1.8;
		this.gasStation.setDieselPrice(newValue);
		assertEquals(newValue, this.gasStation.getDieselPrice(), this.delta);
	}

	@Test
	void getSuperPrice__returnSuperPrice() {
		assertEquals(1.6, this.gasStation.getSuperPrice(), this.delta);
	}

	@Test
	void setSuperPrice__modifySuperPrice() {
		double newValue = 1.9;
		this.gasStation.setSuperPrice(newValue);
		assertEquals(newValue, this.gasStation.getSuperPrice(), this.delta);
	}

	@Test
	void getSuperPlusPrice__returnSuperPlusPrice() {
		assertEquals(-1, this.gasStation.getSuperPlusPrice(), this.delta);
	}

	@Test
	void setSuperPlusPrice__modifySuperPlusPrice() {
		double newValue = 2.0;
		this.gasStation.setSuperPlusPrice(newValue);
		assertEquals(newValue, this.gasStation.getSuperPlusPrice(), this.delta);
	}

	@Test
	void getGasPrice__returnGasPrice() {
		assertEquals(-1, this.gasStation.getGasPrice(), this.delta);
	}

	@Test
	void setGasPrice__modifyGasPrice() {
		double newValue = 3.0;
		this.gasStation.setGasPrice(newValue);
		assertEquals(newValue, this.gasStation.getGasPrice(), this.delta);
	}

	@Test
	void getMethanePrice__returnMethanePrice() {
		assertEquals(1.3, this.gasStation.getMethanePrice(), this.delta);
	}

	@Test
	void setMethanePrice__modifyMethanePrice() {
		double newValue = 1.2;
		this.gasStation.setMethanePrice(newValue);
		assertEquals(newValue, this.gasStation.getMethanePrice(), this.delta);
	}

	@Test
	void getUser__returnUser() {
		assertEquals(this.user, this.gasStation.getUser());
	}

	@Test
	void setUser__modifyUser() {
		User newValue = new User("Allistair ", "Tenpenny", "allt@fall.out", -5);
		this.user.setAdmin(false);
		this.gasStation.setUser(newValue);
		assertEquals(newValue, this.gasStation.getUser());
	}

	@Test
	void getCarSharing__returnCarSharing() {
		assertTrue(this.gasStation.getCarSharing().equals("appreciate"));
	}

	@Test
	void setCarSharing__modifyCarSharing() {
		String newValue = "bike3stop";
		this.gasStation.setCarSharing(newValue);
		assertEquals(newValue, this.gasStation.getCarSharing());
	}

}
