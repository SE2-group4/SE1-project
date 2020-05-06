package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer>{
	
	public List<GasStation> findByGasStationId(Integer gasStationId);
	public List<GasStation> findByHasMethane();
	public List<GasStation> findByHasDiesel();
	public List<GasStation> findByHasSuper();
	public List<GasStation> findByHasSuperPlus();
	public List<GasStation> findByHasGas();
	
}
