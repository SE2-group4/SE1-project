package it.polito.ezgas.dto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GasStationDtoTests {
	GasStationDto gd1;
	
	@BeforeEach
	public void setUp() {
		this.gd1 = new GasStationDto();
	}
	
	@Test
	public void setHasSuper_ShouldHaveUpdateValue() {
		this.gd1.setHasSuper(true);
		assertTrue(this.gd1.getHasSuper());
	}
	

	@Test
	public void setHasSuperPlus_ShouldHaveUpdateValue() {
		this.gd1.setHasSuperPlus(true);
		assertTrue(this.gd1.getHasSuperPlus());
		
	}

	@Test
	public void setHasGas_ShouldHaveUpdateValue() {
		this.gd1.setHasGas(true);
		assertTrue(this.gd1.getHasGas());
		
	}
}
