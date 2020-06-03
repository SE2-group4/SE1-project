package it.polito.ezgas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
import it.polito.ezgas.utils.Utility;

@Service
public class GasStationServiceimpl implements GasStationService {

	private GasStationRepository gasStationRepository;

	private UserRepository userRepository;

	public GasStationServiceimpl(GasStationRepository gasStationRepository, UserRepository userRepository) {
		this.gasStationRepository = gasStationRepository;
		this.userRepository = userRepository;
	}

	@Override
	public GasStationDto getGasStationById(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}

		Optional<GasStation> gasStationOpt = this.gasStationRepository.findByGasStationId(gasStationId);
		GasStation gasStation = gasStationOpt.isPresent() ? gasStationOpt.get() : null;

		if (gasStation != null && gasStation.getReportTimestamp() != null) {
			gasStation.setReportDependability(
					Utility.trustCalculation(gasStation.getReportDependability(), gasStation.getReportTimestamp()));
		}
		
		return (gasStation != null) ? GasStationConverter.GasStationConvertToGasStationDto(gasStation) : null;
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

		if (!Utility.checkCoordinates(gasStation.getLat(), gasStation.getLon()))
			throw new GPSDataException("Coordinates error");

		if (gasStation.getHasMethane()) {
			if (gasStation.getMethanePrice() == -1)
				gasStation.setMethanePrice(0);
		} else
			gasStation.setMethanePrice(-1);

		if (gasStation.getHasSuperPlus()) {
			if (gasStation.getSuperPlusPrice() == -1)
				gasStation.setSuperPlusPrice(0);
		} else
			gasStation.setSuperPlusPrice(-1);

		if (gasStation.getHasSuper()) {
			if (gasStation.getSuperPrice() == -1)
				gasStation.setSuperPrice(0);
		} else
			gasStation.setSuperPrice(-1);

		if (gasStation.getHasGas()) {
			if (gasStation.getGasPrice() == -1)
				gasStation.setGasPrice(0);
		} else
			gasStation.setGasPrice(-1);

		if (gasStation.getHasDiesel()) {
			if (gasStation.getDieselPrice() == -1)
				gasStation.setDieselPrice(0);
		} else
			gasStation.setDieselPrice(-1);

		List<User> userList = this.userRepository.findByUserId(gasStation.getReportUser());
		if (!userList.isEmpty() && gasStation.getGasStationId() != null) {
			Optional<GasStation> currentGasStation = gasStationRepository.findByGasStationId(gasStation.getGasStationId());
			gasStation.setReportDependability(currentGasStation.get().getReportDependability());
			gasStation.setUser(userList.get(0));
		}

