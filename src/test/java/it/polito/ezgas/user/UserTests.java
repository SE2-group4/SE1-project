package it.polito.ezgas.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezgas.entity.User;

public class UserTests {
	private User user;
	
	@Before
	public void setUp() throws Exception {
		this.user = new User();		
		this.user.setUserName("Aldo");
		this.user.setPassword("buonaquestacadrega");
		this.user.setEmail("aldo.baglio@agg.it");
		this.user.setReputation(0);
		this.user.setAdmin(false);
	}
	
	@Test
	public void testInputValidation() {
		int newId = 9999;
		this.user.setUserId(newId);
		assertFalse("User ID should not be changeable!", this.user.getUserId() == newId);
		
		String notAnEmail = new String("not an email");
		this.user.setEmail(notAnEmail);
		assertFalse("Email should be in the right format!", this.user.getEmail().equals(notAnEmail));
		
		String notTrimmedText = new String("   Aldo    ");
		this.user.setUserName(notTrimmedText);
		assertFalse("Please trim the input string!", this.user.getUserName().equals(notTrimmedText));
	}
	
	@Test
	public void testReputation() {
		int newReputation = 999;
		this.user.setReputation(newReputation);
		assertTrue("Trust level must by in [-5, +5]!", this.user.getReputation() <= +5 && this.user.getReputation() >= -5);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
}
