package it.polito.ezgas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import it.polito.ezgas.entity.GasStationTest;
import it.polito.ezgas.utils.UtilityTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({ GasStationTest.class, UtilityTest.class })

public class TestSuite {

}
