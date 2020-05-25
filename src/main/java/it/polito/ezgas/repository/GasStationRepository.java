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
	public List<GasStation> findByHasMethaneTrueOrderByMethanePriceAsc();
	public List<GasStation> findByCarSharing(String carSharing);
	public List<GasStation> findByHasDieselTrueOrderByDieselPriceAsc();
	public List<GasStation> findByHasSuperTrueOrderBySuperPriceAsc();
	public List<GasStation> findByHasSuperPlusTrueOrderBySuperPlusPriceAsc();
	public List<GasStation> findByHasGasTrueOrderByGasPriceAsc();
	
}
