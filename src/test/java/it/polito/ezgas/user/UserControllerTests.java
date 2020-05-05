package it.polito.ezgas.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import exception.InvalidUserException;
import it.polito.ezgas.controller.UserController;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.service.UserService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
	@Autowired
	private MockMvc mvc;
	
    @MockBean
    private UserService service;
    
    private int initSize;
    private List<User> myList;
    
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
		User user;

		user = new User();
		user.setUserId(1);
		user.setUserName("Aldo");
		user.setPassword("buonaquestacadrega");
		user.setEmail("aldo.baglio@agg.it");
		user.setReputation(-3);
		user.setAdmin(false);
		this.myList.add(user);

		user = new User();
		user.setUserId(2);
		user.setUserName("Giovanni");
		user.setPassword("franco");
		user.setEmail("giovanni.storti@agg.it");
		user.setReputation(+5);
		user.setAdmin(true);
		this.myList.add(user);

		user = new User();
		user.setUserId(3);
		user.setUserName("Giacomo");
		user.setPassword("ilnonno");
		user.setEmail("giacomo.poretti@agg.it");
		user.setReputation(+1);
		user.setAdmin(false);
		this.myList.add(user);
		
		/*
		myList.add(new UserDto(1, "Aldo", "buonaquestacadrega", "aldo.baglio@agg.it", -3, false));
		myList.add(new UserDto(2, "Giovanni", "franco", "giovanni.storti@agg.it", +5, true));
		myList.add(new UserDto(3, "Giacomo", "ilnonno", "giacomo.poretti@agg.it", +1, false));
		*/
	}
	
	@Test
	public void testGetUsers() throws Exception {
		List<UserDto> myList = this.myList.stream()
				.map(user -> UserConverter.userConvertToUserDto(user))
				.collect(Collectors.toList());
		
		this.mvc.perform(get("/users"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(res -> {
				assertTrue(res.getResponse().getContentType() == MediaType.APPLICATION_JSON.toString());
			})
			.andExpect(res -> {
				Gson gson = new Gson();
				List<UserDto> list = gson.fromJson(res.getResponse().getContentAsString(),
						new TypeToken<List<UserDto>>() {}.getType());
				
				assertTrue(list.size() == 3);
			});
	}
	
	@Test
	public void testSaveUser() throws Exception {
		// UserDto user = new UserDto(4, "Silvana", "worstactress", "silvana.fallisi@agg.it", 5);
		
		JSONObject json = new JSONObject();
		json.put("id", 4);
		json.put("userName", "Silvana");
		json.put("password", "worstactress");
		json.put("email", "silvana.fallisi@agg.it");
		json.put("reputation", 5);
		
		this.mvc.perform(post("/saveuser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
			.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void testSaveUserError() throws Exception {
		JSONObject json = new JSONObject();
		json.put("id", 4);
		json.put("userName", "Silvana");
		json.put("password", "worstactress");
		/* email is missing */
		json.put("reputation", 5);
		
		this.mvc.perform(post("/saveuser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
			.andExpect(status().is4xxClientError());
		
		json = new JSONObject();
		json.put("id", 4);
		/* userName is missing */
		json.put("password", "worstactress");
		json.put("email", "silvana.fallisi@agg.it");
		json.put("reputation", 5);
		
		this.mvc.perform(post("/saveuser")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()))
			.andExpect(status().is4xxClientError());
	}
	
	@After
	public void tearDown() throws Exception {
		for(User user : this.myList)
			this.service.deleteUser(user.getUserId());
	}

}
