package it.polito.ezgas;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import it.polito.ezgas.converter.*;
import it.polito.ezgas.entity.*;
import it.polito.ezgas.service.*;
import it.polito.ezgas.utils.*;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	EZGasApplicationTests.class,
	GasStationTest.class, UserTests.class, UtilityTest.class,
	GasStationServiceTest.class, UserServiceTests.class,
	GasStationConverterTest.class, UserConverterTests.class})
public class TestSuite {

}
