package com.api.crud.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;

@RestController
@RequestMapping("/api/cliente")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/read-all")
	public ArrayList<UserModel> getUsers(){
		return this.userService.getUsers();
	}	
	
	@PostMapping
	public UserModel saveUser(@RequestBody UserModel user) {
		return userService.saveUser(user);
	}
	
	
	@GetMapping("/read-by-id/{id}") 
	public Optional<UserModel> getUserById(@PathVariable Long id){
		return this.userService.getById(id);
	}
	
	
    @PutMapping("/{id}")
    public UserModel updateUser(@RequestBody UserModel user, @PathVariable Long id) {
        return userService.updateById(user, id);
    }
	
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Long id) {
		boolean ok = this.userService.deleteUser(id);
		
		if(ok) {
			return "User with id " + id + " Delete";
		} else {
			return "Error, can´t Delete User with id " + id ;
		}
		
	}
	
}
