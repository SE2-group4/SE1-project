package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		if (userId < 0) {
			throw new InvalidUserException("Invalid (negative) userId");
		}

		List<User> userList;
		try {
			userList = this.userRepository.findByUserId(userId);
			if (userList.size() > 0)
				return UserConverter.userConvertToUserDto(userList.get(0));
			return null;
		} catch (Exception e) {
			throw new InvalidUserException(e.getMessage());
		}
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User newUser = UserConverter.userDtoConvertToUser(userDto);

		List<User> uList = this.userRepository.findAll().stream().filter(u -> u.getEmail().equals(newUser.getEmail()))
				.collect(Collectors.toList());
		if (uList.size() != 0) {
			User oldUser = uList.get(0);
			newUser.setUserId(oldUser.getUserId());
		} else
			newUser.setReputation(0);

		User user = this.userRepository.save(newUser);
		return UserConverter.userConvertToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepository.findAll();
		List<UserDto> users_dto = new ArrayList<>();
		for (int i = 0; i < users.size(); i++) {
			users_dto.add(UserConverter.userConvertToUserDto(users.get(i)));
		}
		return users_dto;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		if(userId < 0)
			throw new InvalidUserException("Invalid userId (less than 0)");
		List<User> users;
		
		try {
			this.userRepository.delete(userId);
			users = this.userRepository.findByUserId(userId);
			if(users.size() > 0)
				return false;
			return true;
		} catch (Exception e) {
			throw new InvalidUserException(e.getMessage());
		}
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto userLogin;
		try {
			// TODO Auto-generated method stub
			User user = this.userRepository.findByEmailAndPassword(credentials.getUser(), credentials.getPw()).get(0);
			userLogin = new LoginDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(),
					user.getReputation());
			userLogin.setAdmin(user.getAdmin());
		} catch (Exception e) {
			throw new InvalidLoginDataException(e.getMessage());
		}
		return userLogin;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		List<User> userList;
		User user;

		userList = this.userRepository.findByUserId(userId);
		if (userList.size() == 0)
			throw new InvalidUserException("No user with userId " + userId + " registered");

		user = userList.get(0);
		if (user.getReputation() < 5 && user.getReputation() >= -5) {
			Integer new_reputation = user.getReputation() + 1;
			user.setReputation(new_reputation);
			user = this.userRepository.save(user);
			return user.getReputation();
		} else {
			return 5;
		}
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		List<User> userList;
		User user;

		userList = this.userRepository.findByUserId(userId);
		if (userList.size() == 0)
			throw new InvalidUserException("No user with userId " + userId + " registered");

		user = userList.get(0);
		
		if (user.getReputation() <= 5 && user.getReputation() > -5) {
			Integer new_reputation = user.getReputation() - 1;
			user.setReputation(new_reputation);
			user = this.userRepository.save(user);
			return user.getReputation();
		} else {
			return -5;
		}

	}
}
