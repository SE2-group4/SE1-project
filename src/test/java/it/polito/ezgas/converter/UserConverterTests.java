package it.polito.ezgas.converter;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

//import org.junit.*;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverterTests {
	
	private UserDto userDto;
	private User user;
	
	@BeforeEach
	public void setUp() throws Exception {
		userDto = new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, true);
		user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(true);
	}

	@Test
	public void testUserConvertToUserDto() {
		
		UserDto userDto2 = UserConverter.userConvertToUserDto(this.user);
		assertTrue(this.user.getUserId() == userDto2.getUserId());
		assertTrue(this.user.getUserName().equals(userDto2.getUserName()));
		assertTrue(this.user.getPassword().equals(userDto2.getPassword()));
		assertTrue(this.user.getEmail().equals(userDto2.getEmail()));
		assertTrue(this.user.getReputation() == userDto2.getReputation());
		assertTrue(this.user.getAdmin() == userDto2.getAdmin());
	
	}
	
	@Test
	public void testUserDtoConvertToUser() {
		
		User user2 = UserConverter.userDtoConvertToUser(this.userDto);
		assertTrue(this.userDto.getUserId() == user2.getUserId());
		assertTrue(this.userDto.getUserName().equals(user2.getUserName()));
		assertTrue(this.userDto.getPassword().equals(user2.getPassword()));
		assertTrue(this.userDto.getEmail().equals(user2.getEmail()));
		assertTrue(this.userDto.getReputation() == user2.getReputation());
		assertTrue(this.userDto.getAdmin() == user2.getAdmin());
	
	}
	
	/*@After
	public void tearDown() throws Exception {
		
	}*/

}
