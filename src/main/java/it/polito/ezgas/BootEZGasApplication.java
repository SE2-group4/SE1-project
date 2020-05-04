package it.polito.ezgas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.polito.ezgas.entity.User;

@SpringBootApplication
public class BootEZGasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootEZGasApplication.class, args);
	}
	
	@PostConstruct
	public void setupDbWithData() throws SQLException {
				
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/memo", "sa", "password");
		//da togliere
		conn.close();
				
		User user = new User("admin", "admin", "admin@ezgas.com", 5);
		user.setAdmin(true);
		
		/*PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO USER (admin, email, password, reputation, user_name) VALUES (?, ?, ?, ?, ?)");
			st.setBoolean(1, user.getAdmin());
			st.setString(2, user.getEmail());
			st.setString(3, user.getPassword());
			st.setLong(4, user.getReputation());
			st.setString(5, user.getUserName());
			
			st.execute();
			
			System.out.println(user.getEmail());
		} 
		
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			if(st!=null) {
				st.close();
			}
			conn.close();
		}
		/*
		 
		list all the users stored in the database and, if there is no an admin user create it
		 
			User user= new User("admin", "admin", "admin@ezgas.com", 5);
			user.setAdmin(true);
			
		and then save it in the db
	
			
		*/

	}

}
