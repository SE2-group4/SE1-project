package it.polito.ezgas.service;



//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;

import org.springframework.context.annotation.Bean;

import exception.InvalidGasStationException;
import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
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
	
    
    @Autowired
    static UserService service;   
    
    static UserRepository userRepository;
    	
    
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
	
	boolean compareLoginDto(User u1, LoginDto l1) {
		if (u1 == null && l1 == null)
			return true;
		if (u1 == null || l1 == null)
			return false;
		
		return (u1.getAdmin() == l1.getAdmin() 
				&& u1.getEmail().compareTo(l1.getEmail()) == 0
				// && u1.getPassword().compareTo(l1.getPassword()) == 0
				&& u1.getReputation() == l1.getReputation()
				&& u1.getUserId() == l1.getUserId()
				&& u1.getUserName().compareTo(l1.getUserName()) == 0);
	}
	
	
	@Nested
	@DisplayName("Test getUserById")
	public class GetUserById {
		
		private List<User> uList; 
		
		@BeforeEach
		void setUp() {
			this.uList = new ArrayList<User>();
			
			User u1 = new User("Aldo", "aldo", "aldo.baglio@agg.it", -2);
			u1.setUserId(4);
			u1.setAdmin(false);
			this.uList.add(u1);
			
			initializeTest(); // re-create all mocks
			when(userRepository.findByUserId(4)).thenReturn(uList);
		}
		
		@Test
		public void getUser_UserId_ShouldReturnUser() {
			UserDto ud1 = null;
			try {
				ud1 = service.getUserById(4);
			} catch (Exception e) {
				fail();
			}
			assertTrue(compareUserDto(UserConverter.userConvertToUserDto(this.uList.get(0)), ud1));
		}
		
		@Test
		public void getUser_NotExistingUserId_ShouldThrowException() {
			try {
				service.getUserById(-1);
				fail();
			} catch (InvalidUserException e) {}
			catch(Exception e) {
				fail();
			}
		}	
	}
	
	@Nested
	@DisplayName("Test saveUser")
	public class SaveUser {
		User u1;
		UserDto ud1;
		List<User> uList;

		@BeforeEach
		public void setUp() {
			this.u1 = new User("Aldo", "aldo", "aldo.baglio@agg.it", 5);
			this.u1.setUserId(4);
			this.u1.setAdmin(false);
			this.ud1 = UserConverter.userConvertToUserDto(this.u1);
			
			this.uList = new ArrayList<User>();
			this.uList.add(this.u1);			
			
			initializeTest(); // re-create all mocks
		}
		
		@Test
		public void saveNewUser_ShouldHaveReputationEq0() {
			when(userRepository.findAll()).thenReturn(new ArrayList<User>()); // user not inserted yet
			when(userRepository.save(Mockito.any(User.class))).thenAnswer(new Answer<User>() {
				   public User answer(InvocationOnMock invocation) {
				     Object[] args = invocation.getArguments();
				     // Object mock = invocation.getMock();
				     return (User)args[0];
				   }
				}); // return the same user inserted
			
			UserDto insertedUser1 = service.saveUser(this.ud1);
			assertNotNull(insertedUser1);
			assertEquals(0, insertedUser1.getReputation(), "Init reputation must be 0!");
		}
		
		@Test
		public void saveTwoUser_ShouldNotHaveSameEmail(){
			when(userRepository.findAll()).thenReturn(this.uList);
			when(userRepository.save(Mockito.any(User.class))).thenReturn(this.u1);
			
			this.u1.setUserId(5);
			UserDto insertedUser1 = service.saveUser(this.ud1);
			assertNotNull(insertedUser1);
			
			this.u1.setUserId(3);
			UserDto insertedUser2 = service.saveUser(this.ud1);
			assertNotNull(insertedUser2);
			
			int nUsers = service.getAllUsers().stream().filter(u -> u.getEmail().equals(this.u1.getEmail())).collect(Collectors.toList()).size();
			assertEquals(1, nUsers, "Email must be unique!");
		}
	}

	/*@Test
	public void testGet() {
		List<UserDto> list = service.getAllUsers();
		assertTrue(list != null);
		
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
	}*/
	
	
	@Nested
	@DisplayName("Test for deleteUser")
	public class DeleteUser{
		
		User u1 = new User();
		User u2 = new User();
		List<User> uListEmpty = new ArrayList<>();
		List<User> uList = new ArrayList<>();
		int size_uList;
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1);
			u1.setUserId(1);
			u1.setAdmin(false);
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +5);
			u2.setUserId(2);
			u2.setAdmin(true);
			
			uList = new ArrayList<>();
			
			uList.add(u1);
			uList.add(u2);
			
			size_uList = uList.size();

			initializeTest(); // re-create all mocks
			when(userRepository.findByUserId(u1.getUserId())).thenReturn(new ArrayList<User>());
			when(userRepository.findAll()).thenReturn(uList);
		}
		
		@Test
		public void testDelete() {
			
			uList.remove(u1);
			boolean del = false;
			try {
				del = service.deleteUser(u1.getUserId());			
			} catch (InvalidUserException e) {}
			List<UserDto> list = service.getAllUsers();
			
			assertEquals(size_uList-1, list.size());
			assertTrue(del, "User is not deleted.");
		}
		
		
		@Test
		public void testInvalidUserException() {
			u2.setUserId(-1);
			try {
				service.deleteUser(u2.getUserId());
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {}
		}
		
		@Test
		public void nonExistingId() {
			try {
				service.deleteUser(999);
			} catch (InvalidUserException e) {}
			
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
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +5);
			u2.setUserId(2);
			u2.setAdmin(true);
			
			
			List<User> uList = new ArrayList<>();
			
			uList.add(u1);
			uList.add(u2);

			initializeTest(); // re-create all mocks
			when(userRepository.findAll()).thenReturn(uList);
		}
		
		@Test
		public void _returnEmptyList() {
			when(userRepository.findAll()).thenReturn(uListEmpty);
			assertTrue(uListEmpty.isEmpty(), "List of users retrieved is not empty");
		}
		
		@Test
		public void _returnUserDtoList() {
				List<UserDto> userDto = service.getAllUsers();
				assertTrue(compareUserDto(userDto.get(0), UserConverter.userConvertToUserDto(u1)), 
						"User retrieved is not the same that has been inserted");
				assertTrue(compareUserDto(userDto.get(1), UserConverter.userConvertToUserDto(u2)),
						"User retrieved is not the same that has been inserted");
		}
	}
	
	@Nested
	@DisplayName ("Test for login")
	public class Login {
		User u1;
		List<User> uList;
		
		@BeforeEach
		public void setUp() {
			this.uList = new ArrayList<User>();
			
			this.u1 = new User("Giovanni", "gvnn", "giovanni.storti@agg.it", +1);
			this.u1.setUserId(3);
			this.u1.setAdmin(true);
			this.uList.add(this.u1);

			initializeTest(); // re-create all mocks
			when(userRepository.findByUserId(u1.getUserId())).thenReturn(uList);
			when(userRepository.findByEmailAndPassword(this.u1.getEmail(), this.u1.getPassword())).thenReturn(this.uList);
		}
		
		@Test
		public void invalidEmail_ShouldThrowException() {
			IdPw credentials = new IdPw("invalid email", this.u1.getPassword());
			try {
				service.login(credentials);
				fail();
			} catch (InvalidLoginDataException e) {
				// good!
			}
		}
		
		@Test
		public void invalidPassword_ShouldThrowException() {
			// IdPw credentials = new IdPw(this.u1.getEmail(), "invalid email");
			IdPw credentials = new IdPw();
			credentials.setUser(this.u1.getEmail());
			credentials.setPw("invalid email");
			
			try {
				service.login(credentials);
				fail();
			} catch (InvalidLoginDataException e) {
				// good!
			}
		}
		
		@Test
		public void correctIdPw_ShouldReturnLogin() {
			IdPw credentials = new IdPw(this.u1.getEmail(), this.u1.getPassword());
			LoginDto res = null;
			try {
				res = service.login(credentials);
			} catch (InvalidLoginDataException e) {
				fail();
			}
			
			assertTrue(compareLoginDto(this.u1, res));			
		}
	}
	
	@Nested
	@DisplayName("Test for increase reputation")
	public class IncreaseReputation {
		
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", +5);
			u1.setUserId(1);
			u1.setAdmin(true);
			u2 = new User("Giovanni", "franco", "giovanni2.storti@agg.it", +1);
			u2.setUserId(-2);
			u2.setAdmin(false);
			u3 = new User("Giacomo", "ilnonno", "giacomo3.poretti@agg.it", +2);
			u3.setUserId(3);
			u3.setAdmin(true);
			List<User> uList = new ArrayList<User>();
			uList.add(u1);
			List<User> uList2 = new ArrayList<User>();
			uList2.add(u2);
			List<User> uList3 = new ArrayList<User>();
			uList3.add(u3);

			initializeTest(); // re-create all mocks
			when(userRepository.findByUserId(u1.getUserId())).thenReturn(uList);
			when(userRepository.findByUserId(u2.getUserId())).thenReturn(new ArrayList<>());
			when(userRepository.findByUserId(u3.getUserId())).thenReturn(uList3);
<<<<<<< HEAD
			when(userRepository.findByUserId(999)).thenReturn(new ArrayList<User>());
=======
			when(userRepository.save(Mockito.any(User.class))).thenAnswer(new Answer<User>() {
				@Override
				public User answer(InvocationOnMock invocation) throws Throwable {
					return (User) (invocation.getArguments()[0]);
				}				
			});
>>>>>>> 28d357f1b3aad6a614b9118a3fec080ea0c27e4b
		}
		
		@Test 
		public void testMaxValueIncrease() {
			try {
				assertEquals(u1.getReputation(), service.increaseUserReputation(u1.getUserId()));
			} catch(InvalidUserException e) {
				fail(e.getMessage());
			}			
		}
		
		@Test
		public void testIncrease() {
			try {
				assertEquals((u3.getReputation()+1), service.increaseUserReputation(u3.getUserId()));
			} catch(InvalidUserException e) {
				fail(e.getMessage());
			}
		}

		@Test
		public void testInvalidUserException() {
			try {
				service.increaseUserReputation(u2.getUserId());
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {
			}
		}
		
		@Test
		public void nonExistingId() {
			try {
<<<<<<< HEAD
				service.increaseUserReputation(999);
				fail("Non existing id should throw an InvalidGasStationException");
=======
				UserDto ud1 = service.getUserById(999);
				assertNull(ud1, "User should be null");
>>>>>>> 28d357f1b3aad6a614b9118a3fec080ea0c27e4b
			} catch (InvalidUserException e) {
				
			}
			
		}
	}
	
	@Nested
	@DisplayName("Test for decrease reputation")
	public class DecreaseReputation {

		User u1;
		User u2;
		User u3;
		
		@BeforeEach
		void setUp() {
			u1 = new User("Giacomo", "ilnonno", "giacomo.poretti@agg.it", -5);
			u1.setUserId(1);
			u1.setAdmin(true);
			u2 = new User("Giovanni", "franco", "giovanni.storti@agg.it", +1);
			u2.setUserId(-2);
			u2.setAdmin(false);		
			u3 = new User("Giacomo", "ilnonno", "giacomo3.poretti@agg.it", +2);
			u3.setUserId(3);
			u3.setAdmin(true);
			List<User> uList = new ArrayList<User>();
			uList.add(u1);
			List<User> uList2 = new ArrayList<User>();
			uList2.add(u2);
			List<User> uList3 = new ArrayList<User>();
			uList3.add(u3);

			initializeTest(); // re-create all mocks
			when(userRepository.findByUserId(u1.getUserId())).thenReturn(uList);
			when(userRepository.findByUserId(u2.getUserId())).thenReturn(new ArrayList<>());
			when(userRepository.findByUserId(u3.getUserId())).thenReturn(uList3);
			when(userRepository.save(Mockito.any(User.class))).thenAnswer(new Answer<User>() {
				@Override
				public User answer(InvocationOnMock invocation) throws Throwable {
					return (User) (invocation.getArguments()[0]);
				}				
			});
		}
		
		@Test
		public void testDecrease() {
			u2.setUserId(2);
			try {
				assertEquals((u3.getReputation()-1), service.decreaseUserReputation(u3.getUserId()));
			} catch(InvalidUserException e) {
				fail(e.getMessage());
			}
		}
		
		@Test 
		public void testMinValueDecrease() {
			try {
				assertEquals(u1.getReputation(), service.decreaseUserReputation(u1.getUserId()));
			} catch(InvalidUserException e) {
				fail(e.getMessage());
			}
		}

		@Test
		public void testInvalidUserException() {
			try {
				service.decreaseUserReputation(u2.getUserId());
				fail("Negative id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {
			}
		}
		
		@Test
		public void nonExistingId() {
			try {
				service.decreaseUserReputation(999);
				fail("Non existing id should throw an InvalidGasStationException");
			} catch (InvalidUserException e) {}
		}
	}
}
