package it.polito.ezgas.service;



import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;

import exception.InvalidGasStationException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.impl.UserServiceImpl;

public class UserServiceTests {
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl(userRepository);
        }
    }
	
	/*
	 * not working...
	 * 
	@Autowired
    private TestEntityManager entityManager;
    */
    
    @Autowired
    static UserService service;   
    
    static UserRepository userRepository;
    
    private int initSize;
    private List<User> myList;
	
    
	@BeforeAll
	static void initializeTest() {
		userRepository = mock(UserRepository.class);
		service = new UserServiceImpl(userRepository);
	}
	
	boolean compareUserDto(UserDto u1, UserDto u2) {
		if (u1 == null && u2 == null)
			return true;
		if (u1 == null || u2 == null)
			return false;
		
		return (u1.getAdmin() == u2.getAdmin() 
				&& u1.getEmail().compareTo(u2.getEmail()) == 0
				&& u1.getPassword().compareTo(u2.getPassword()) == 0
				&& u1.getReputation() == u2.getReputation()
				&& u1.getUserId() == u2.getUserId()
				&& u1.getUserName().compareTo(u2.getUserName()) == 0);
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		/*List<UserDto> list = service.getAllUsers();
			
		this.initSize = list.size();
		
		this.myList = new ArrayList<>();
		User user;

		user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(false);
		this.myList.add(user);
		assertTrue(service.saveUser(UserConverter.userConvertToUserDto(user)) != null);
		//assertTrue(this.userRepository.save(user) != null);
		//this.entityManager.persist(user);

		user = new User();
		user.setUserId(2);
		user.setUserName("Giovanni");
		user.setPassword("franco");
		user.setEmail("giovanni.storti@agg.it");
		user.setReputation(+5);
		user.setAdmin(true);
		this.myList.add(user);
		assertTrue(userRepository.save(user) != null);
		//this.entityManager.persist(user);

		user = new User();
		user.setUserId(3);
		user.setUserName("Giacomo");
		user.setPassword("ilnonno");
		user.setEmail("giacomo.poretti@agg.it");
		user.setReputation(+1);
		user.setAdmin(false);
		this.myList.add(user);
		assertTrue(userRepository.save(user) != null);
		//this.entityManager.persist(user);
		
		assertEquals(this.getExpectedSize(), userRepository.count());
		//this.entityManager.flush();

		/*
		myList.add(new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, false));
		myList.add(new UserDto(2, "Giovanni", "franco", "giovanni.storti@agg.it", +5, true));
		myList.add(new UserDto(3, "Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1, false));
		*/
	}
	/*
	@AfterEach
	public void tearDown() throws Exception {
		for(User user : this.myList) {
			try {
				service.deleteUser(user.getUserId());
			} catch(Exception e) {}
		}
		this.myList.clear();
		
		//this.entityManager.clear();
	}*/
	
	public int getExpectedSize() {
		return(this.initSize + this.myList.size());
	}
	
	@Test
	public void testUniqueUser(){
		UserDto user = new UserDto(4, "Silvana", "worstactress", "silvana.fallisi@agg.it", 5);
		
		UserDto insertedUser1 = service.saveUser(user);
		assertTrue(insertedUser1 != null);
		assertTrue(insertedUser1.getReputation() == 0, "Init reputation must be 0!");
		
		user.setUserId(5);
		UserDto insertedUser2 = service.saveUser(user);
		assertTrue(insertedUser2 != null);
		assertTrue(insertedUser1.getEmail().compareTo(insertedUser2.getEmail()) != 0, "Email must be unique!");
	}
	
	@Test
	public void testGet() {
		List<UserDto> list = service.getAllUsers();
		assertTrue(list != null);
		assertEquals(this.getExpectedSize(), list.size());
		
		User myUser = this.myList.get(0);		
		UserDto user = null;
		try {
			user = service.getUserById(myUser.getUserId());
		} catch (InvalidUserException e) {
			fail();
		} 
		assertSame(user.getUserId(), myUser.getUserId());
		assertSame(user.getUserName(), myUser.getUserName());
	}
	
	
	
	@Test
	public void testDelete() {
		User user = this.myList.remove(2);
		
		try {
			service.deleteUser(user.getUserId());			
		} catch (InvalidUserException e) {
			fail();
		}
		List<UserDto> list = service.getAllUsers();
		
		assertEquals(list.size(), this.getExpectedSize());
	}
	
	public void testDecreaseReputation() {
		int id = this.myList.get(1).getUserId();
		UserDto user = null;
		int previousReputation;
		
		try {
			user = service.getUserById(id);
		} catch (InvalidUserException e1) {
			fail();
		}
		
		previousReputation = user.getReputation();
		
		try {
			service.decreaseUserReputation(id);
		} catch (InvalidUserException e) {
			fail();
		}
		assertTrue(previousReputation >= user.getReputation(), "User reputation should be less then before!");
	}
	
	@Test
	public void testIncreaseReputation() {
		int id = this.myList.get(1).getUserId();
		UserDto user = null;
		int previousReputation;
		
		try {
			user = service.getUserById(id);
		} catch (InvalidUserException e1) {
			fail();
		}

		assertTrue(user != null);
		previousReputation = user.getReputation();		
		try {
			service.increaseUserReputation(id);
		} catch (InvalidUserException e) {
			fail();
		}
		assertTrue(user.getReputation() >= previousReputation, "User reputation should be more then before!");
	}
	
	@Test
	public void testGetUserException() {
		try {
			service.getUserById(-1);
			fail("InvalidUserException expected!");
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail("InvalidUserException expected!");
		}
	}

	@Test
	public void testDeleteUserException() {
		try {
			service.deleteUser(-1);
			fail("InvalidUserException expected!");
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail("InvalidUserException expected!");
		}
	}

	@Nested
	@DisplayName("Test for getAllUsers")
	public class GetAllUsers {
		User u1 = new User();
		User u2 = new User();
		List<User> uListEmpty = new ArrayList<>();
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1);
			u1.setUserId(1);
			u1.setAdmin(false);
			System.out.println(u1.getUserId());
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +5);
			u2.setUserId(2);
			u2.setAdmin(true);
			
			
			List<User> uList = new ArrayList<>();
			
			uList.add(u1);
			uList.add(u2);
			
			when(userRepository.findAll()).thenReturn(uList);
		}
		
		@Test
		public void _returnEmptyList() {
			when(userRepository.findAll()).thenReturn(uListEmpty);
			assertTrue("List of users retrieved is not empty", uListEmpty.isEmpty());
		}
		
		@Test
		public void _returnUserDtoList() {
				List<UserDto> userDto = service.getAllUsers();
				System.out.println(userDto.get(0));
				assertTrue("User retrieved is not the same that has been inserted",
						compareUserDto(userDto.get(0), UserConverter.userConvertToUserDto(u1)));
				assertTrue("User retrieved is not the same that has been inserted",
						compareUserDto(userDto.get(1), UserConverter.userConvertToUserDto(u2)));
		}
	}
	
	@Nested
	@DisplayName("Test for increase reputation")
	public class IncreaseReputation {
		
		User u1 = new User();
		User u2 = new User();
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", +5);
			u1.setUserId(1);
			u1.setAdmin(true);
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +1);
			u2.setUserId(-2);
			u2.setAdmin(false);		
		}
		
		@Test
		public void testIncrease() {
			u2.setUserId(2);
			try {
				assertEquals((u2.getReputation()+1), service.increaseUserReputation(u2.getUserId()));
			} catch(InvalidUserException e) {}
		}

		@Test
		public void testInvalidUserException() {
			try {
				service.getUserById(u2.getUserId());
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {}
		}
		
		@Test
		public void nonExistingId() {
			try {
				UserDto userDto = service.getUserById(999);
				assertNull("User should be null", userDto);
			} catch (InvalidUserException e) {}
			
		}
	}
	
	@Nested
	@DisplayName("Test for decrease reputation")
	public class DecreaseReputation {

		User u1 = new User();
		User u2 = new User();
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", -5);
			u1.setUserId(1);
			u1.setAdmin(true);
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +1);
			u2.setUserId(-2);
			u2.setAdmin(false);		
		}
		
		@Test
		public void testDecrease() {
			u2.setUserId(2);
			try {
				assertEquals((u2.getReputation()-1), service.decreaseUserReputation(u2.getUserId()));
			} catch(InvalidUserException e) {}
		}

		@Test
		public void testInvalidUserException() {
			try {
				service.getUserById(u2.getUserId());
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {}
		}
		
		@Test
		public void nonExistingId() {
			try {
				UserDto userDto = service.getUserById(999);
				assertNull("User should be null", userDto);
			} catch (InvalidUserException e) {}
		}
	}
}
