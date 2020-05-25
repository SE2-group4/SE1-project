package it.polito.ezgas.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by softeng on 27/4/2020.
 */
@Entity
@Table(name="USER")
public class User implements Serializable{

    private static final long serialVersionUID = 0x62A6DA99AABDA8A8L;
	
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer userId;
	@Column(name="USER_NAME")
    private String userName;
	@Column(name="PASSWORD")
    private String password;
	@Column(name="EMAIL")
    private String email;
	@Column(name="REPUTATION")
    private Integer reputation;
	@Column(name="ADMIN") 
    private Boolean admin;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }


    public User() {
    }

    public User(String userName, String password, String email, Integer reputation) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.reputation = reputation;
    }

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
    
    public Boolean getAdmin() {
    	return admin;
    }
    
    public void setAdmin(Boolean admin) {
    	this.admin = admin;
    }
}
