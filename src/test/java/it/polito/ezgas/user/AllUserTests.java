package it.polito.ezgas.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//@RunWith(SpringRunner.class)
//@SpringBootTest

@RunWith(Suite.class)
@SuiteClasses({
	UserControllerTests.class,
	UserConverterTests.class,
	UserDtoTests.class,
	UserServiceTests.class,
	UserTests.class,
	OtherTests.class
})
public class AllUserTests {
}
