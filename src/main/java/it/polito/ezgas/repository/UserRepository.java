package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	public List<User> findByEmailAndPassword(String email, String password); 
	
	public List<User> findByUserId(Integer user_id);
	
}