		g = this.gasStationRepository.save(gasStation);
		if (g.getReportTimestamp() != null) {
			g.setReportDependability(
					Utility.trustCalculation(g.getReportDependability(), g.getReportTimestamp()));
		}
		return GasStationConverter.GasStationConvertToGasStationDto(g);
	}

	@Override
	public List<GasStationDto> getAllGasStations() {
		List<GasStation> gasStationList = this.gasStationRepository.findAll();

		gasStationList.forEach(gs -> {
			if (gs.getReportTimestamp() != null)
				gs.setReportDependability(
						Utility.trustCalculation(gs.getReportDependability(), gs.getReportTimestamp()));
		});

		return gasStationList.stream().map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs))
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deleteGasStation(Integer gasStationId) throws InvalidGasStationException {
		if (gasStationId == null || gasStationId < 0 ) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}
		
		this.gasStationRepository.delete(gasStationId);
			
		return !(this.gasStationRepository.findByGasStationId(gasStationId).isPresent());
	}

	@Override
	public List<GasStationDto> getGasStationsByGasolineType(String gasolinetype) throws InvalidGasTypeException {
		List<GasStation> gasStationList = new ArrayList<GasStation>();
		if (gasolinetype == null || gasolinetype.equals("null") || gasolinetype.equals(""))
			throw new InvalidGasTypeException("gasoline is null or empty");

		switch (gasolinetype) {
		case "Diesel":
			gasStationList = this.gasStationRepository.findByHasDieselTrueOrderByDieselPriceAsc();
			break;
		case "Super":
			gasStationList = this.gasStationRepository.findByHasSuperTrueOrderBySuperPriceAsc();
			break;
		case "SuperPlus":
			gasStationList = this.gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPriceAsc();
			break;
		case "Gas":
			gasStationList = this.gasStationRepository.findByHasGasTrueOrderByGasPriceAsc();
			break;
		case "Methane":
			gasStationList = this.gasStationRepository.findByHasMethaneTrueOrderByMethanePriceAsc();
			break;
		default:
			throw new InvalidGasTypeException("Wrong gasoline type.");
		}

		gasStationList.forEach(gs -> {
			if (gs.getReportTimestamp() != null)
				gs.setReportDependability(
						Utility.trustCalculation(gs.getReportDependability(), gs.getReportTimestamp()));
		});

		return gasStationList.stream().map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs))
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithCoordinates(double lat, double lon, String gasolinetype,
			String carsharing) throws InvalidGasTypeException, GPSDataException {
		if (!Utility.checkCoordinates(lat, lon))
			throw new GPSDataException("coordinates error");

		if (gasolinetype.toLowerCase().compareTo("") == 0)
			throw new InvalidGasTypeException("Invalid gas type");

		List<GasStationDto> gasStationList = new ArrayList<GasStationDto>();

		if (!gasolinetype.equals("null") && !gasolinetype.equals("Select gasoline type")
				&& !carsharing.equals("null")) {
			gasStationList = getGasStationsWithoutCoordinates(gasolinetype, carsharing);
		} else if (!carsharing.equals("null")) {
			gasStationList = getGasStationByCarSharing(carsharing);
		} else if (!gasolinetype.equals("null") && !gasolinetype.equals("Select gasoline type")) {
			gasStationList = getGasStationsByGasolineType(gasolinetype);
		} else {
			gasStationList = getAllGasStations();
		}

		return gasStationList.stream()
				.filter(gs -> Utility.calculateDistanceInMeters(gs.getLat(), gs.getLon(), lat, lon) < 1000)
				.sorted((gs1, gs2) -> Double.compare(
						Utility.calculateDistanceInMeters(gs1.getLat(), gs1.getLon(), lat, lon),
						Utility.calculateDistanceInMeters(gs2.getLat(), gs2.getLon(), lat, lon)))
				.collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsWithoutCoordinates(String gasolinetype, String carsharing)
			throws InvalidGasTypeException {
		return getGasStationsByGasolineType(gasolinetype).stream().filter(gs -> gs.getCarSharing().equals(carsharing))
				.collect(Collectors.toList());
	}

	@Override
	public void setReport(Integer gasStationId, double dieselPrice, double superPrice, double superPlusPrice,
			double gasPrice, double methanePrice, Integer userId)
			throws InvalidGasStationException, PriceException, InvalidUserException {

		if (userId == null || userId < 0) {
			throw new InvalidUserException("Invalid (negative) userId");
		}
		if (gasStationId == null || gasStationId < 0) {
			throw new InvalidGasStationException("Invalid (negative) gasStationId");
		}

		Optional<GasStation> gasStationOpt = this.gasStationRepository.findByGasStationId(gasStationId);

		if (!gasStationOpt.isPresent()) {
			throw new InvalidGasStationException("GasStation not present");
		}

		GasStation gasStation = gasStationOpt.get();
		List<User> uList = userRepository.findByUserId(userId);
		if(uList.size() != 1)
			throw new InvalidUserException("User not present");
		User user = uList.get(0);

		/*
		if ((gasStation.getMethanePrice() != -1 && gasStation.getMethanePrice() < 0)
				|| (gasStation.getSuperPlusPrice() != -1 && gasStation.getSuperPlusPrice() < 0)
				|| (gasStation.getSuperPrice() != -1 && gasStation.getSuperPrice() < 0)
				|| (gasStation.getGasPrice() != -1 && gasStation.getGasPrice() < 0)
				|| (gasStation.getDieselPrice() != -1 && gasStation.getDieselPrice() < 0)) {
			throw new PriceException("Invalid (negative) price");
		}
		*/
		
		if ((gasStation.getHasMethane() && methanePrice < 0)
				|| (gasStation.getHasSuperPlus() && superPlusPrice < 0)
				|| (gasStation.getHasSuper() && superPrice < 0)
				|| (gasStation.getHasGas() && gasPrice < 0)
				|| (gasStation.getHasDiesel() && dieselPrice < 0)) {
			throw new PriceException("Invalid (negative) price");
		}

		if (gasStation.getHasDiesel())
			gasStation.setDieselPrice(dieselPrice);
		if (gasStation.getHasSuper())
			gasStation.setSuperPrice(superPrice);
		if (gasStation.getHasSuperPlus())
			gasStation.setSuperPlusPrice(superPlusPrice);
		if (gasStation.getHasGas())
		gasStation.setGasPrice(gasPrice);
		if (gasStation.getHasMethane())
		gasStation.setMethanePrice(methanePrice);
		gasStation.setUser(user);
		gasStation.setReportUser(userId);
		gasStation.setReportTimestamp((new Date()).toString());
		gasStation.setReportDependability(user.getReputation());
		gasStationRepository.save(gasStation);
	}

	@Override
	public List<GasStationDto> getGasStationByCarSharing(String carSharing) {
		return this.gasStationRepository.findAll().stream().filter(gs -> gs.getCarSharing().equals(carSharing))
				.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}

	@Override
	public List<GasStationDto> getGasStationsByProximity(double lat, double lon) throws GPSDataException {
		if (!Utility.checkCoordinates(lat, lon))
			throw new GPSDataException("coordinates error");

		return this.gasStationRepository.findAll().stream()
				.filter(gs -> Utility.calculateDistanceInMeters(gs.getLat(), gs.getLon(), lat, lon) < 1000)
				.sorted((gs1, gs2) -> Double.compare(
						Utility.calculateDistanceInMeters(gs1.getLat(), gs1.getLon(), lat, lon),
						Utility.calculateDistanceInMeters(gs2.getLat(), gs2.getLon(), lat, lon)))
				.map(gs -> GasStationConverter.GasStationConvertToGasStationDto(gs)).collect(Collectors.toList());
	}
}
