package it.polito.ezgas.controllertests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
	HomeControllerTests.class,
	GasStationControllerTests.class,
	UserControllerTests.class,
})

public class TestController {

}



