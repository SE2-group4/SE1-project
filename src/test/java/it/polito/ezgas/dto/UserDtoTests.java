package it.polito.ezgas.dto;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class UserDtoTests {
	@Test
	public void notInitializedAdmin_ShouldBeFalse() {
		UserDto ud1 = new UserDto(1, "Aldo", "aldo", "aldo.baglio@agg.it", 3);
		assertFalse(ud1.getAdmin());
	}
}
