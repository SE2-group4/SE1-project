package it.polito.ezgas.user;

import org.junit.platform.suite.api.SelectClasses;

@SelectClasses({
	UserControllerTests.class,
	UserConverterTests.class,
	UserDtoTests.class,
	UserServiceTests.class,
	UserTests.class,
	OtherTests.class
})
public class AllUserTests {
}
