package com.web;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository) {
	this.userRepository = userRepository;
	}


	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	

	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)

	public void deleteUser(@PathVariable Long id){
		userRepository.delete(id);
	}
	

	@RequestMapping(value="/user", method=RequestMethod.POST)
	public void createUser(@RequestBody User user ){
		User new_one = new User();
		new_one=user;
		System.out.println(new_one.toString());
		userRepository.save(new_one);
	}

}