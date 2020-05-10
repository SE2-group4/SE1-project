package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer>{
	
	public List<GasStation> findByGasStationId(Integer gasStationId);
	public List<GasStation> findByHasMethaneTrue();
	public List<GasStation> findByHasDieselTrue();
	public List<GasStation> findByHasSuperTrue();
	public List<GasStation> findByHasSuperPlusTrue();
	public List<GasStation> findByHasGasTrue();
	
}
