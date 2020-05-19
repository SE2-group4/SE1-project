package it.polito.ezgas;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.UserTests;

@SelectClasses({
	UserTests.class
})
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllUserUnitTests {
}
