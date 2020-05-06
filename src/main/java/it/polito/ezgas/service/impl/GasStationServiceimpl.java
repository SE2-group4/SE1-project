package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.GPSDataException;
import exception.InvalidGasStationException;
import exception.InvalidGasTypeException;
import exception.InvalidUserException;
import exception.PriceException;
import it.polito.ezgas.converter.GasStationConverter;
import it.polito.ezgas.dto.GasStationDto;
import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.GasStationRepository;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.GasStationService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class GasStationServiceimpl implements GasStationService {
	
	@Autowired 
	GasStationRepository gasStationRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		GasStation gasStation = this.gasStationRepository.findByGasStationId(gasStationId).get(0);
		return GasStationConverter.GasStationConvertToGasStationDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStation gasStation = this.gasStationRepository.save(GasStationConverter.GasStationDtoConvertToGasStation(gasStationDto));
		System.out.println(gasStation);
		return GasStationConverter.GasStationConvertToGasStationDto(gasStation);
	}
	
	@Override
	public List<GasStationDto> getAllGasStations() {
		List<GasStation> gasStations = this.gasStationRepository.findAll();
		List<GasStationDto> gasStations_dto = new ArrayList<>();
		for(GasStation g : gasStations) {
			gasStations_dto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
		}
		return gasStations_dto;
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		boolean isDeleted = false;
		try {
			this.gasStationRepository.delete(gasStationId);
			isDeleted=true;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return isDeleted;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		List<GasStationDto> gasStationDto = new ArrayList<>();
		switch(gasolinetype) {
			case "methane": 
				List<GasStation> gasStationsMethane = this.gasStationRepository.findByHasMethane();
				for(GasStation g : gasStationsMethane) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				return gasStationDto;
			case "super": 
				List<GasStation> gasStationsSuper = this.gasStationRepository.findByHasSuper();
				for(GasStation g : gasStationsSuper) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				return gasStationDto;
			case "superPlus": 
				List<GasStation> gasStationsSuperPlus = this.gasStationRepository.findByHasSuperPlus();
				for(GasStation g : gasStationsSuperPlus) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				return gasStationDto;
			case "gas": 
				List<GasStation> gasStationsGas = this.gasStationRepository.findByHasGas();
				for(GasStation g : gasStationsGas) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				return gasStationDto;
			case "diesel": 
				List<GasStation> gasStationsDiesel = this.gasStationRepository.findByHasDiesel();
				for(GasStation g : gasStationsDiesel) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				return gasStationDto;
		}
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		List<GasStation> gasStationsbyProx = this.gasStationRepository.findAll().stream().filter(g -> g.getLat()-lat<1000).filter(g -> g.getLon()-lon<1000).collect(Collectors.toList());
		List<GasStationDto> gDto = new ArrayList<>();
		for(GasStation g : gasStationsbyProx) {
			gDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));			
		}
		return gDto;
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
		GasStation gasStation = gasStationRepository.findByGasStationId(gasStationId).get(0);
		User user = userRepository.findByUserId(userId).get(0);
		
		gasStation.setDieselPrice(dieselPrice);
		gasStation.setSuperPrice(superPrice);
		gasStation.setSuperPlusPrice(superPlusPrice);
		gasStation.setGasPrice(gasPrice);
		gasStation.setMethanePrice(methanePrice);
		gasStation.setUser(user);		
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		List<GasStation> gasStationsByCarShare = gasStationRepository.findAll().stream().filter(g -> g.getCarSharing().compareTo(carSharing)==0).collect(Collectors.toList());
		List<GasStationDto> gDto = new ArrayList<>();
		for(GasStation g : gasStationsByCarShare) {
			gDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));			
		}
		return gDto;
	}
	
}
