package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		User user;
		
		if(userId >= 0) {
			user = this.userRepository.findByUserId(userId).get(0);
			return UserConverter.userConvertToUserDto(user);
		} else {
			throw new InvalidUserException("Invalid (negative) userId");
		}
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.userRepository.save(UserConverter.userDtoConvertToUser(userDto));
		System.out.println(user.getReputation());
		return UserConverter.userConvertToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepository.findAll();
		List<UserDto> users_dto = new ArrayList<>();
		for(int i = 0; i < users.size(); i++) {
			users_dto.add(UserConverter.userConvertToUserDto(users.get(i)));
		}
		return users_dto;
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		if(userId < 0) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
		this.userRepository.delete(userId);
		if(this.userRepository.findByUserId(userId).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto userLogin;
		if(credentials.getUser().compareTo("") != 0 || credentials.getPw().compareTo("") != 0) {
			// TODO Auto-generated method stub
			User user = this.userRepository.findByEmailAndPassword(credentials.getUser(), credentials.getPw()).get(0);
			userLogin = new LoginDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getReputation());
			userLogin.setAdmin(user.getAdmin());
		}
		else {
			throw new InvalidLoginDataException("Username or password is empty.");
		}
		return userLogin;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub	
		if(userId >= 0) {
			User user = this.userRepository.findByUserId(userId).get(0);
			if(user.getReputation() < 5 && user.getReputation() >= -5) {
				Integer new_reputation = user.getReputation()+1;
				user.setReputation(new_reputation);
				this.userRepository.save(user);
				return new_reputation;
			} else {
				return 5;
			}
		} else {
			throw new InvalidUserException("Invalid (negative) userId");
		}
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		if(userId >= 0) {
			User user = this.userRepository.findByUserId(userId).get(0);
			if(user.getReputation() <= 5 && user.getReputation() > -5) {
				Integer new_reputation = user.getReputation()-1;
				user.setReputation(new_reputation);
				this.userRepository.save(user);
				return new_reputation;
			} else {
				return -5;
			}
		} else {
			throw new InvalidUserException("Invalid (negative) userId");
		}
	}
}
