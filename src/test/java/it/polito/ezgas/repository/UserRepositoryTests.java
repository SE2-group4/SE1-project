package it.polito.ezgas.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.polito.ezgas.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {
	
	@Autowired
    private UserRepository userRepository;
	
	@Test
    public void testFindByEmailAndPassword() {

        User user = new User("admin", "password", "admin@gmail.com", 1);
        user.setAdmin(true);
        //user.setUserId(50);
        userRepository.save(user);
        List<User> uList = userRepository.findByEmailAndPassword("admin@gmail.com", "password");
        assertNotNull(user);
        assertEquals(1, uList.size());
        User user2 = uList.get(0);
        assertNotNull(user2);
        assertEquals(user.getUserName(), user2.getUserName());
        assertEquals(user.getPassword(), user2.getPassword());
        assertEquals(user.getEmail(), user2.getEmail());
    }
	
	@Test
    public void testFindByUserId() {

		User user3 = new User("admin3", "password3", "admin3@gmail.com", 1);
        user3.setAdmin(true);
        userRepository.save(user3);
        List<User> uList = userRepository.findByUserId(user3.getUserId());
        assertNotNull(user3);
        assertEquals(1, uList.size());      
        User user4 = uList.get(0);
        assertNotNull(user4);
        assertEquals(user3.getAdmin(), user4.getAdmin());
        assertEquals(user3.getUserId(), user4.getUserId());
        assertEquals(user3.getUserName(), user4.getUserName());
        assertEquals(user3.getPassword(), user4.getPassword());
        assertEquals(user3.getEmail(), user4.getEmail());
        assertEquals(user3.getReputation(), user4.getReputation());
    }
}
