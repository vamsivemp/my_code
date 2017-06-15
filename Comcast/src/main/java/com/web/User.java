package com.web;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User{

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
private String username;
private String email;
private Date userRegisteredDate;
//avinash has made
User(){
}

public User(String username, String email) {
	this.username = username;
	this.email = email;
	this.userRegisteredDate = new Date();
}
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getUserRegisteredDate() {
	return userRegisteredDate;
}
public void setUserRegisteredDate(Date userRegisteredDate) {
	this.userRegisteredDate = userRegisteredDate;
}

@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("User[id =%d, username =%s, email=%s,date =%s]",id, username,email,userRegisteredDate.toString());
	}
}
