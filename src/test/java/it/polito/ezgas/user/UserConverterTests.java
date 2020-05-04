package it.polito.ezgas.user;

import org.junit.*;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverterTests {
	private User user;
	private UserDto userDto;
	
	@Before
	public void setUp() throws Exception {
		this.user = new User();		
		this.user.setUserName("Aldo");
		this.user.setEmail("aldo.baglio@agg.it");
		this.user.setPassword("buonaquestacadrega");
		this.user.setReputation(0);
		this.user.setAdmin(false);
		
		this.userDto = new UserDto();	
		this.userDto.setUserName("Aldo");
		this.userDto.setEmail("aldo.baglio@agg.it");
		this.userDto.setPassword("buonaquestacadrega");
		this.userDto.setReputation(0);
		this.userDto.setAdmin(false);
	}
	
	@Test
	public void testUserToDto() {
				
	}
	
	@Test
	public void testDtoToUser() {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

}
