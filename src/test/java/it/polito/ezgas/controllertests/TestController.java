package it.polito.ezgas.controllertests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	UserControllerTests.class,
	GasStationControllerTests.class,
	HomeControllerTests.class,
})

public class TestController {

}



