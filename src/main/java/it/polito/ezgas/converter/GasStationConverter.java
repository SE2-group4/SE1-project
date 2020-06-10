package it.polito.ezgas.converter;

import org.springframework.beans.BeanUtils;

import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;

public class GasStationConverter {

	public static GasStationDto GasStationConvertToGasStationDto(GasStation gasStation) {
		
		GasStationDto gasStationDto = new GasStationDto();
		BeanUtils.copyProperties(gasStation, gasStationDto);
		/*
		GasStationDto gasStationDto = new GasStationDto();
		User user = gasStation.getUser();
		UserDto userDto = UserConverter.userConvertToUserDto(user);
		
		gasStationDto.setGasStationId(gasStation.getGasStationId());
		gasStationDto.setGasStationName(gasStation.getGasStationName());
		gasStationDto.setGasStationAddress(gasStation.getGasStationAddress());
		gasStationDto.setHasDiesel(gasStation.getHasDiesel());
		gasStationDto.setHasSuper(gasStation.getHasSuper());
		gasStationDto.setHasSuperPlus(gasStation.getHasSuperPlus());
		gasStationDto.setHasGas(gasStation.getHasGas());
		gasStationDto.setHasMethane(gasStation.getHasMethane());
		gasStationDto.setCarSharing(gasStation.getCarSharing());
		gasStationDto.setLat(gasStation.getLat());
		gasStationDto.setLon(gasStation.getLon());
		gasStationDto.setDieselPrice(gasStation.getDieselPrice());
		gasStationDto.setSuperPrice(gasStation.getSuperPrice());
		gasStationDto.setSuperPlusPrice(gasStation.getSuperPlusPrice());
		gasStationDto.setGasPrice(gasStation.getGasPrice());
		gasStationDto.setMethanePrice(gasStation.getMethanePrice());
		gasStationDto.setReportUser(gasStation.getReportUser());
		gasStationDto.setReportDependability(gasStation.getReportDependability());
		gasStationDto.setReportTimestamp(gasStation.getReportTimestamp());
		gasStationDto.setUserDto(userDto);
		*/
		if(gasStation.getUser() != null) {
			gasStationDto.setUserDto(UserConverter.userConvertToUserDto(gasStation.getUser()));
		}
		
		return gasStationDto;
		
	}

public static GasStation GasStationDtoConvertToGasStation(GasStationDto gasStationDto) {
	
	GasStation gasStation = new GasStation();
	BeanUtils.copyProperties(gasStationDto, gasStation);
	/*
	UserDto userDto = gasStationDto.getUserDto();
	User user = UserConverter.userDtoConvertToUser(userDto);
	GasStation gasStation = new GasStation();
	gasStation.setGasStationId(gasStationDto.getGasStationId());
	gasStation.setGasStationName(gasStationDto.getGasStationName());
	gasStation.setGasStationAddress(gasStationDto.getGasStationAddress());
	gasStation.setHasDiesel(gasStationDto.getHasDiesel());
	gasStation.setHasSuper(gasStationDto.getHasSuper());
	gasStation.setHasSuperPlus(gasStationDto.getHasSuperPlus());
	gasStation.setHasGas(gasStationDto.getHasGas());
	gasStation.setHasMethane(gasStationDto.getHasMethane());
	gasStation.setCarSharing(gasStationDto.getCarSharing());
	gasStation.setLat(gasStationDto.getLat());
	gasStation.setLon(gasStationDto.getLon());
	gasStation.setDieselPrice(gasStationDto.getDieselPrice());
	gasStation.setSuperPrice(gasStationDto.getSuperPrice());
	gasStation.setSuperPlusPrice(gasStationDto.getSuperPlusPrice());
	gasStation.setGasPrice(gasStationDto.getGasPrice());
	gasStation.setMethanePrice(gasStationDto.getMethanePrice());
	gasStation.setReportUser(gasStationDto.getReportUser());
	gasStation.setReportDependability(gasStationDto.getReportDependability());
	gasStation.setReportTimestamp(gasStationDto.getReportTimestamp());
	gasStation.setUser(user);
	*/
	if(gasStationDto.getUserDto() != null) {
		gasStation.setUser(UserConverter.userDtoConvertToUser(gasStationDto.getUserDto()));
	}
	
	return gasStation;
	
	}	
}
