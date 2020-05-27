package it.polito.ezgas.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.service.UserService;
import net.minidev.json.JSONObject;

@WebMvcTest(UserController.class)
public class UserControllerTests {
	@Autowired
	private MockMvc mvc;
	
    @MockBean
    private UserService service;
    
    private int initSize;
    private List<User> myList;
	
    @BeforeEach
	public void setUp() throws Exception {
		List<UserDto> list = this.service.getAllUsers();
			
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
		/*List<UserDto> myList = this.myList.stream()
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
			});*/
	}
	
	@Test
	public void testSaveUser() throws Exception {
	}
	
	@Test
	public void testSaveUserError() throws Exception {
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		for(User user : this.myList)
			this.service.deleteUser(user.getUserId());
	}

}