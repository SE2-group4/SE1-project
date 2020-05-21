package it.polito.ezgas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.GasStation;

@Repository
public interface GasStationRepository extends JpaRepository<GasStation, Integer>{
	
	public Optional<GasStation> findByGasStationId(int gasStationId);
	public List<GasStation> findByHasMethaneTrueOrderByMethanePriceDesc();
	public List<GasStation> findByCarSharing(String carSharing);
	public List<GasStation> findByHasDieselTrueOrderByDieselPriceDesc();
	public List<GasStation> findByHasSuperTrueOrderBySuperPriceDesc();
	public List<GasStation> findByHasSuperPlusTrueOrderBySuperPlusPriceDesc();
	public List<GasStation> findByHasGasTrueOrderByGasPriceDesc();
	
}
