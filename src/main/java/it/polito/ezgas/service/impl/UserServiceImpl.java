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
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		User user;
		try {
			user = this.userRepository.findByUserId(userId).get(0);
			return UserConverter.userConvertToUserDto(user);
		}
		catch(Exception e) {
			throw new InvalidUserException(e.getMessage());
		}
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = this.userRepository.save(UserConverter.userDtoConvertToUser(userDto));
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
		boolean isDeleted = false;
		try {
			this.userRepository.delete(userId);
			isDeleted = true;
		}
		catch(Exception e) {
			isDeleted = false;
			throw new InvalidUserException(e.getMessage());
		}
		return isDeleted;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {
		LoginDto userLogin;
		try {
			// TODO Auto-generated method stub
			User user = this.userRepository.findByEmailAndPassword(credentials.getUser(), credentials.getPw()).get(0);
			userLogin = new LoginDto(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), user.getReputation());
			userLogin.setAdmin(user.getAdmin());
		}
		catch(Exception e) {
			throw new InvalidLoginDataException(e.getMessage());
		}
		return userLogin;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub	
		try {
			User user = this.userRepository.findByUserId(userId).get(0);
			if(user.getReputation() < 5 && user.getReputation() >= -5) {
				System.out.println("Sei un grande!");
				Integer new_reputation = user.getReputation()+1;
				user.setReputation(new_reputation);
				this.userRepository.save(user);
				return new_reputation;
			} else {
				return 5;
			}
		} catch(Exception e) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {
		// TODO Auto-generated method stub
		try {
			User user = this.userRepository.findByUserId(userId).get(0);
			if(user.getReputation() <= 5 && user.getReputation() > -5) {
				Integer new_reputation = user.getReputation()-1;
				user.setReputation(new_reputation);
				this.userRepository.save(user);
				return new_reputation;
			} else {
				return -5;
			}
		} catch(Exception e) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
	}
}
