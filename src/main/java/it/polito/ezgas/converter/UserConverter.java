package it.polito.ezgas.converter;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	
	public static UserDto userConvertToUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setReputation(user.getReputation());
		userDto.setAdmin(user.getAdmin());
		return userDto;
		
	}
	
	public static User userDtoConvertToUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setReputation(userDto.getReputation());
		user.setAdmin(userDto.getAdmin());
		return user;
	}
}
