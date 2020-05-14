package it.polito.ezgas.user;

import org.junit.platform.suite.api.SelectClasses;

@SelectClasses({
	UserTests.class,
	UserRepositoryTests.class
})
public class AllUserUnitTests {
}
