package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.Math;
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
		if(gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		GasStation gasStation = this.gasStationRepository.findByGasStationId(gasStationId).get(0);
		return GasStationConverter.GasStationConvertToGasStationDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStation gasStation = GasStationConverter.GasStationDtoConvertToGasStation(gasStationDto);
		if((gasStation.getMethanePrice()!=-1 && gasStation.getMethanePrice()<0) ||
		   (gasStation.getSuperPlusPrice()!=-1 && gasStation.getSuperPlusPrice()<0) ||
		   (gasStation.getSuperPrice()!=-1 && gasStation.getSuperPrice()<0) ||
		   (gasStation.getGasPrice()!=-1 && gasStation.getGasPrice()<0) ||
		   (gasStation.getDieselPrice()!=-1 && gasStation.getDieselPrice()<0)) {
				throw new PriceException("Invalid (negative) price"); 
		}
		if(gasStation.getLat() <-90 || gasStation.getLon() <-180 || 
		   gasStation.getLat() > 90 || gasStation.getLon() > 180) {
			throw new GPSDataException("Latitude and Longitude are invalid");
		}
		if((gasStation.getMethanePrice()==-1) &&
		   (gasStation.getSuperPlusPrice()==-1) &&
		   (gasStation.getSuperPrice()==-1) &&
		   (gasStation.getGasPrice()==-1) &&
		   (gasStation.getDieselPrice()==-1)) {
			if(gasStation.getHasMethane())
				gasStation.setMethanePrice(2);
			if(gasStation.getHasSuperPlus())
				gasStation.setSuperPlusPrice(2);
			if(gasStation.getHasSuper())
				gasStation.setSuperPrice(2);
			if(gasStation.getHasGas())
				gasStation.setGasPrice(2);
			if(gasStation.getHasDiesel())
				gasStation.setDieselPrice(2);
				}
		this.gasStationRepository.save(gasStation);
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
		if(gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		this.gasStationRepository.delete(gasStationId);
		if(this.gasStationRepository.findByGasStationId(gasStationId).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		List<GasStationDto> gasStationDto = new ArrayList<>();
		if(gasolinetype.compareTo("")==0) {
			throw new InvalidGasTypeException("Invalid gas type");
		}
		switch(gasolinetype) {
			case "methane": 
				List<GasStation> gasStationsMethane = this.gasStationRepository.findByHasMethaneTrue();
				for(GasStation g : gasStationsMethane) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				gasStationDto.sort(Comparator.comparing(GasStationDto::getMethanePrice));
				/*gasStationDto.sort(new Comparator<GasStationDto>() {
					@Override
					 public int compare(GasStationDto g1, GasStationDto g2) {
					        return Double.compare(g1.getMethanePrice(), g2.getMethanePrice());
					    }
				});*/
				return gasStationDto;
				
			case "super": 
				List<GasStation> gasStationsSuper = this.gasStationRepository.findByHasSuperTrue();
				for(GasStation g : gasStationsSuper) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				gasStationDto.sort(Comparator.comparing(GasStationDto::getSuperPrice));
				return gasStationDto;
				
			case "superPlus": 
				List<GasStation> gasStationsSuperPlus = this.gasStationRepository.findByHasSuperPlusTrue();
				for(GasStation g : gasStationsSuperPlus) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				gasStationDto.sort(Comparator.comparing(GasStationDto::getSuperPlusPrice));
				return gasStationDto;
				
			case "gas": 
				List<GasStation> gasStationsGas = this.gasStationRepository.findByHasGasTrue();
				for(GasStation g : gasStationsGas) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				gasStationDto.sort(Comparator.comparing(GasStationDto::getGasPrice));
				return gasStationDto;
				
			case "diesel": 
				List<GasStation> gasStationsDiesel = this.gasStationRepository.findByHasDieselTrue();
				for(GasStation g : gasStationsDiesel) {
					gasStationDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));
				}
				gasStationDto.sort(Comparator.comparing(GasStationDto::getDieselPrice));
				return gasStationDto;
		}
		return new ArrayList<>();
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if(lat < -90 || lon < -180 || lat > 90 || lon > 180) {
			throw new GPSDataException("Latitude and Longitude are invalid");
		}
		List<GasStation> gasStationsbyProx = this.gasStationRepository.findAll().stream().filter(g -> Math.abs(g.getLat()-lat)<(Math.sqrt(2)*1000)).filter(g -> Math.abs(g.getLon()-lon)<(Math.sqrt(2)*1000)).collect(Collectors.toList());
		List<GasStationDto> gDto = new ArrayList<>();
		for(GasStation g : gasStationsbyProx) {
			gDto.add(GasStationConverter.GasStationConvertToGasStationDto(g));			
		}
		return gDto;
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if(gasolinetype.compareTo("")==0) {
			throw new InvalidGasTypeException("Invalid gas type");
		}
		if(lat < -90 || lon < -180 || lat > 90 || lon > 180) {
			throw new GPSDataException("Latitude and Longitude are invalid");
		}
		List<GasStationDto> gDtobyProx = getGasStationsByProximity(lat, lon);
		List<GasStationDto> gDtobyGasType = getGasStationsByGasolineType(gasolinetype);
		List<GasStationDto> gDtobyCarShare = getGasStationByCarSharing(carsharing);
		
		gDtobyProx.retainAll(gDtobyGasType);
		gDtobyProx.retainAll(gDtobyCarShare);	
		
		return gDtobyProx;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		if(gasolinetype.compareTo("")==0) {
			throw new InvalidGasTypeException("Invalid gas type");
		}
		List<GasStationDto> gDtobyGasType = getGasStationsByGasolineType(gasolinetype);
		List<GasStationDto> gDtobyCarShare = getGasStationByCarSharing(carsharing);
		
		gDtobyGasType.retainAll(gDtobyCarShare);	
		
		return gDtobyGasType;
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {
						
		if(userId < 0) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
		if(gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		
		GasStation gasStation = gasStationRepository.findByGasStationId(gasStationId).get(0);
		User user = userRepository.findByUserId(userId).get(0);
		
		if((gasStation.getMethanePrice()!=-1 && gasStation.getMethanePrice()<0) ||
		   (gasStation.getSuperPlusPrice()!=-1 && gasStation.getSuperPlusPrice()<0) ||
		   (gasStation.getSuperPrice()!=-1 && gasStation.getSuperPrice()<0) ||
		   (gasStation.getGasPrice()!=-1 && gasStation.getGasPrice()<0) ||
		   (gasStation.getDieselPrice()!=-1 && gasStation.getDieselPrice()<0)) {
				throw new PriceException("Invalid (negative) price"); 
		}
		
		
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
