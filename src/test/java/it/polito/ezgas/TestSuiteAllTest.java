package it.polito.ezgas;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import it.polito.ezgas.converter.*;
import it.polito.ezgas.dto.GasStationDtoTests;
import it.polito.ezgas.dto.UserDtoTests;
import it.polito.ezgas.entity.*;
import it.polito.ezgas.repository.GasStationRepositoryTest;
import it.polito.ezgas.repository.UserRepositoryTests;
import it.polito.ezgas.service.*;
import it.polito.ezgas.utils.*;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	GasStationTest.class, 
	UserTests.class, 
	GasStationDtoTests.class, 
	UserDtoTests.class,
	UtilityTest.class,
	
	GasStationRepositoryTest.class, 
	UserRepositoryTests.class,
	GasStationConverterTest.class, 
	UserConverterTests.class,
	
	GasStationServiceTest.class, 
	UserServiceTests.class,
})
public class TestSuiteAllTest {

}
