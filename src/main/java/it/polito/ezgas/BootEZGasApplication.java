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

		User user = new User("admin", "admin", "admin@ezgas.com", 5);
		user.setAdmin(true);

		// da commentare da qui
		PreparedStatement st = null;
		PreparedStatement u1 = null;
		PreparedStatement u2 = null;
		PreparedStatement gs1 = null;
		PreparedStatement gs2 = null;
		PreparedStatement gs3 = null;
		PreparedStatement del1 = null;
		PreparedStatement del2 = null;
		
		del2 = conn.prepareStatement("DELETE FROM GAS_STATION");
		del2.execute();
		del1 = conn.prepareStatement("DELETE FROM USER");
		del1.execute();
		

		st = conn.prepareStatement("INSERT INTO USER (user_id, admin, email, password, reputation, user_name) "
				+ "VALUES (1, true, 'admin@ezgas.com', 'admin', 2, 'admin')");
		st.execute();

		u1 = conn.prepareStatement("INSERT INTO USER (user_id, admin, email, password, reputation, user_name) "
				+ "VALUES (2, false, 'user1@ezgas.com', 'user1', 0, 'user1');");
		u1.execute();

		u2 = conn.prepareStatement("INSERT INTO USER (user_id, admin, email, password, reputation, user_name) "
				+ "VALUES (3, false, 'user2@ezgas.com', 'user2', 0, 'user2');");
		u2.execute();

		gs1 = conn.prepareStatement(
				"INSERT INTO GAS_STATION (gas_station_id, car_sharing, diesel_price, gas_price, gas_station_address,"
						+ "gas_station_name, has_diesel, has_gas, has_methane, has_super, has_super_plus, lat, lon, methane_price,"
						+ "report_dependability, report_timestamp, report_user, super_plus_price, super_price, user_id) "
						+ "VALUES (1, 'Enjoy', 0, -1, 'Via Rocciamelone Caselle Torinese Piemont Italy', 'Station1', true, false,"
						+ "false, true, false, 45.1635676, 7.6647799, -1, 0, null, -1, -1, 0, null);");
		gs1.execute();

		gs3 = conn.prepareStatement(
				"INSERT INTO GAS_STATION (gas_station_id, car_sharing, diesel_price, gas_price, gas_station_address,"
						+ "gas_station_name, has_diesel, has_gas, has_methane, has_super, has_super_plus, lat, lon, methane_price,"
						+ "report_dependability, report_timestamp, report_user, super_plus_price, super_price, user_id) "
						+ "VALUES (3, 'null', 0, -1, 'Via Rocciamelone Caselle Torinese Piemont Italy', 'Station3', true, false,"
						+ "false, true, false, 45.1635676, 7.6647799, -1, 0, null, -1, -1, 0, null);");
		gs3.execute();
		
		gs2 = conn.prepareStatement(
				"INSERT INTO GAS_STATION (gas_station_id, car_sharing, diesel_price, gas_price, gas_station_address,"
						+ "gas_station_name, has_diesel, has_gas, has_methane, has_super, has_super_plus, lat, lon, methane_price,"
						+ "report_dependability, report_timestamp, report_user, super_plus_price, super_price, user_id) "
						+ "VALUES (2, 'Car2Go', -1, 3, 'Via Roma Turin Piemont Italy', 'Station2', false, true,"
						+ "true, false, true, 45.0705111, 7.6845806, 3, 0, 'Thu May 28 17:11:56 CEST 2020', 1, 3, -1, 1);");
		gs2.execute();
		
		conn.close();

		/*
		 * 
		 * list all the users stored in the database and, if there is no an admin user
		 * create it
		 * 
		 * User user= new User("admin", "admin", "admin@ezgas.com", 5);
		 * user.setAdmin(true);
		 * 
		 * and then save it in the db
		 * 
		 * 
		 */

	}

}
