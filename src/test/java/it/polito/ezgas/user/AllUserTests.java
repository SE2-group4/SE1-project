package it.polito.ezgas.user;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polito.ezgas.dto.UserDto;

//@RunWith(SpringRunner.class)
//@SpringBootTest

@RunWith(Suite.class)
@SuiteClasses({
	UserControllerTests.class,
	UserConverterTests.class,
	UserDtoTests.class,
	UserServiceTests.class,
	UserTests.class
})
public class AllUserTests {
	
	@Test
	public void testJavadoc() {
		fail("Please add Javadoc everywhere (classes and methods), then delete this test!");
	}
}
