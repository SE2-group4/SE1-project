package it.polito.ezgas.user;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import exception.InvalidUserException;
import it.polito.ezgas.controller.UserController;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
	@Autowired
	private MockMvc mvc;
	
    @MockBean
    private UserService service;
    
    private int initSize;
    private List<UserDto> myList;
    
    public UserControllerTests() throws Exception {
		List<UserDto> list = this.service.getAllUsers();
			
		this.initSize = list.size();
		
		/*given(this.service.getAllUsers()).willReturn(list);
		this.mvc.perform(get("URL").contentType(MediaType.APPLICATION_JSON)).andReturn();
		
		assertTrue(true);*/
    }
	
	@Before
	public void setUp() throws Exception {
		this.myList = new ArrayList<>();

		myList.add(new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, false));
		myList.add(new UserDto(2, "Giovanni", "franco", "giovanni.storti@agg.it", +5, true));
		myList.add(new UserDto(3, "Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1, false));
	}
	
	@After
	public void tearDown() throws Exception {
		for(UserDto user : this.myList)
			this.service.deleteUser(user.getUserId());
	}

}
