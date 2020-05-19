package it.polito.ezgas.converter;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverterTests {
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConversion() {
		UserDto userDto = new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, true);
		User user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(true);
		
		User user2 = UserConverter.userDtoConvertToUser(userDto);
		assertTrue(userDto.getUserId() == user2.getUserId());
		
		UserDto userDto2 = UserConverter.userConvertToUserDto(user);
		assertTrue(user.getAdmin() == userDto2.getAdmin());
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

}
