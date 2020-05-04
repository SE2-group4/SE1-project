package it.polito.ezgas.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import exception.InvalidUserException;
import it.polito.ezgas.controller.UserController;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
	/*
	@Autowired
	private MockMvc mvc;
	*/
 
    @MockBean
    private UserService service;
    
    private int initSize;
    private List<UserDto> myList;
    
    public UserControllerTests() {
		List<UserDto> list = this.service.getAllUsers();
			
		this.initSize = list.size();
    }
	
	@Before
	public void setUp() throws Exception {
		this.myList = new ArrayList<>();

		myList.add(new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, false));
		myList.add(new UserDto(2, "Giovanni", "franco", "giovanni.storti@agg.it", +5, true));
		myList.add(new UserDto(3, "Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1, false));
	}
	
	public int getExpectedSize() {
		return(this.initSize + this.myList.size());
	}
	
	@Test
	public void testGet() {
		List<UserDto> list = this.service.getAllUsers();
		UserDto myUser = this.myList.get(0);
		
		assertEquals(list.size(), this.getExpectedSize());
		
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
		UserDto user = this.myList.remove(2);
		
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
		assertTrue("User reputation should be less then before!", previousReputation < user.getReputation());
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

		previousReputation = user.getReputation();		
		try {
			this.service.increaseUserReputation(id);
		} catch (InvalidUserException e) {
			fail();
		}
		assertTrue("User reputation should be more then before!", previousReputation > user.getReputation());
	}
	
	@After
	public void tearDown() throws Exception {
		for(UserDto user : this.myList)
			this.service.deleteUser(user.getUserId());
	}

}
