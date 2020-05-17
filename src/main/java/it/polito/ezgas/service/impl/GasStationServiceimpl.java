package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.lang.Math;
import java.text.SimpleDateFormat;

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


@Service
public class GasStationServiceimpl implements GasStationService {

	@Autowired
	GasStationRepository gasStationRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		GasStation gasStation = this.gasStationRepository.findByGasStationId(gasStationId).get(0);
		return GasStationConverter.GasStationConvertToGasStationDto(gasStation);
	}

	@Override
	public GasStationDto saveGasStation(GasStationDto gasStationDto) throws PriceException, GPSDataException {
		GasStation gasStation = GasStationConverter.GasStationDtoConvertToGasStation(gasStationDto);
		GasStation g = new GasStation();
		if ((gasStation.getMethanePrice() != -1 && gasStation.getMethanePrice() < 0)
				|| (gasStation.getSuperPlusPrice() != -1 && gasStation.getSuperPlusPrice() < 0)
				|| (gasStation.getSuperPrice() != -1 && gasStation.getSuperPrice() < 0)
				|| (gasStation.getGasPrice() != -1 && gasStation.getGasPrice() < 0)
				|| (gasStation.getDieselPrice() != -1 && gasStation.getDieselPrice() < 0)) {
			throw new PriceException("Invalid (negative) price");
		}
		if (gasStation.getLat() < -90 || gasStation.getLon() < -180 || gasStation.getLat() > 90
				|| gasStation.getLon() > 180) {
			throw new GPSDataException("Latitude and Longitude are invalid");
		}
		if ((gasStation.getMethanePrice() == -1) && (gasStation.getSuperPlusPrice() == -1)
				&& (gasStation.getSuperPrice() == -1) && (gasStation.getGasPrice() == -1)
				&& (gasStation.getDieselPrice() == -1)) {
			if (gasStation.getHasMethane())
				gasStation.setMethanePrice(2);
			if (gasStation.getHasSuperPlus())
				gasStation.setSuperPlusPrice(2);
			if (gasStation.getHasSuper())
				gasStation.setSuperPrice(2);
			if (gasStation.getHasGas())
				gasStation.setGasPrice(2);
			if (gasStation.getHasDiesel())
				gasStation.setDieselPrice(2);
			
			g = this.gasStationRepository.save(gasStation);
			
		}else {
			if (gasStation.getHasMethane())
				gasStation.setMethanePrice(2);
			if (gasStation.getHasSuperPlus())
				gasStation.setSuperPlusPrice(2);
			if (gasStation.getHasSuper())
				gasStation.setSuperPrice(2);
			if (gasStation.getHasGas())
				gasStation.setGasPrice(2);
			if (gasStation.getHasDiesel())
				gasStation.setDieselPrice(2);
			
			g = this.gasStationRepository.save(gasStation);			
		}
		
		return GasStationConverter.GasStationConvertToGasStationDto(g);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		List<GasStation> gasStationList = this.gasStationRepository.findAll();
	
		gasStationList.forEach(gs -> {
			if(gs.getReportTimestamp()!= null)
				gs.setReportDependability(trustCalculation(gs.getUser().getReputation(), gs.getReportTimestamp()));
		});
		
		return gasStationList.stream().map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		this.gasStationRepository.delete(gasStationId);
		if (this.gasStationRepository.findByGasStationId(gasStationId).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		return getByGasolineType(gasolinetype).stream()
				.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if (!checkCoordinates(lat, lon))
			throw new GPSDataException("coordinates error");
		if (gasolinetype.toLowerCase().compareTo("") == 0)
			throw new InvalidGasTypeException("Invalid gas type");
		
		List<GasStationDto> gSbyProx = getGasStationsByProximity(lat, lon);
		if(!gasolinetype.equals("null") && !gasolinetype.equals("Select gasoline type")) {
			switch(gasolinetype) {
			      case "Diesel": gSbyProx = gSbyProx.stream().filter(g -> g.getHasDiesel()).collect(Collectors.toList()); break;
			      case "Super": gSbyProx = gSbyProx.stream().filter(g -> g.getHasSuper()).collect(Collectors.toList()); break;
			      case "SuperPlus": gSbyProx = gSbyProx.stream().filter(g -> g.getHasSuperPlus()).collect(Collectors.toList()); break;
			      case "Gas": gSbyProx = gSbyProx.stream().filter(g -> g.getHasGas()).collect(Collectors.toList()); break;
			      case "Methane": gSbyProx = gSbyProx.stream().filter(g -> g.getHasMethane()).collect(Collectors.toList()); break;
			      default: throw new InvalidGasTypeException("Wrong gasoline type.");
			 }
			}
	    if(!carsharing.equals("null")) {
	    	gSbyProx = gSbyProx.stream().filter(g -> g.getCarSharing().equals(carsharing)).collect(Collectors.toList());
	    }
			    return gSbyProx;
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		if (gasolinetype == null || gasolinetype.equals("null") || gasolinetype.equals(""))
			throw new InvalidGasTypeException("gasoline is null or empty");

		return getByGasolineType(gasolinetype).stream().filter(gs -> gs.getCarSharing().equals(carsharing))
				.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {

		if (userId < 0) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
		if (gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}

		GasStation gasStation = gasStationRepository.findByGasStationId(gasStationId).get(0);
		User user = userRepository.findByUserId(userId).get(0);

		if ((gasStation.getMethanePrice() != -1 && gasStation.getMethanePrice() < 0)
				|| (gasStation.getSuperPlusPrice() != -1 && gasStation.getSuperPlusPrice() < 0)
				|| (gasStation.getSuperPrice() != -1 && gasStation.getSuperPrice() < 0)
				|| (gasStation.getGasPrice() != -1 && gasStation.getGasPrice() < 0)
				|| (gasStation.getDieselPrice() != -1 && gasStation.getDieselPrice() < 0)) {
			throw new PriceException("Invalid (negative) price");
		}

		gasStation.setDieselPrice(dieselPrice);
		gasStation.setSuperPrice(superPrice);
		gasStation.setSuperPlusPrice(superPlusPrice);
		gasStation.setGasPrice(gasPrice);
		gasStation.setMethanePrice(methanePrice);
		gasStation.setUser(user);
		gasStation.setReportUser(userId);
		gasStation.setReportTimestamp((new Date()).toString());
		gasStationRepository.save(gasStation);
	}

	
	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		return gasStationRepository.findByCarSharing(carSharing).stream().
				map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}
	
	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if (!checkCoordinates(lat, lon))
			throw new GPSDataException("coordinates error");

		return this.gasStationRepository.findAll().stream()
					.filter(g -> Math.sqrt(Math.pow((Math.abs(g.getLat()-lat)), 2)+Math.pow((Math.abs(g.getLon()-lon)), 2))<(0.008))
					.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}

	public boolean checkCoordinates(double lat, double lon) {
		if (lat < (double) -90 || lon < (double) -180 || lat > (double) 90 || lon > (double) 180)
			return false;
		return true;
	}

	public double trustCalculation(int userRep, String timestamp) {
		double obsolescence;
		double trust;

		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
		Date data = null;
		try {
			data = sdf.parse(timestamp);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Long timestamp_long = data.getTime();		
		Date today = new Date();
		Long today_long = today.getTime();

		if (today_long - timestamp_long > 604800000) { // 7 days
			obsolescence = 0.0;
		} else {
			obsolescence = 1 - (today_long - timestamp_long) / 604800000.0;
		}

		trust = 50 * (userRep + 5) / 10 + 50 * obsolescence;
		return trust;
	}
	
	public List<GasStation> getByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		List<GasStation> gasStationList = new ArrayList<GasStation>();
		if (gasolinetype.toLowerCase().compareTo("") == 0) {
			throw new InvalidGasTypeException("Invalid gas type");
		}
		if (gasolinetype.toLowerCase().compareTo("methane") == 0) {
			gasStationList = this.gasStationRepository
					.findByHasMethaneTrueOrderByMethanePriceDesc();
		}
		if (gasolinetype.toLowerCase().compareTo("super") == 0) {
			gasStationList = this.gasStationRepository.findByHasSuperTrueOrderBySuperPriceDesc();
		}
		if (gasolinetype.toLowerCase().compareTo("superplus") == 0) {
			gasStationList = this.gasStationRepository
					.findByHasSuperPlusTrueOrderBySuperPlusPriceDesc();
		}
		if (gasolinetype.toLowerCase().compareTo("gas") == 0) {
			gasStationList = this.gasStationRepository.findByHasGasTrueOrderByGasPriceDesc();
		}
		if (gasolinetype.toLowerCase().compareTo("diesel") == 0) {
			gasStationList = this.gasStationRepository.findByHasDieselTrueOrderByDieselPriceDesc();
		}
		
		gasStationList.forEach(gs -> {
			if(gs.getReportTimestamp() != null)
			gs.setReportDependability(trustCalculation(gs.getUser().getReputation(), gs.getReportTimestamp()));
		});
		
		return gasStationList;
	}

}
