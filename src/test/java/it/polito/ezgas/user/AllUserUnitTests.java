package it.polito.ezgas.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	UserTests.class,
	UserRepositoryTests.class
})
public class AllUserUnitTests {

}
