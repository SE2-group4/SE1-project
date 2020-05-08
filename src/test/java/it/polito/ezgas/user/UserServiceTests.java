package it.polito.ezgas.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;
import it.polito.ezgas.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class UserServiceTests {
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
	
	/*
	 * not working...
	 * 
	@Autowired
    private TestEntityManager entityManager;
    */
    
    @Autowired
    private UserService service;   
    
    @MockBean
    private UserRepository userRepository;
    
    private int initSize;
    private List<User> myList;
	
	@Before
	public void setUp() throws Exception {
		List<UserDto> list = this.service.getAllUsers();
			
		this.initSize = list.size();
		
		this.myList = new ArrayList<>();
		User user;

		user = new User();
		//user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(false);
		this.myList.add(user);
		assertTrue(this.service.saveUser(UserConverter.userConvertToUserDto(user)) != null);
		//assertTrue(this.userRepository.save(user) != null);
		//this.entityManager.persist(user);

		user = new User();
		//user.setUserId(2);
		user.setUserName("Giovanni");
		user.setPassword("franco");
		user.setEmail("giovanni.storti@agg.it");
		user.setReputation(+5);
		user.setAdmin(true);
		this.myList.add(user);
		assertTrue(this.userRepository.save(user) != null);
		//this.entityManager.persist(user);

		user = new User();
		//user.setUserId(3);
		user.setUserName("Giacomo");
		user.setPassword("ilnonno");
		user.setEmail("giacomo.poretti@agg.it");
		user.setReputation(+1);
		user.setAdmin(false);
		this.myList.add(user);
		assertTrue(this.userRepository.save(user) != null);
		//this.entityManager.persist(user);
		
		assertEquals(this.getExpectedSize(), this.userRepository.count());
		//this.entityManager.flush();

		/*
		myList.add(new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, false));
		myList.add(new UserDto(2, "Giovanni", "franco", "giovanni.storti@agg.it", +5, true));
		myList.add(new UserDto(3, "Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1, false));
		*/
	}
	
	public int getExpectedSize() {
		return(this.initSize + this.myList.size());
	}
	
	@Test
	public void testUniqueUser(){
		UserDto user = new UserDto(4, "Silvana", "worstactress", "silvana.fallisi@agg.it", 5);
		
		UserDto insertedUser1 = this.service.saveUser(user);
		assertTrue(insertedUser1 != null);
		assertTrue("Init reputation must be 0!", insertedUser1.getReputation() == 0);
		
		user.setUserId(5);
		UserDto insertedUser2 = this.service.saveUser(user);
		assertTrue(insertedUser2 != null);
		assertTrue("Email must be unique!", insertedUser1.getEmail().compareTo(insertedUser2.getEmail()) != 0);
	}
	
	@Test
	public void testGet() {
		List<UserDto> list = this.service.getAllUsers();
		assertTrue(list != null);
		assertEquals(this.getExpectedSize(), list.size());
		
		User myUser = this.myList.get(0);		
		UserDto user = null;
		try {
			user = this.service.getUserById(myUser.getUserId());
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
			this.service.deleteUser(user.getUserId());			
		} catch (InvalidUserException e) {
			fail();
		}
		List<UserDto> list = this.service.getAllUsers();
		
		assertEquals(list.size(), this.getExpectedSize());
	}
	
	public void testDecreaseReputation() {
		int id = this.myList.get(1).getUserId();
		UserDto user = null;
		int previousReputation;
		
		try {
			user = this.service.getUserById(id);
		} catch (InvalidUserException e1) {
			fail();
		}
		
		previousReputation = user.getReputation();
		
		try {
			this.service.decreaseUserReputation(id);
		} catch (InvalidUserException e) {
			fail();
		}
		assertTrue("User reputation should be less then before!", previousReputation >= user.getReputation());
	}
	
	@Test
	public void testIncreaseReputation() {
		int id = this.myList.get(1).getUserId();
		UserDto user = null;
		int previousReputation;
		
		try {
			user = this.service.getUserById(id);
		} catch (InvalidUserException e1) {
			fail();
		}

		assertTrue(user != null);
		previousReputation = user.getReputation();		
		try {
			this.service.increaseUserReputation(id);
		} catch (InvalidUserException e) {
			fail();
		}
		assertTrue("User reputation should be more then before!", user.getReputation() >= previousReputation);
	}
	
	@Test
	public void testGetUserException() {
		try {
			this.service.getUserById(-1);
			fail("InvalidUserException expected!");
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail("InvalidUserException expected!");
		}
	}

	@Test
	public void testDeleteUserException() {
		try {
			this.service.deleteUser(-1);
			fail("InvalidUserException expected!");
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail("InvalidUserException expected!");
		}
	}

	@Test
	public void testDecreaseReputationException() {
		try {
			this.service.decreaseUserReputation(-1);
			fail();
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testIncreaseReputationException() {
		try {
			this.service.increaseUserReputation(-1);
			fail();
		} catch (InvalidUserException e) {}
		catch(Exception e) {
			fail();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		for(User user : this.myList) {
			try {
				this.service.deleteUser(user.getUserId());
			} catch(Exception e) {}
		}
		this.myList.clear();
		
		//this.entityManager.clear();
	}
}
