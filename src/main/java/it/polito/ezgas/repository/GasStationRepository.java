package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer>{
	
	public List<GasStation> findByGasStationId(Integer gasStationId);
	public List<GasStation> findByHasMethaneTrue();
	public List<GasStation> findByCarSharing(String carSharing);
	public List<GasStation> findByHasDieselTrue();
	public List<GasStation> findByHasSuperTrue();
	public List<GasStation> findByHasSuperPlusTrue();
	public List<GasStation> findByHasGasTrue();
	
	@Query(value = "SELECT gasStationId, gasStationName, gasStationAddress, hasDiesel, hasSuper, hasSuperPlus, hasGas, hasMethane, carSharing, "
			+ "lat, lon, dieselPrice, superPrice, superPlusPrice, gasPrice, methanePrice, reportUser, reportTimestamp, reportDependability"
			+ " FROM GasStation WHERE (lat - ?1) <= 1000/1.41 AND (lon - ?2) <= 1000/1.41")
	public List<Object> findByLatAndLon(Double lat, Double lon);
}
